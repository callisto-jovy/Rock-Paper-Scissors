package src;

import src.server.ApplicationServer;

public class MainServer {

    public static void main(String[] args) {
        ApplicationServer.INSTANCE.startServer();
    }
}
