package src.server.packets;

import src.util.*;
import src.server.*;

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
        
    }
}
