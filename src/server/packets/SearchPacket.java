package src.server.packets;
import src.server.Packet;
import org.json.JSONObject;
import org.json.JSONArray;
import src.server.ApplicationServer;
import src.server.Highscore;
import src.server.User;


/**
 * Beschreiben Sie hier die Klasse SearchPacket.
 * 
 * @author (Ihr Name) 
 * @version (eine Versionsnummer oder ein Datum)
 */
public class SearchPacket extends Packet
{
    public SearchPacket() {
        super("SEAR");
    }
    
    public void receive(final JSONObject object, User user) {
        user.setSearchesMatch(true);
        setPayload("halt"); 
    }
    
    public void send() {
        
    }
    
    
    
}
