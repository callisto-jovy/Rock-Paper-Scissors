package src.server.packets;

import org.json.JSONArray;
import org.json.JSONObject;
import src.server.ApplicationServer;
import src.server.Highscore;
import src.server.User;
import src.util.Packet;

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


    public void send() {

    }


    public void receive(final JSONObject obj, User user) {
        ApplicationServer.INSTANCE.highscoreList.toFirst();
        JSONArray jsonArray = new JSONArray();

        while (ApplicationServer.INSTANCE.highscoreList.hasAccess()) {
            Highscore hs = ApplicationServer.INSTANCE.highscoreList.getContent();
            jsonArray.put(hs.getName() + ":" + hs.getScore());

            ApplicationServer.INSTANCE.highscoreList.next();
        }

        setPayload(jsonArray);
    }

}
