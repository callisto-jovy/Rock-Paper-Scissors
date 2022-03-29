package src.client.packets;
//Client
import org.json.JSONArray;
import src.server.ApplicationServer;
import src.server.User;
import src.util.Packet;
import src.util.PacketUtil;
import javax.swing.*;

public class ListPacket extends Packet {

    public ListPacket() {
        super("LIST");
    }

    @Override
    public void receive(PacketUtil input, User parent) {
        if(input.hasPayload())
        {
            final JSONArray playerlistArray = input.getPayloadArray();
            for(int i = 0; i < playerlistArray.length(); i++) {
                System.out.println(playerlistArray.get(i));
            }
        }
        else if(input.isError())
        {
            final String error = input.getError();
            JOptionPane.showMessageDialog(null, "Error: " + error);
        }
    }

    @Override
    public void send() {
        setPayload("nico");
    }
}
