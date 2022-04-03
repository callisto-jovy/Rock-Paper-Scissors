package src.client.packets;

import org.json.JSONArray;
import src.server.User;
import src.util.Packet;
import src.util.PacketUtil;

import javax.swing.*;

public class HighscorePacket extends Packet {

    public HighscorePacket() {
        super("HLST");
    }

    @Override
    public void receive(PacketUtil input, User parent) {
        if (input.hasPayload()) {
            final JSONArray highscoreArray = input.getPayloadArray();
            final StringBuilder highscores = new StringBuilder();
            for (int i = 0; i < highscoreArray.length(); i++) {
                highscores
                        .append("\n")
                        .append(highscoreArray.getString(i));
            }
            JOptionPane.showMessageDialog(null, "Highscores: " + highscores);
        } else if (input.isError()) {
            final String error = input.getError();
            JOptionPane.showMessageDialog(null, "Error: " + error);
        } else {
            JOptionPane.showMessageDialog(null, "No current Highscores...");
        }

    }

    public void send() {
        setPayload("highscores");
    }
}
