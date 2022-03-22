package src.server.packets;

import org.json.JSONArray;
import org.json.JSONObject;
import src.server.ApplicationServer;
import src.server.Packet;
import src.server.User;

public class ListPacket extends Packet {

    public ListPacket() {
        super("LIST");
    }

    @Override
    public void receive(JSONObject input, User parent) {
        final JSONArray jsonArray = new JSONArray();

        ApplicationServer.USER_LIST.toFirst();
        while (ApplicationServer.USER_LIST.hasAccess()) {
            final User user = ApplicationServer.USER_LIST.getContent();
            jsonArray.put(user.getName());
            ApplicationServer.USER_LIST.next();
        }
        setPayload(jsonArray);
    }

    @Override
    public void send() {
    }
}
