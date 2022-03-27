package src.client.packets;

import org.json.JSONObject;
import src.server.User;
import src.util.Packet;

public class SearchPacket extends Packet {

    public SearchPacket() {
        super("SEAR");
    }

    @Override
    public void send() {
        //this.setSearchingMatch whatever (true);
        setPayload("halt");
    }

    @Override
    public void receive(final JSONObject object, User user) {

    }
}
