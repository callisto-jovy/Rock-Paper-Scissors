package src.client.packets;

import org.json.JSONObject;
import src.client.Player;
import src.server.User;
import src.util.Packet;
import src.util.PacketUtil;
import src.util.eventapi.EventManager;
import src.util.events.MatchEvent;

import javax.swing.*;

/**
 * Beschreiben Sie hier die Klasse MatchRequestPacket.
 * 
 * @author (Ihr Name) 
 * @version (eine Versionsnummer oder ein Datum)
 */
public class MatchRequestPacket extends Packet
{
    
    
    /**
     * Konstruktor für Objekte der Klasse MatchRequestPacket
     */
    public MatchRequestPacket()
    {
        super("MTRQ");
    }
    
    @Override
    public void receive(PacketUtil input, User user) {
        
    }
    
    @Override 
    public void send() {
        setStatusCode(80);
        final JSONObject userPayload = new JSONObject();
        
        userPayload.put("matchrequest", Player.INSTANCE.getName());
    }
    
}
