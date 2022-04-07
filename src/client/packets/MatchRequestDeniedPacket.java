package src.client.packets;

import src.server.User;
import src.util.Packet;
import src.util.PacketUtil;

import javax.swing.*;

public class MatchRequestDeniedPacket extends Packet {

    public MatchRequestDeniedPacket() {
        super("MTRQDN");
    }


    @Override
    public void receive(PacketUtil input, User user) {
        JOptionPane.showMessageDialog(null, "Your match request has been denied.");
    }

    @Override
    public void send() {
        //Not needed.
    }
}
