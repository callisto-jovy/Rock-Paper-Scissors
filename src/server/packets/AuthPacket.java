package src.server.packets;

import org.json.JSONObject;
import src.server.ApplicationServer;
import src.server.Highscore;
import src.server.Packet;
import src.server.User;

public class AuthPacket extends Packet {

    public AuthPacket() {
        super("AUTH");
    }

    @Override
    public void receive(JSONObject input, User parent) {
        final String userName = input.getString("payload");

        if (userName.isEmpty()) {
            setError("Your username may not be null");
            return;
        }

        ApplicationServer.HIGHSCORE_LIST.toFirst();
        while (ApplicationServer.HIGHSCORE_LIST.hasAccess()) {
            final Highscore highscore = ApplicationServer.HIGHSCORE_LIST.getContent();
            if (highscore.getName().equals(userName)) {
                setError("Username already on the highscore-list, please try with another one!");
                return;
            }
            ApplicationServer.HIGHSCORE_LIST.next();
        }

        parent.setName(userName);
    }

    @Override
    public void send() {
        setPayload("Request Username");
    }
}
