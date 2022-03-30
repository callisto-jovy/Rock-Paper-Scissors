package src.util.events;

import src.util.eventapi.events.Event;

public class MatchFoundEvent implements Event {

    private final int enemyProfilePicture;
    private final String enemyName;

    public MatchFoundEvent(final String enemyName, final int enemyProfilePicture) {
        this.enemyName = enemyName;
        this.enemyProfilePicture = enemyProfilePicture;
    }

    public int getEnemyProfilePicture() {
        return enemyProfilePicture;
    }

    public String getEnemyName() {
        return enemyName;
    }
}
