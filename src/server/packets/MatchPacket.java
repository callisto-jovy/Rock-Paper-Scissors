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
        super("MTCH");
    }

    public MatchPacket(final User winner, final User looser, final int dec) {
        super("MTCH");
        this.winner = winner;
        this.looser = looser;
        this.decision = dec;
    }


    @Override
    public void receive(PacketUtil input, User parent) {
        final int decision = input.getPayloadInt();

        ApplicationServer.INSTANCE.matchList.toFirst();
        while (ApplicationServer.INSTANCE.matchList.hasAccess()) {
            final Match match = ApplicationServer.INSTANCE.matchList.getContent();
            if (match.getUser1().equals(parent)) {
                match.setDecision1(decision);
                match.decideWinner();
                return;
            } else if (match.getUser2().equals(parent)) {
                match.setDecision2(decision);
                match.decideWinner();
                return;
            }
            ApplicationServer.INSTANCE.matchList.next();
        }
    }

    @Override
    public void send() {
        final JSONObject matchResultPayload = new JSONObject();
        matchResultPayload.put("winner", winner.getName());
        matchResultPayload.put("nico", looser.getName());
        matchResultPayload.put("decision", decision);

        setPayload(matchResultPayload);
    }
}
