package src.server.packets;

import org.json.JSONObject;
import src.server.ApplicationServer;
import src.server.Match;
import src.server.User;
import src.util.Packet;
import src.util.PacketUtil;

public class MatchRequestPacket extends Packet
{

    private User challenger;
    
    public MatchRequestPacket()
    {
        super("MTRQ");
    }
    
     public MatchRequestPacket(final User challenger)
    {
        super("MTRQ");
        this.challenger = challenger;
    }
    
    @Override
    public void receive(PacketUtil input, User user) {
        //Ich habe mir mal die Freiheit genommen..
        
        if(input.hasPayload()) {
            final String challenging = input.getPayloadString();

            User userToChallenge = ApplicationServer.INSTANCE.getUserByName(challenging);
            if(userToChallenge == null) {
                setError("User you challenged is not available");
                return;
            }

            if(ApplicationServer.INSTANCE.isUserInMatch(userToChallenge, user)) {
                setError("The user is already in an ongoing match");
                return;
            }
            //At this point we know that the user can be challenged
            
            final MatchRequestPacket matchRequestPacket = new MatchRequestPacket(user);
            matchRequestPacket.send();
            ApplicationServer.INSTANCE.sendToUser(userToChallenge, matchRequestPacket);
        }
    }

    @Override 
    public void send() {
        //
        this.setPayload(challenger.toJSON());
    }
}
