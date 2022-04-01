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
            final String enemyCustomProfilePictureBase64String = enemyJSON.optString("custom_profile_picture", null);
            
            EventManager.call(new MatchFoundEvent(enemyJSON.getString("username"), enemyJSON.getInt("profile_picture"), enemyCustomProfilePictureBase64String));
        } else if (input.isError()) {
            JOptionPane.showMessageDialog(null, "Error: " + input.getError());
        }
    }

    @Override
    public void send() {

    }
}
