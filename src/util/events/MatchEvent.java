package src.util.events;

import src.util.eventapi.events.Event;

public class MatchEvent implements Event {

    private final String winner, looser;
    private final int enemyDecision;

    public MatchEvent(String winner, String looser, int enemyDecision) {
        this.winner = winner;
        this.looser = looser;
        this.enemyDecision = enemyDecision;
    }

    public String getWinner() {
        return winner;
    }

    public String getLooser() {
        return looser;
    }

    public int getEnemyDecision() {
        return enemyDecision;
    }
}
