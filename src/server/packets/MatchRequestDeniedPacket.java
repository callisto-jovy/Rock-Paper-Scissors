package src.server.packets;

import src.server.User;
import src.util.Packet;
import src.util.PacketUtil;

public class MatchRequestDeniedPacket extends Packet
{
    
    public MatchRequestDeniedPacket() {
        super("MTRQDN");
    }
    
     
    @Override
    public void receive(PacketUtil input, User user) {
        //Ich habe mir mal die Freiheit genommen..
        
    }

    @Override 
    public void send() {
        setPayload("nico...");
    }
}
