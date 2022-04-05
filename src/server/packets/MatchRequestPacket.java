package src.server.packets;

import org.json.JSONObject;
import src.server.ApplicationServer;
import src.server.User;
import src.util.Packet;
import src.util.PacketUtil;

public class MatchRequestPacket extends Packet {

    private User challenger;

    public MatchRequestPacket() {
        super("MTRQ");
    }

    public MatchRequestPacket(final User challenger) {
        super("MTRQ");
        this.challenger = challenger;
    }

    @Override
    public void receive(PacketUtil input, User user) {
        //Ich habe mir mal die Freiheit genommen..
        if (input.hasPayload()) {
            if (input.getStatusCode() == 80) { //Statuscode 80 = match request
                final String challenging = input.getPayloadString();

                User userToChallenge = ApplicationServer.INSTANCE.getUserByName(challenging);
                if (userToChallenge == null) {
                    setError("User you challenged is not available");
                    return;
                }

                if (ApplicationServer.INSTANCE.isUserInMatch(userToChallenge, user)) {
                    setError("The user is already in an ongoing match");
                    return;
                }
                //At this point we know that the user can be challenged

                final MatchRequestPacket matchRequestPacket = new MatchRequestPacket(user);
                matchRequestPacket.send();
                ApplicationServer.INSTANCE.sendToUser(userToChallenge, matchRequestPacket);
            } else if (input.getStatusCode() == 120) {
                final JSONObject payloadJSON = input.getPayloadJSON();
                final String responseTo = payloadJSON.getString("response_to");
                final int selection = payloadJSON.getInt("selection");

                final User challenger = ApplicationServer.INSTANCE.getUserByName(responseTo);
                if (challenger == null)
                    return;

                if (selection == 0) {
                    //Initiate match
                    //if the user queues for a match, cancel.
                    if (ApplicationServer.INSTANCE.matchQueue.contains(challenger) || ApplicationServer.INSTANCE.matchQueue.contains(user)) {
                        setError("Your custom match has been canceled, as you are currently queueing for a match!");
                        return;
                    }
                    ApplicationServer.INSTANCE.setupMatch(user, challenger); //Match is set up
                } else {
                    ApplicationServer.INSTANCE.sendToUser(challenger, new MatchRequestDeniedPacket());
                }
            }
        }
    }

    @Override
    public void send() {
        this.setPayload(challenger.toJSON());
    }
}
