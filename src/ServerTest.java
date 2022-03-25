package src;

import src.client.Player;
import src.server.ApplicationServer;

/**
 * Die Test-Klasse ServerTest.
 * Unit tests, to test the server's functionality
 *
 * @author Roman
 * @version 1.0
 */
public class ServerTest {

    /**
     * Konstruktor fuer die Test-Klasse ServerTest
     */
    public ServerTest() {
    }

    public static void main(String[] args) {
        ApplicationServer.INSTANCE.startServer();
        Player.PLAYER.connect();
        System.out.println("Player connected:" + Player.PLAYER.isConnected());
    }

    //@BeforeEach
    public void setUp() {
        ApplicationServer.INSTANCE.startServer();


    }

    /**
     * Gibt das Testgerüst wieder frei.
     * <p>
     * Wird nach jeder Testfall-Methode aufgerufen.
     */
    //@AfterEach
    public void tearDown() {
    }
}
