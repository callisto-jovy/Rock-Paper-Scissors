package src.server;

import org.json.JSONObject;

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
     * The user's profilePicture (used if the user did not provide a custom profile picture)
     */
    private int profilePicture;

    /**
     * Base64 String with the user's custom profile picture (null if none is given)
     */
    private String customPicture;

    public User(String clientIP, int clientPort) {
        this.clientIP = clientIP;
        this.clientPort = clientPort;
    }

    public JSONObject toJSON() {
        final JSONObject object = new JSONObject();

        object
                .put("username", name)
                .put("profile_picture", profilePicture);

        if (customPicture != null)
            object.put("custom_profile_picture", customPicture);

        return object;
    }

    /*
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return clientPort == user.clientPort && clientIP.equals(user.clientIP) && name.equals(user.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(clientIP, clientPort, name);
    }

     */

    public void setCustomProfilePicture(final String base64) {
        this.customPicture = base64;
    }

    public void setProfilePicture(final int picture) {
        this.profilePicture = picture;
    }

    public int getProfilePicture() {
        return profilePicture;
    }

    public String getCustomProfilePictureBase64String() {
        return this.customPicture;
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
