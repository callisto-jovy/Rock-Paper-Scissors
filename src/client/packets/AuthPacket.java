package src.client.packets;

import org.json.JSONObject;
import src.server.User;
import src.util.Packet;

import javax.swing.*;

public class AuthPacket extends Packet {

    public AuthPacket() {
        super("AUTH");
    }

    @Override
    public void receive(JSONObject input, User user) {
        if(input.has("payload")) {

            final String payload = input.getString("payload");
            if(payload.equals("prompt")) {
                final String userName = JOptionPane.showInputDialog("Enter username!");
                setPayload(userName);
            }
        } else {
            final String error = input.getString("error");
            JOptionPane.showMessageDialog(null, "Error: " + error);

            final String userName = JOptionPane.showInputDialog("Enter username!");
            setPayload(userName);
        }
    }

    @Override
    public void send() {
        //Empty, user does not need to send any auth packes, instead the user is prompted by the server.
    }

}
