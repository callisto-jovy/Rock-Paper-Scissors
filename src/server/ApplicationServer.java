package src.server;

import src.server.packets.*;
import src.util.*;

import java.util.Queue;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.logging.Level;

public class ApplicationServer extends Server {

    public static final ApplicationServer INSTANCE = new ApplicationServer();

    /**
     * The port on which the server binds on
     */
    public static final int PORT = 2049;
    /**
     * List of all active users
     */
    public final List<User> userList = new List<>();
    /**
     * List of all high-scores also used to check for names
     */
    public final List<Highscore> highscoreList = new List<>();
    /**
     * List of all ongoing matches
     */
    public final List<Match> matchList = new List<>();
    /**
     * Queue with all users which are searching for a match
     */
    public final Queue<User> matchQueue = new ConcurrentLinkedQueue<>();
    /**
     * Private list with all packets that the server may receive
     */
    private final List<Packet> packetList = new List<>();

    public ApplicationServer() {
        super(PORT);
        LogUtil.getLogger().log(Level.INFO, "Server started on port:" + PORT);

        //Add packets
        packetList.toFirst();
        packetList.append(new AuthPacket());
        packetList.append(new ListPacket());
        packetList.append(new HighscorePacket());
        packetList.append(new MatchPacket());
        packetList.append(new SearchPacket());

        //Initiate search task:
        final Timer timer = new Timer(); // Instantiate Timer Object
        timer.scheduleAtFixedRate(new TimerTask() {
                public void run() {
                    //This task matches the players together, always the first two from the queue.
                    LogUtil.getLogger().log(Level.INFO, "Executing match task, matching users...");
                    //Get the front of the queue and check whether the front is null. If it is null, no users is searching, return.
                    final User latestSearching = matchQueue.poll();
                    if(latestSearching == null) 
                        return;
                    //At this point at least one user is searching for a match, peek the front of the queue
                    //which is now the next searching user or nulll
                    final User nextSearching = matchQueue.peek();
                    if(nextSearching == null) {
                        matchQueue.add(latestSearching); 
                        //If the next searching is indeed null, the only user which searches for a match is the previous front,
                        //That's why he is added back to the queue
                    } else { //Otherwise, we know that at least two users are searching for a match 
                        final User next = matchQueue.poll();
                        //Match both users together and create a new match, then append it to the list of ongoing matches.
                        final Match match = new Match(next, latestSearching);
                        matchList.toFirst();
                        matchList.append(match);
                        //Start the match.
                        match.start();
                    }

                }
            }, 10, 5000); //Repeat every 5s with a start delay of 10ms.
    }

    @Override
    public void processNewConnection(String pClientIP, int pClientPort) {
        LogUtil.getLogger().log(Level.INFO, "New client connected with IP: " + pClientIP + " on port: " + pClientPort);

        //Create and append new user object
        final User newUser = new User(pClientIP, pClientPort);
        userList.toFirst();
        userList.append(newUser);
        //Send a user-authentication packet to the newly connected user
        final AuthPacket userAuthPacket = new AuthPacket();
        this.sendToUser(newUser, userAuthPacket);
    }

    @Override
    public void processMessage(String pClientIP, int pClientPort, String pMessage) {
        //Check message
        if (pMessage.isEmpty())
            return;

        //Get user from list to process packets
        final User user = getUserByIPAndPort(pClientIP, pClientPort);

        if(user != null) {
            final PacketManager packetManager = new PacketManager(packetList);

            final Packet returnToSender = packetManager.processMessage(pMessage, user);
            //Send the formatted packet to the sender if the packet has to send anything at all.
            if (returnToSender != null) {
                send(user.getClientIP(), user.getClientPort(), PacketFormatter.formatPacket(returnToSender));
                //Reset packet (must be done)
                returnToSender.getData().remove("payload");
                returnToSender.getData().remove("error");
            }
        }
    }

    @Override
    public void processClosingConnection(String pClientIP, int pClientPort) {
        //Remove user from list if disconnected
        User toRemove = null;

        userList.toFirst();
        while (userList.hasAccess()) {
            final User user = userList.getContent();
            if (user.getClientIP().equals(pClientIP) && user.getClientPort() == pClientPort) {
                toRemove = user;
                userList.remove();
                break;
            }
            userList.next();
        }

        //Remove user from search queue
        if(toRemove != null)
            matchQueue.remove(toRemove);

        //TODO: Remove user from match & if necessary close match
    }

    /******** Additional Methods *********/
    /**
     * Returns a given user found by its name or null
     * @param name the user's username 
     */
    public User getUserByName(final String name) {
        userList.toFirst();

        while(userList.hasAccess()) {
            final User content = userList.getContent();

            if(content.getName().equals(name)) {
                return content;
            }

            userList.next();
        }
        return null;
    }

    public User getUserByIPAndPort(final String ipAddress, final int port) {
        userList.toFirst();

        while(userList.hasAccess()) {
            final User content = userList.getContent();

            if(content.getClientIP().equals(ipAddress) && content.getClientPort() == port) {
                return content;
            }

            userList.next();
        }
        return null;
    }

    public boolean isUserInMatch(final User... users) {
        matchList.toFirst();

        while(matchList.hasAccess()) {
            final Match match = matchList.getContent();

            for(final User user : users) {
                if(match.containsUser(user)) {
                    return true;
                }
            }
            matchList.next();

        }
        return false;
    }

    public void sendToUser(final User user, final Packet packet) {
        packet.send();
        this.send(user.getClientIP(), user.getClientPort(), PacketFormatter.formatPacket(packet));
    }

}
