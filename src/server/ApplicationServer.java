package src.server;

import src.server.packets.*;
import src.util.List;
import src.util.LogUtil;
import src.util.Packet;
import src.util.PacketFormatter;

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
                    LogUtil.getLogger().log(Level.INFO, "Executing match task, matching users...");
                    final User latestSearching = matchQueue.poll();
                    if(latestSearching == null) 
                        return;
                        
                    final User nextSearching = matchQueue.peek();
                    if(nextSearching == null) {
                        matchQueue.add(latestSearching);
                    } else {
                        final User next = matchQueue.poll();

                        final Match match = new Match(next, latestSearching);
                        matchList.toFirst();
                        matchList.append(match);
                        match.start();
                    }

                }
        }, 10, 5000);
    }

    @Override
    public void processNewConnection(String pClientIP, int pClientPort) {
        LogUtil.getLogger().log(Level.INFO, "New client connected with IP: " + pClientIP + " on port: " + pClientPort);

        //Create and append new user object
        final User newUser = new User(pClientIP, pClientPort);
        userList.toFirst();
        userList.append(newUser);
        //Send a user-authentication packet to the newly connected user (prompt for a username)
        final AuthPacket userAuthPacket = new AuthPacket();
        this.sendToUser(newUser, userAuthPacket);
    }

    @Override
    public void processMessage(String pClientIP, int pClientPort, String pMessage) {
        //Check message
        if (pMessage.isEmpty())
            return;

        //Get user from list to process packets
        userList.toFirst();
        User user = null;

        while (userList.hasAccess()) {
            final User u = userList.getContent();
            if (u.getClientIP().equals(pClientIP) && u.getClientPort() == pClientPort) {
                user = u;
                break;
            }
            userList.next();
        }

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

        //TODO: Remove user from match & if necessary close match (

    }

    public void sendToUser(final User user, final Packet packet) {
        packet.send();
        this.send(user.getClientIP(), user.getClientPort(), PacketFormatter.formatPacket(packet));
    }

}
