package src.server.packets;

import org.json.JSONObject;
import src.server.ApplicationServer;
import src.server.Highscore;
import src.server.User;
import src.util.Packet;
import src.util.PacketUtil;

public class AuthPacket extends Packet {

    public AuthPacket() {
        super("AUTH");
    }

    @Override
    public void receive(PacketUtil input, User parent) {
        if (input.hasPayload()) {
            final JSONObject payload = input.getPayloadJSON();
            
            final String userName = payload.getString("username");
            final int profilePicture = payload.getInt("profile_picture");
            
            if (userName.isEmpty()) {
                setError("Your username may not be null");
                return;
            }

            ApplicationServer.INSTANCE.highscoreList.toFirst();
            while (ApplicationServer.INSTANCE.highscoreList.hasAccess()) {
                final Highscore highscore = ApplicationServer.INSTANCE.highscoreList.getContent();
                if (highscore.getName().equals(userName)) {
                    setError("Username already on the highscore-list, please try with another one!");
                    return;
                }
                ApplicationServer.INSTANCE.highscoreList.next();
            }

            parent.setName(userName);
            parent.setProfilePicture(profilePicture);
            setPayload("user added");
        }
    }

    @Override
    public void send() {
        setPayload("prompt");
    }
}
