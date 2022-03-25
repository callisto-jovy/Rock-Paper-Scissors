package src.server.packets;

import org.json.JSONArray;
import src.server.ApplicationServer;
import src.server.Highscore;
import src.server.User;
import src.util.Packet;
import src.util.PacketUtil;

/**
 * Beschreiben Sie hier die Klasse HighscorePacket.
 *
 * @author (Ihr Name)
 * @version (eine Versionsnummer oder ein Datum)
 */
public class HighscorePacket extends Packet {

    public HighscorePacket() {
        super("HLST");
    }


    @Override
    public void receive(PacketUtil input, User parent) {
        ApplicationServer.INSTANCE.highscoreList.toFirst();
        final JSONArray jsonArray = new JSONArray();

        while (ApplicationServer.INSTANCE.highscoreList.hasAccess()) {
            Highscore hs = ApplicationServer.INSTANCE.highscoreList.getContent();
            jsonArray.put(hs.getName() + ":" + hs.getScore());

            ApplicationServer.INSTANCE.highscoreList.next();
        }

        setPayload(jsonArray);
    }

    public void send() {

    }


}
