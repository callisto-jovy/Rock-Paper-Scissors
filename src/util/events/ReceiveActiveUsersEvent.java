package src.util.events;

import org.json.JSONArray;
import src.util.eventapi.events.Event;

public class ReceiveActiveUsersEvent implements Event {

    private final JSONArray userListArray;

    public ReceiveActiveUsersEvent(final JSONArray userListArray) {
        this.userListArray = userListArray;
    }

    public JSONArray getUserListArray() {
        return userListArray;
    }
}
