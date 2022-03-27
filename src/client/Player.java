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

    public static final Player PLAYER = new Player();
    //Management of the Packets
    private final PacketManager packetManager;
    //attributes
    private String name;
    private boolean searchesMatch;
    private int scoreInMatch;

    public Player() {
        super("localhost", 2049);
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

    public String getName() {
        return name;
    }

    public void setName(String pName) {
        this.name = pName;
    }

    public boolean getSearchesMatch() {
        return searchesMatch;
    }

    public void setSearchesMatch(boolean pSearchesMatch) {
        this.searchesMatch = pSearchesMatch;
    }

    public int getScoreInMatch() {
        return scoreInMatch;
    }

    public void setScoreInMatch(int pScoreInMatch) {
        this.scoreInMatch = pScoreInMatch;
    }

    public PacketManager getPacketManager() {
        return packetManager;
    }
}
