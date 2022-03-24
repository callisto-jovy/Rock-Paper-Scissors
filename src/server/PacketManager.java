package src.server;

import org.json.JSONObject;
import src.server.packets.AuthPacket;
import src.server.packets.HighscorePacket;
import src.server.packets.ListPacket;
import src.util.List;
import src.util.LogUtil;
import src.util.Packet;

import java.util.logging.Level;

public class PacketManager {

    /*
        List of all packets which may be received
     */
    private final List<Packet> packetList = new List<>();

    public PacketManager() {
        //Add packets
        packetList.toFirst();
        packetList.append(new AuthPacket());
        packetList.append(new ListPacket());
        packetList.append(new HighscorePacket());
    }

    /**
     * Processes an incoming message, for the structure of a packet see @{@link Packet}
     *
     * @param input  Input to "process"
     * @param client User which sent the packet
     * @return Null or a packet which will be formatted and sent back to the user.
     */
    public Packet processMessage(final String input, User client) {
        //Null-case
        if (input == null || input.isEmpty()) {
            return null;
        }

        final JSONObject jsonObject = new JSONObject(input);
        final int statusCode = jsonObject.optInt("status_code");

        //Check statuscode
        if (statusCode == 0) {
            LogUtil.getLogger().log(Level.WARNING, "Packet received with a statuscode of 0");
        } else if (statusCode == 500) {
            LogUtil.getLogger().log(Level.WARNING, "An error occurred with error message: " + jsonObject.optString("error"));
        }
        //Loop through all the available packets and determine whether the received packet is actually valid.
        packetList.toFirst();
        while (packetList.hasAccess()) {
            final Packet packet = packetList.getContent();
            if (packet.getIdentifier().equals(jsonObject.getString("id"))) { //Match identifier
                packet.receive(jsonObject, client); //Call the packet's receive method
                return packet; //Return the packet to later format its data.
            }
            packetList.next();
        }
        return null;
    }

}
