package src;

import src.client.Player;
import src.server.ApplicationServer;

public class Main {

    public static void main(String[] args) {
        ApplicationServer.INSTANCE.startServer();
        Player.INSTANCE.displayConnectPage();
    }
}
