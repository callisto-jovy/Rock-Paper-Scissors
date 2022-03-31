package src.server.packets;

import org.json.JSONObject;
import src.server.User;
import src.util.Packet;
import src.util.PacketUtil;

public class ResultPacket extends Packet {

    private User winner;
    private int score;

    public ResultPacket() {
        super("RSLT");
    }

    public ResultPacket(final User winner, final int score) {
        super("RSLT");
        this.winner = winner;
        //this.score = score;
    }

    @Override
    public void receive(PacketUtil input, User parent) {
        //Receive empty
    }

    public void send() {
        final JSONObject payloadWinner = new JSONObject();
        payloadWinner.put("winner", winner.getName());
        payloadWinner.put("score", score);
                                
        setPayload(payloadWinner);
    }
}
