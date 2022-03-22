package src.server;

import org.json.JSONObject;
import src.util.List;
import src.util.LogUtil;

import java.util.logging.Level;

public class ApplicationServer extends Server {

    public static final int PORT = 80;

    public static final List<User> USER_LIST = new List<>(); //List of all active Users
    public static final List<Highscore> HIGHSCORE_LIST = new List<>();

    public ApplicationServer() {
        super(PORT);
        LogUtil.getLogger().log(Level.INFO, "Server started on port:" + PORT);
    }

    @Override
    public void processNewConnection(String pClientIP, int pClientPort) {
        LogUtil.getLogger().log(Level.INFO, "New client connected with IP: " + pClientIP + " on port: " + pClientPort);
        final User newUser = new User(pClientIP, pClientPort, new PacketManager());
        USER_LIST.append(newUser);
    }

    @Override
    public void processMessage(String pClientIP, int pClientPort, String pMessage) {
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
        final JSONObject returnToSender = user.getPacketManager().proccessMessage(pMessage, user);
        if (returnToSender != null) {
            send(user.getClientIP(), user.getClientPort(), returnToSender + "\r\n");
        }
    }

    @Override
    public void processClosingConnection(String pClientIP, int pClientPort) {

    }
}
