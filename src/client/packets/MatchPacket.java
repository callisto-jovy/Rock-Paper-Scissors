package src.client.packets;
import src.server.Packet;
import org.json.JSONObject;
import org.json.JSONArray;
import src.server.ApplicationServer;
import src.server.Highscore;
import src.server.User;


public class MatchPacket extends Packet
{
    public MatchPacket() {
        super("MATC");
    }
    
    public void send(JSONObject object, User user){
        
    }
    
    public void receive() {
        
    }
}
