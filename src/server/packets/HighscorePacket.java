package src.server.packets;

import org.json.JSONArray;
import src.server.ApplicationServer;
import src.server.Highscore;
import src.server.User;
import src.util.Packet;
import src.util.PacketUtil;

public class HighscorePacket extends Packet {

    public HighscorePacket() {
        super("HLST");
    }


    @Override
    public void receive(PacketUtil input, User parent) {        
        ApplicationServer.INSTANCE.highscoreList.toFirst();
        final JSONArray jsonArray = new JSONArray();

        while (ApplicationServer.INSTANCE.highscoreList.hasAccess()) {
            final Highscore hs = ApplicationServer.INSTANCE.highscoreList.getContent();
            jsonArray.put(hs.getName() + ":" + hs.getScore());

            ApplicationServer.INSTANCE.highscoreList.next();
        }
        setPayload(jsonArray);
    }
    
    @Override
    public void send() {
        
    }


}
