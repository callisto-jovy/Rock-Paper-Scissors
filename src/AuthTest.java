package src;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import src.client.Player;
import src.server.ApplicationServer;

/**
 * Die Test-Klasse ServerTest.
 * Unit tests, to test the server's functionality
 *
 * @author Roman
 * @version 1.0
 */
public class AuthTest {
    public AuthTest() {
    }

    @BeforeEach
    public void setUp() {
        ApplicationServer.INSTANCE.startServer();
    }

    @Test
    public void authTest() {
        Player.PLAYER.connect();
        System.out.println("Player connected:" + Player.PLAYER.isConnected());
    }

    /**
     * Gibt das Testgerüst wieder frei.
     * <p>
     * Wird nach jeder Testfall-Methode aufgerufen.
     */
    @AfterEach
    public void tearDown() {
        Player.PLAYER.close();
        ApplicationServer.INSTANCE.close();
    }
}
