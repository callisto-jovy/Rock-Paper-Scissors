package src.client;

import src.client.packets.*;
import src.util.*;

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
        packetList.append(new MatchRequestPacket());
        packetList.append(new MatchRequestDeniedPacket());
        packetList.append(new HighscorePacket());
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

    public void sendPacket(final Packet packet) {
        this.send(PacketFormatter.formatPacket(packet));
    }

    public PacketManager getPacketManager() {
        return packetManager;
    }
}
