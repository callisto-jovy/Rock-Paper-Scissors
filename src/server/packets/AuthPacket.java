package src.server.packets;

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
            final String userName = input.getPayloadString();

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
        }
    }

    @Override
    public void send() {
        setPayload("prompt");
    }
}
