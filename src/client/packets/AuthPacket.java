package src.client.packets;

import org.json.JSONObject;
import src.client.Player;
import src.server.User;
import src.util.Packet;
import src.util.PacketUtil;
import src.util.eventapi.EventManager;
import src.util.events.AuthPacketEvent;
import src.util.events.UsernameErrorEvent;

public class AuthPacket extends Packet {

    public AuthPacket() {
        super("AUTH");
    }

    @Override
    public void receive(PacketUtil input, User user) {
        if (input.isError()) {
            final String error = input.getError();
            //JOptionPane.showMessageDialog(null, "Error: " + error);
            EventManager.call(new UsernameErrorEvent(error));
        } else if (input.hasPayload()) {
            final JSONObject userPayload = new JSONObject();

            final String payload = input.getPayloadString();
            if (payload.equals("prompt")) {
                userPayload.put("username", Player.INSTANCE.getName());
                userPayload.put("profile_picture", Player.INSTANCE.getProfilePic());

                if (Player.INSTANCE.getCustomProfilePic() != null)
                    userPayload.put("custom_profile_picture", Player.INSTANCE.getCustomProfilePic());

                setPayload(userPayload);
            } else if (payload.equals("user added")) { //user was added.
                EventManager.call(new AuthPacketEvent());
            }
        }
    }

    @Override
    public void send() {
        final JSONObject userPayload = new JSONObject();

        userPayload.put("username", Player.INSTANCE.getName());
        userPayload.put("profile_picture", Player.INSTANCE.getProfilePic());

        if (Player.INSTANCE.getCustomProfilePic() != null)
            userPayload.put("custom_profile_picture", Player.INSTANCE.getCustomProfilePic());

        setPayload(userPayload);
    }

}
