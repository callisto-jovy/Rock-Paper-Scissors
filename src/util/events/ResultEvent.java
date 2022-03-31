package src.util.events;

import src.util.eventapi.events.Event;

public class ResultEvent implements Event {

    private final String winner;
    private final int score;

    public ResultEvent(final String winner, final int score) {
        this.winner = winner;
        this.score = score;
    }

    public String getWinner() {
        return winner;
    }

    public int getScore() {
        return score;
    }
}
