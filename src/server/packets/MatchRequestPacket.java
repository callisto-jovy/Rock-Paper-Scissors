package src.server.packets;

import org.json.JSONObject;
import src.server.ApplicationServer;
import src.server.Match;
import src.server.User;
import src.util.Packet;
import src.util.PacketUtil;

/**
 * Beschreiben Sie hier die Klasse MatchRequestPacket.
 * 
 * @author (Ihr Name) 
 * @version (eine Versionsnummer oder ein Datum)
 */
public class MatchRequestPacket extends Packet
{
    
    public MatchRequestPacket()
    {
        super("MTRQ");
    }

    @Override
    public void receive(PacketUtil input, User user) {
        
    }
    
    @Override 
    public void send() {
        
        this.setPayload("MTRQ");
        
    }
}
