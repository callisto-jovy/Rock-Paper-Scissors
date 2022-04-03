package src.util.events;

import src.util.eventapi.events.Event;

public class UsernameErrorEvent implements Event {

    private final String error;

    public UsernameErrorEvent(String error) {
        this.error = error;
    }

    public String getError() {
        return error;
    }
}
