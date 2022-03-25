package src;

import src.server.ApplicationServer;
import src.util.LogUtil;
import src.client.*;

import java.util.logging.Level;

/**
 * Die Test-Klasse ServerTest.
 * Unit tests, to test the server's functionallity 
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

    //@BeforeEach
    public void setUp() {
        ApplicationServer.INSTANCE.startServer();
        final Player player = new Player();

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
