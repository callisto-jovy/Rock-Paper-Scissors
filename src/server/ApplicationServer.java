package src.server;

import src.server.packets.AuthPacket;
import src.util.List;
import src.util.LogUtil;
import src.util.PacketFormatter;

import java.util.logging.Level;

public class ApplicationServer extends Server {

    /**
     * The port on which the server binds on
     */
    public static final int PORT = 80;
    /**
     * List of all active users
     */
    public static final List<User> USER_LIST = new List<>();
    /**
     * List of all high-scores also used to check for names
     */
    public static final List<Highscore> HIGHSCORE_LIST = new List<>();

    public ApplicationServer() {
        super(PORT);
        LogUtil.getLogger().log(Level.INFO, "Server started on port:" + PORT);
    }

    @Override
    public void processNewConnection(String pClientIP, int pClientPort) {
        LogUtil.getLogger().log(Level.INFO, "New client connected with IP: " + pClientIP + " on port: " + pClientPort);

        //Create and append new user object
        final User newUser = new User(pClientIP, pClientPort, new PacketManager());
        USER_LIST.toFirst();
        USER_LIST.append(newUser);
        //Send a user-authentication packet to the newly connected user (prompt for a username)
        final AuthPacket userAuthPacket = new AuthPacket();
        userAuthPacket.send();
        send(pClientIP, pClientPort, PacketFormatter.formatPacket(userAuthPacket));
    }

    @Override
    public void processMessage(String pClientIP, int pClientPort, String pMessage) {
        //Get user from list to process packets
        USER_LIST.toFirst();
        User user = null;

        while (USER_LIST.hasAccess()) {
            final User u = USER_LIST.getContent();
            if (u.getClientIP().equals(pClientIP) && u.getClientPort() == pClientPort) {
                user = u;
                break;
            }
            USER_LIST.next();
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
        USER_LIST.toFirst();
        while (USER_LIST.hasAccess()) {
            final User user = USER_LIST.getContent();
            if (user.getClientIP().equals(pClientIP) && user.getClientPort() == pClientPort) {
                USER_LIST.remove();
                break;
            }
            USER_LIST.next();
        }
    }
}
