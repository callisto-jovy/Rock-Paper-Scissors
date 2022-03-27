package src.server.packets;

import org.json.JSONArray;
import org.json.JSONObject;
import src.server.ApplicationServer;
import src.server.User;
import src.util.Packet;

public class ListPacket extends Packet {

    public ListPacket() {
        super("LIST");
    }

    @Override
    public void receive(JSONObject input, User parent) {
        final JSONArray jsonArray = new JSONArray();

        ApplicationServer.INSTANCE.userList.toFirst();
        while (ApplicationServer.INSTANCE.userList.hasAccess()) {
            final User user = ApplicationServer.INSTANCE.userList.getContent();
            jsonArray.put(user.getName());
            ApplicationServer.INSTANCE.userList.next();
        }
        setPayload(jsonArray);
    }

    @Override
    public void send() {
    }
}
