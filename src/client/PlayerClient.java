package src.client;

import src.client.packets.*;
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
public class PlayerClient extends Client {
    //Management of the Packets
    private final PacketManager packetManager;

    public PlayerClient(final String ipAddress) {
        super(ipAddress, 2049);
        final List<Packet> packetList = new List<>();
        packetList.toFirst();
        packetList.append(new AuthPacket());
        packetList.append(new SearchPacket());
        packetList.append(new MatchPacket());
        packetList.append(new MatchFoundPacket());
        packetList.append(new ListPacket());
        packetList.append(new ResultPacket());
        packetList.append(new MatchStalematePacket());
        this.packetManager = new PacketManager(packetList);
    }

    @Override
    public void processMessage(String pMessage) {
        if (pMessage.isEmpty())
            return;

        LogUtil.getLogger().log(Level.INFO, "Received message as client: " + pMessage);

        final Packet packet = packetManager.processMessage(pMessage, null);
        if (packet != null) {
            send(PacketFormatter.formatPacket(packet));
            //Reset packet (must be done)
            packet.getData().remove("payload");
            packet.getData().remove("error");
        }
    }

    public PacketManager getPacketManager() {
        return packetManager;
    }
}
