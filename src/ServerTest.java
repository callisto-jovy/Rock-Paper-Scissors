package src;

import src.server.*;
import src.client.*;
import src.util.*;
import java.util.logging.Level;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Die Test-Klasse ServerTest.
 *
 * @author  (Ihr Name)
 * @version (eine Versionsnummer oder ein Datum)
 */
public class ServerTest
{
    /**
     * Konstruktor fuer die Test-Klasse ServerTest
     */
    public ServerTest()
    {
    }

    /**
     *  Setzt das Testgerüst fuer den Test.
     *
     * Wird vor jeder Testfall-Methode aufgerufen.
     */
    private ApplicationServer applicationServer;
    
    @BeforeEach
    public void setUp()
    {
        this.applicationServer = new ApplicationServer();
        
        final Connection connection = new Connection("localhost", 80);
        LogUtil.getLogger().log(Level.INFO, connection.receive());
        
        
        connection.send("{\"status_code\":200,\"id\":\"AUTH\", \"payload\": \"Ali\"}");
        
        LogUtil.getLogger().log(Level.INFO, connection.receive());
    }

    /**
     * Gibt das Testgerüst wieder frei.
     *
     * Wird nach jeder Testfall-Methode aufgerufen.
     */
    @AfterEach
    public void tearDown()
    {
    }
}
