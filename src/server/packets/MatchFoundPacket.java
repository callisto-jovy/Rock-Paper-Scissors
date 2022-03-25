package src.server.packets;


import org.json.JSONObject;
import src.server.User;
import src.util.Packet;

public class MatchFoundPacket extends Packet {

    public MatchFoundPacket() {
        super("MFND");
    }

    @Override
    public void receive(JSONObject input, User parent) {
    }

    @Override
    public void send() {
        setPayload("match found");
    }
}
