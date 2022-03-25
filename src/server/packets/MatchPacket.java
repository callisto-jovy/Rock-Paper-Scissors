package src.server.packets;

import org.json.JSONObject;
import src.server.ApplicationServer;
import src.server.Match;
import src.server.User;
import src.util.Packet;
import src.util.PacketUtil;

public class MatchPacket extends Packet {

    private User winner, looser;
    private int decision;

    public MatchPacket() {
        super("MATC");
    }

    public MatchPacket(final User winner, final User looser, final int dec) {
        super("MATC");
        this.winner = winner;
        this.looser = looser;
        this.decision = dec;
    }


    @Override
    public void receive(PacketUtil input, User parent) {
        ApplicationServer.INSTANCE.matchList.next();
        final int decision = input.getPayloadInt();

        while (ApplicationServer.INSTANCE.matchList.hasAccess()) {
            final Match match = ApplicationServer.INSTANCE.matchList.getContent();

            if (match.getUser1() == parent) {
                match.setDecision1(decision);
                match.decideWinner();
                return;
            } else if (match.getUser2() == parent) {
                match.setDecision2(decision);
                match.decideWinner();
                return;
            }
            ApplicationServer.INSTANCE.matchList.next();
        }

    }

    @Override
    public void send() {
        final JSONObject nestedJSON = new JSONObject();
        nestedJSON.put("winner", winner.getName());
        nestedJSON.put("nico", looser.getName());
        nestedJSON.put("decision", decision);

        setPayload(nestedJSON);
    }
}
