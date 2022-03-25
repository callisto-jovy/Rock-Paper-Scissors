package src.client.packets;

import org.json.JSONObject;
import src.server.User;
import src.util.Packet;


public class MatchPacket extends Packet {

    public MatchPacket() {
        super("MATC");
    }

    @Override
    public void send() {

    }

    @Override
    public void receive(JSONObject object, User user) {

    }

}
