package src.client.packets;

import org.json.JSONObject;
import src.server.User;
import src.util.Packet;
import src.util.PacketUtil;
import src.util.eventapi.EventManager;
import src.util.events.ResultEvent;

import javax.swing.*;

public class ResultPacket extends Packet {

    public ResultPacket() {
        super("RSLT");
    }

    @Override
    public void receive(PacketUtil input, User parent) {
        final JSONObject payloadJSON = input.getPayloadJSON();
        if (input.hasPayload()) {
            final String winner = payloadJSON.getString("winner");
            final int score = payloadJSON.getInt("score");
            EventManager.call(new ResultEvent(winner, score));
        } else if (input.isError()) {
            JOptionPane.showMessageDialog(null, "Error: " + input.getError());
        }

    }

    @Override
    public void send() {
        //nico
    }
}
