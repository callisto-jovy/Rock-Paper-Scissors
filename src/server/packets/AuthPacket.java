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
            final int profilePicture = payload.getInt("profile_picture"); //Int profile picture, with default profiles

            if (userName.isEmpty()) {
                setError("Your username may not be null");
                return;
            }

            ApplicationServer.INSTANCE.userList.toFirst();
            while (ApplicationServer.INSTANCE.userList.hasAccess()) {
                final User user = ApplicationServer.INSTANCE.userList.getContent();
                if (user.getName() != null && user.getName().equals(userName)) {
                    setError("Username already on the user-list, please try with another one!");
                    return;
                }
                ApplicationServer.INSTANCE.userList.next();
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

            if (profilePicture == 0 && payload.has("custom_profile_picture")) { //Custom profile
                final String customProfileBase64 = payload.getString("custom_profile_picture");
                parent.setCustomProfilePicture(customProfileBase64);

            } else {
                parent.setProfilePicture(profilePicture);
            }

            setPayload("user added");
        }
    }

    @Override
    public void send() {
        setPayload("prompt");
    }
}
