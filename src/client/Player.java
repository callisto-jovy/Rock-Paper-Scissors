package src.client;


/**
 * Locale Instanz des Clients
 *
 * @author marcel
 * @version beta
 */
public class Player extends Client {
    //--> for connection purposes to a Server
    private final String clientIP;
    private final int clientPort;
    //Management of the Packets
    private final PacketManager packetManager;
    //attributes
    private String name;
    private boolean searchesMatch;
    private int scoreInMatch;


    public Player() {
        this.packetManager = packetManager;

    }


    @Override
    public void processMessage(String pMessage) {
        
        
        
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
