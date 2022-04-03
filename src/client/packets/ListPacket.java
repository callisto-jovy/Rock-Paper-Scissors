package src.client.packets;
//Client

import org.json.JSONArray;
import src.server.User;
import src.util.Packet;
import src.util.PacketUtil;
import src.util.eventapi.EventManager;
import src.util.events.ReceiveActiveUsersEvent;

import javax.swing.*;

public class ListPacket extends Packet {

    public ListPacket() {
        super("LIST");
    }

    @Override
    public void receive(PacketUtil input, User parent) {
        if(input.hasPayload()) {
            final JSONArray playerListArray = input.getPayloadArray();
            EventManager.call(new ReceiveActiveUsersEvent(playerListArray));

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
