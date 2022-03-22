package src.server;

public class User {

    private final String clientIP;
    private final int clientPort;
    private final PacketManager packetManager;
    private boolean searchesMatch;

    public User(String clientIP, int clientPort, PacketManager packetManager) {
        this.clientIP = clientIP;
        this.clientPort = clientPort;
        this.packetManager = packetManager;
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
