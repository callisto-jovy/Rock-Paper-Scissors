package src.client.packets;

import org.json.JSONObject;
import src.server.User;
import src.util.Packet;

public class AuthPacket extends Packet {

    public AuthPacket() {
        super("AUTH");
    }

    @Override
    public void receive(JSONObject input, User user) {

    }

    @Override
    public void send() {

    }


}

