package src.client;


/**
 * Locale Instanz des Clients
 *
 * @marcel
 * @version beta
 */
public class Player extends Client
{
    
    private final String clientIP; 
    private final int clientPort;
    private final PacketManager packetManager;
    private String name;
    private boolean searchesMatch;
    private int scoreInMatch;
    
    
    
    public Player() {
        this.packetManager = packetManager;
    }
    
    public void setName(String pName) {
        this.name=pName;
    }
    
    public String getName() {
        return name; 
    }
    
    public PacketManager getPacketManager() {
        return packetManager; 
    }
    
    public void setSearchesMatch(boolean searchesMatch) {
        this.searchesMatch = searchesMatch;
    }
    
    
}
