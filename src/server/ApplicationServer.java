package src.server;

import src.util.List;
import src.util.LogUtil;

import java.util.logging.Level;

public class ApplicationServer extends Server {

    public static final int PORT = 80;

    private final List<User> users = new List<>(); //List of all active Users
    private final List<Highscore> highscores = new List<>();

    public ApplicationServer() {
        super(PORT);
        LogUtil.getLogger().log(Level.INFO, "Server started on port:" + PORT);
    }

    @Override
    public void processNewConnection(String pClientIP, int pClientPort) {
        LogUtil.getLogger().log(Level.INFO, "New client connected with IP: " + pClientIP + " on port: " + pClientPort);


    }

    @Override
    public void processMessage(String pClientIP, int pClientPort, String pMessage) {

    }

    @Override
    public void processClosingConnection(String pClientIP, int pClientPort) {

    }
}
