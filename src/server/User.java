package src.server;
import org.json.*;
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
     * The user's name, will be null before the user "authenticates" himself (Can be used as an indicator whether the user is authenticated
     */
    private String name;
    /**
     * Toggle whether the user is searching for a match
     */
    private boolean searchesMatch;
    /**
     * The user's total score
     */
    private int totalScore;
    /**
     * The user's profilePicture 
     */
    private int profilePicture;
    
    public User(String clientIP, int clientPort) {
        this.clientIP = clientIP;
        this.clientPort = clientPort;
    }
    
    public JSONObject toJSON() {
        return new JSONObject()
        .put("username", name)
        .put("profile_picture", profilePicture);
    }
    
    public void setProfilePicture(final int picture) {
        this.profilePicture = picture;
    }
    
    public int getProfilePicture() {
        return profilePicture;
    }
    
    public int getScore() {
        return totalScore;
    }

    public void increasePoints() {
        this.totalScore++;
    }

    public void deductPoints() {
        if (this.totalScore > 0) {
            this.totalScore--;
        }

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
