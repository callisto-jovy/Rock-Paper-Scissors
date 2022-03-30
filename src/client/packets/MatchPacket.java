package src.client.packets;

import org.json.JSONObject;
import src.client.Player;
import src.server.User;
import src.util.Packet;
import src.util.PacketUtil;
import src.util.eventapi.EventManager;
import src.util.events.MatchEvent;

import javax.swing.*;

public class MatchPacket extends Packet {

    public MatchPacket() {
        super("MATC");
    }

    @Override
    public void receive(PacketUtil input, User user) {
        final JSONObject payloadJSON = input.getPayloadJSON();

        if (input.hasPayload()) {
            final int decisionEnemy = payloadJSON.getInt("decision");
            final String winner = payloadJSON.getString("winner");
            final String looser = payloadJSON.getString("nico");
            EventManager.call(new MatchEvent(winner, looser, decisionEnemy));
        } else if (input.isError()) {
            JOptionPane.showMessageDialog(null, "Error: " + input.getError());
        }
    }

    @Override
    public void send() {
        setPayload(Player.INSTANCE.getDecision());
    }

}


