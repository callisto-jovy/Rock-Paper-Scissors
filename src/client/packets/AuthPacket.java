package src.client.packets;

import org.json.JSONObject;
import src.server.ApplicationServer;
import src.server.Highscore;
import src.server.Packet;
import src.server.User;

public class AuthPacket extends Packet {
    
    public AuthPacket() {
        super("AUTH");
    }
    
    public void receive() {
        
    }
    
    public void send(JSONObject input, User user) {
        
    }
    
    
}

