package src.client.packets;
import src.server.Packet;
import org.json.JSONObject;
import org.json.JSONArray;
import src.server.ApplicationServer;
import src.server.Highscore;
import src.server.User;

public class SearchPacket extends Packet
{ 
   public SearchPacket() {
       super("SEAR");
   }
   
   public void send(final JSONObject object, User user){
       //this.setSearchingMatch whatever (true);
       setPayload("halt");
   }
   
   public void receive() {
       
   }
}
