package src.util.events;

import src.util.eventapi.events.Event;
import java.util.*;


public class MatchFoundEvent implements Event {

    private final int enemyProfilePicture;
    private final String enemyName;
    private final String enemyCustomProfilePictureBase64String;
    

    public MatchFoundEvent(final String enemyName, final int enemyProfilePicture, final String enemyCustomProfilePictureBase64String) {
        this.enemyName = enemyName;
        this.enemyProfilePicture = enemyProfilePicture;
        this.enemyCustomProfilePictureBase64String = enemyCustomProfilePictureBase64String;
    }

    public int getEnemyProfilePicture() {
        return enemyProfilePicture;
    }

    public String getEnemyName() {
        return enemyName;
    }
    
    public Optional<String> getCustomProfilePicture() {
        return Optional.ofNullable(enemyCustomProfilePictureBase64String);
    }
}
