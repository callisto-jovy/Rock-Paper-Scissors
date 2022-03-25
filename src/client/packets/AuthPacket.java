package src.client.packets;

import src.server.User;
import src.util.Packet;
import src.util.PacketUtil;

import javax.swing.*;

public class AuthPacket extends Packet {

    public AuthPacket() {
        super("AUTH");
    }

    @Override
    public void receive(PacketUtil input, User user) {
        if (input.isError()) {
            final String error = input.getError();
            JOptionPane.showMessageDialog(null, "Error: " + error);

            final String userName = JOptionPane.showInputDialog("Enter username!");
            setPayload(userName);
        } else if (input.hasPayload()) {
            final String payload = input.getPayloadString();
            if (payload.equals("prompt")) {
                final String userName = JOptionPane.showInputDialog("Enter username!");
                setPayload(userName);
            }
        }
    }

    @Override
    public void send() {
        //Empty, user does not need to send any auth packes, instead the user is prompted by the server.
    }

}
