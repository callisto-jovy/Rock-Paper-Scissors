package src.client;
import src.client.packets.*;
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
    //--> for connection purposes to a Server
    //Management of the Packets
    private final PacketManager packetManager;
    public static final Player PLAYER = new Player();
    private final List<Packet> packetList = new List<>();
    //attributes
    private String name;
    private boolean searchesMatch;
    private int scoreInMatch;

    public Player() {
        super("localhost", 2049);
        packetList.toFirst();
        packetList.append(new AuthPacket());
        packetList.append(new SearchPacket());
        packetList.append(new MatchPacket());
        
        this.packetManager = new PacketManager(packetList);
    }

    @Override
    public void processMessage(String pMessage) {
        LogUtil.getLogger().log(Level.INFO, "Received message as client: " + pMessage);
        
        final Packet packet = packetManager.processMessage(pMessage, null);
        send(PacketFormatter.formatPacket(packet));
    }

    public String getName() {
        return name;
    }

    public void setName(String pName) {
        this.name = pName;
    }

    public void setSearchesMatch(boolean pSearchesMatch) {
        this.searchesMatch = pSearchesMatch;
    }

    public boolean getSearchesMatch() {
        return searchesMatch;
    }

    public void setScoreInMatch(int pScoreInMatch) {
        this.scoreInMatch = pScoreInMatch;
    }

    public int getScoreInMatch() {
        return scoreInMatch;
    }

    public PacketManager getPacketManager() {
        return packetManager;
    }
}
