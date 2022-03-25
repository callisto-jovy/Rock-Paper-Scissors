package src.client.packets;

import org.json.JSONArray;
import src.server.ApplicationServer;
import src.server.Highscore;
import src.server.User;
import src.util.Packet;
import src.util.PacketUtil;
import javax.swing.*;

public class HighscorePacket extends Packet
{

    public HighscorePacket() {
        super("HLST");
    }

    @Override
    public void receive(PacketUtil input, User parent) {
        if(input.hasPayload()) {
            final JSONArray highscoreArray = input.getPayloadArray();
            for(int i = 0; i < highscoreArray.length(); i++) {
                System.out.println(highscoreArray.get(i));
            }
        } else if(input.isError()) {
            final String error = input.getError();
            JOptionPane.showMessageDialog(null, "Error: " + error);
        }

    }

    public void send() {
        setPayload("highscores");
    }
}
