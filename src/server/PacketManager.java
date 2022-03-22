package src.server;

import org.json.JSONObject;
import src.server.packets.AuthPacket;
import src.server.packets.ListPacket;
import src.util.List;
import src.util.LogUtil;

import java.util.logging.Level;

public class PacketManager {

    private final List<Packet> packetList = new List<>();

    public PacketManager() {
        //Add packets
        packetList.toFirst();
        packetList.append(new AuthPacket());
        packetList.append(new ListPacket());

    }

    public JSONObject proccessMessage(final String input, User client) {
        if (input == null || input.isEmpty()) {
            return null;
        }

        final JSONObject jsonObject = new JSONObject(input);
        final int statusCode = jsonObject.optInt("statusCode");

        if (statusCode == 0) {
            LogUtil.getLogger().log(Level.WARNING, "Packet received with a statuscode of 0");
        } else if (statusCode == 500) {
            LogUtil.getLogger().log(Level.WARNING, "An error occurred with error message: " + jsonObject.optString("error"));
        }

        packetList.toFirst();
        while (packetList.hasAccess()) {
            final Packet packet = packetList.getContent();
            if (packet.getIdentifier().equals(jsonObject.getString("id"))) {
                packet.receive(jsonObject, client);
                return packet.getBasicData();
            }
            packetList.next();
        }
        return null;
    }

}
