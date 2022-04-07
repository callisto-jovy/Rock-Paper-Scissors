package src;

import src.server.ApplicationServer;
import src.util.*;
import java.util.logging.Level;

public class MainServer {

    public static void main(String[] args) {
                LogUtil.getLogger().setLevel(Level.OFF);

        ApplicationServer.INSTANCE.startServer();
    }
}
