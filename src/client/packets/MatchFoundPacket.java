package src.client.packets;

import org.json.JSONObject;
import src.server.User;
import src.util.Packet;
import src.util.PacketUtil;
import src.util.eventapi.EventManager;
import src.util.events.MatchFoundEvent;

import javax.swing.*;

public class MatchFoundPacket extends Packet {

    public MatchFoundPacket() {
        super("MFND");
    }

    @Override
    public void receive(PacketUtil input, User parent) {
        if (input.hasPayload()) {
            final JSONObject enemyJSON = input.getPayloadJSON();
            EventManager.call(new MatchFoundEvent(enemyJSON.getString("username"), enemyJSON.getInt("profile_picture")));
            //  parent.setSearchesMatch(false); //TODO: Will not work, please fix
        } else if (input.isError()) {
            JOptionPane.showMessageDialog(null, "Error: " + input.getError());
        }
    }

    @Override
    public void send() {

    }
}
