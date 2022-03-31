package src.server;

public class Highscore {

    private final String name;
    private int score;

    public Highscore(String name, int score) {
        this.name = name;
        this.score = score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getScore() {
        return score;
    }

    public String getName() {
        return name;
    }
}
