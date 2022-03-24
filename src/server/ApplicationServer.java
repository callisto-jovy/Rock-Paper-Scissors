package src.server;

import src.server.packets.AuthPacket;
import src.util.List;
import src.util.LogUtil;
import src.util.Packet;
import src.util.PacketFormatter;

import java.util.logging.Level;

public class ApplicationServer extends Server {

    public static final ApplicationServer INSTANCE = new ApplicationServer();

    /**
     * The port on which the server binds on
     */
    public static final int PORT = 80;
    /**
     * List of all active users
     */
    public final List<User> userList = new List<>();
    /**
     * List of all high-scores also used to check for names
     */
    public final List<Highscore> highscoreList = new List<>();
    /**
     * List of all matches
     */
    public final List<Match> matchList = new List<>();


    public ApplicationServer() {
        super(PORT);
        LogUtil.getLogger().log(Level.INFO, "Server started on port:" + PORT);
    }

    @Override
    public void processNewConnection(String pClientIP, int pClientPort) {
        LogUtil.getLogger().log(Level.INFO, "New client connected with IP: " + pClientIP + " on port: " + pClientPort);

        //Create and append new user object
        final User newUser = new User(pClientIP, pClientPort, new PacketManager());
        userList.toFirst();
        userList.append(newUser);
        //Send a user-authentication packet to the newly connected user (prompt for a username)
        final AuthPacket userAuthPacket = new AuthPacket();
        userAuthPacket.send();
        send(pClientIP, pClientPort, PacketFormatter.formatPacket(userAuthPacket));
    }

    @Override
    public void processMessage(String pClientIP, int pClientPort, String pMessage) {
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

        assert user != null;
        final Packet returnToSender = user.getPacketManager().processMessage(pMessage, user);
        //Send the formatted packet to the sender if the packet has to send anything at all.
        if (returnToSender != null) {
            send(user.getClientIP(), user.getClientPort(), PacketFormatter.formatPacket(returnToSender));
        }
    }

    @Override
    public void processClosingConnection(String pClientIP, int pClientPort) {
        //Remove user from list if disconnected
        userList.toFirst();
        while (userList.hasAccess()) {
            final User user = userList.getContent();
            if (user.getClientIP().equals(pClientIP) && user.getClientPort() == pClientPort) {
                userList.remove();
                break;
            }
            userList.next();
        }
    }

}
