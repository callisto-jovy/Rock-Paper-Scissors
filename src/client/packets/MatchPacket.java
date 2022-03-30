package src.client.packets;

import org.json.JSONObject;
import src.server.User;
import src.util.Packet;
import src.util.PacketUtil;

public class MatchPacket extends Packet {

    private int decision;

    public MatchPacket() {
        super("MATC");
    }

    @Override
    public void send() {
        final JSONObject matchResultPayload = new JSONObject();
        //needs decision
        matchResultPayload.put("decision", decision);
        setPayload(matchResultPayload);
    }

    @Override
    public void receive(PacketUtil input, User user) {
        final JSONObject decision = input.getPayloadJSON();

        if (decision.has("decision")) {
            final int enemyDecision = input.getPayloadInt();
            //optical enemyDecision=?

        } else {
            //show Winner, show looser
        }

    }
}


