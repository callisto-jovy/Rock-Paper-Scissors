package src.server.packets;
import src.server.Packet;
import org.json.JSONObject;
import org.json.JSONArray;
import src.server.ApplicationServer;
import src.server.Highscore;
import src.server.User;

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
        ApplicationServer.HIGHSCORE_LIST.toFirst();
        JSONArray jsonArray = new JSONArray();

        while(ApplicationServer.HIGHSCORE_LIST.hasAccess()) {
            Highscore hs = ApplicationServer.HIGHSCORE_LIST.getContent();    
            jsonArray.put(hs.getName() + ":" + hs.getScore());
            
            ApplicationServer.HIGHSCORE_LIST.next();
        }
        
        getData().put("payload", jsonArray);
    }

}
