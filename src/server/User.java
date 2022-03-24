package src.server;

/**
 * Basic User class, in order to manage all the users.
 * This class contains such things as the user's name, ip and port. Further, the class points to the users individual packet manager,
 * in order to reroute the user's packet. Lastly, the class contains a toggle which indicates whether the user is searching for a rock-paper-scissors
 * battle
 *
 * @author Roman
 */
public class User {

    /**
     * The user's ip, pretty self-explanatory
     */
    private final String clientIP;
    /**
     * The user's connected port
     */
    private final int clientPort;
    /**
     * The user's packet manager in order to reroute and assign packets
     */
    private final PacketManager packetManager;
    /**
     * The users name, will be null before the user "authenticates" himself (Can be used as an indicator whether the user is authenticated
     */
    private String name;
    /**
     * Toggle whether the user is searching for a match
     */
    private boolean searchesMatch;

    private int score;

    public User(String clientIP, int clientPort, PacketManager packetManager) {
        this.clientIP = clientIP;
        this.clientPort = clientPort;
        this.packetManager = packetManager;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public PacketManager getPacketManager() {
        return packetManager;
    }

    public boolean searchesMatch() {
        return searchesMatch;
    }

    public void setSearchesMatch(boolean searchesMatch) {
        this.searchesMatch = searchesMatch;
    }

    public int getClientPort() {
        return clientPort;
    }

    public String getClientIP() {
        return clientIP;
    }
}
