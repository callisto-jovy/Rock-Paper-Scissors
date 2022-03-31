package src.client.packets;

import org.json.JSONObject;
import src.server.User;
import src.util.Packet;
import src.util.PacketUtil;
import javax.swing.*;
import src.util.eventapi.EventManager;
import src.util.events.MatchEvent;

public class ResultPacket extends Packet {

    public ResultPacket() {
        super("RSLT");
    }

    @Override
    public void receive(PacketUtil input, User parent) {
        final JSONObject payloadJSON = input.getPayloadJSON();
        if(input.hasPayload()) {
            final String winner = payloadJSON.getString("winner");
            EventManager.call(new MatchEvent());
        } else if (input.isError()) {
            JOptionPane.showMessageDialog(null, "Error: " + input.getError());
        }
        
    }

    @Override
    public void send() {
        //nico
    }
}
