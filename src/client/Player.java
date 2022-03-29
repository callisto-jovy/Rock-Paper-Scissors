package src.client;

import src.client.packets.AuthPacket;
import src.client.packets.MatchPacket;
import src.client.packets.SearchPacket;
import src.server.PacketManager;
import src.util.List;
import src.util.LogUtil;
import src.util.Packet;
import src.util.PacketFormatter;

import java.util.logging.Level;

/**
 * Locale Instanz des Clients
 *
 * @author marcel
 * @version beta
 */
public class Player extends Client {
    //Management of the Packets
    private final PacketManager packetManager;

    public Player(final String ipAddress) {
        super(ipAddress, 2049);
        final List<Packet> packetList = new List<>();
        packetList.toFirst();
        packetList.append(new AuthPacket());
        packetList.append(new SearchPacket());
        packetList.append(new MatchPacket());
        this.packetManager = new PacketManager(packetList);
    }

    @Override
    public void processMessage(String pMessage) {
        if (pMessage.isEmpty())
            return;

        LogUtil.getLogger().log(Level.INFO, "Received message as client: " + pMessage);

        final Packet packet = packetManager.processMessage(pMessage, null);
        if (packet != null)
            send(PacketFormatter.formatPacket(packet));
    }

    public PacketManager getPacketManager() {
        return packetManager;
    }
}
