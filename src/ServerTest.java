package src;

import src.client.Connection;
import src.server.ApplicationServer;
import src.util.LogUtil;

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


        final Connection connection = new Connection("localhost", 80);
        LogUtil.getLogger().log(Level.INFO, connection.receive());


        connection.send("{\"status_code\":200,\"id\":\"AUTH\", \"payload\": \"Ali\"}");

        LogUtil.getLogger().log(Level.INFO, connection.receive());
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
