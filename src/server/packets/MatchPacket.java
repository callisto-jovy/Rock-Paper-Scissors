package src.server.packets;

import org.json.JSONObject;
import src.server.ApplicationServer;
import src.server.Match;
import src.server.User;
import src.util.Packet;

public class MatchPacket extends Packet {

    public MatchPacket() {
        super("MATC");
    }

    @Override
    public void receive(JSONObject input, User parent) {
        ApplicationServer.INSTANCE.matchList.next();
        final int decision = input.optInt("payload", -1);

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


    }
}
