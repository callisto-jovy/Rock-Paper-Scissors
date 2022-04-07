package src.client.packets;

import org.json.JSONObject;
import src.server.User;
import src.util.ImageUtil;
import src.util.Packet;
import src.util.PacketUtil;
import src.util.UserRequestMatchPromptDialog;

import javax.swing.*;

import static javax.swing.JOptionPane.YES_NO_OPTION;

public class MatchRequestPacket extends Packet {
    private String userToChallenge;

    public MatchRequestPacket(final String userToChallenge) {
        super("MTRQ");
        this.userToChallenge = userToChallenge;
    }

    public MatchRequestPacket() {
        super("MTRQ");
    }

    @Override
    public void receive(PacketUtil input, User user) {
        if (input.isError()) {
            //TODO: other solution
            JOptionPane.showMessageDialog(null, input.getError());
        } else if (input.hasPayload()) {
            //User requested to match with you (all user data is sent)
            final JSONObject userData = input.getPayloadJSON();

            final String enemyName = userData.getString("username");
            final int defaultProfilePicture = userData.optInt("profile_picture");
            final ImageIcon icon = userData.has("custom_profile_picture") ?
                    ImageUtil.getImageIcon(
                            userData.getString("custom_profile_picture"),
                            128,
                            128) :
                    ImageUtil.getImageIconFromID(defaultProfilePicture);


            final int selection = JOptionPane.showConfirmDialog(
                    null,
                    new UserRequestMatchPromptDialog(icon, enemyName),
                    "A opponent has challenged you to a match!",
                    YES_NO_OPTION,
                    JOptionPane.PLAIN_MESSAGE);

            final JSONObject payloadJSON = new JSONObject();
            payloadJSON.put("selection", selection); //0 = yes, 1 = no
            payloadJSON.put("response_to", enemyName);

            setPayload(payloadJSON);
            setStatusCode(120);
        }
    }

    @Override
    public void send() {
        //You have to set the payload first, then you override the status code!
        setPayload(userToChallenge);
        setStatusCode(80);
    }

}
