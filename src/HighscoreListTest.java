package src;


import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import src.client.Player;
import src.client.packets.HighscorePacket;
import src.server.ApplicationServer;
import src.util.PacketFormatter;


/**
 * Die Test-Klasse HighscoreListTest.
 *
 * @author  Roman
 */
public class HighscoreListTest
{
    /**
     * Konstruktor fuer die Test-Klasse HighscoreListTest
     */
    public HighscoreListTest()
    {
    }

    /**
     *  Setzt das Testgerüst fuer den Test.
     *
     * Wird vor jeder Testfall-Methode aufgerufen.
     */
    @BeforeEach
    public void setUp()
    {
        ApplicationServer.INSTANCE.startServer();
        Player.PLAYER.connect();

    }

    @Test
    public void highscoreTest() {
        final HighscorePacket packet = new HighscorePacket();
        packet.send();
        Player.PLAYER.send(PacketFormatter.formatPacket(packet));
    }

    /**
     * Gibt das Testgerüst wieder frei.
     *
     * Wird nach jeder Testfall-Methode aufgerufen.
     */
    @AfterEach
    public void tearDown()
    {
        Player.PLAYER.close();
        ApplicationServer.INSTANCE.close();
    }
}

