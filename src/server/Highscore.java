package src.server;

public class Highscore {

    private final String name;
    private int score;

    public Highscore(String name, int score) {
        this.name = name;
        this.score = score;
    }

    public void increaseHighscore() {
        this.score++;
    }

    public void decreaseHighscore() {
        if (score > 0) {
            score--;
        }
    }

    public int getScore() {
        return score;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Highscore{" +
                "name='" + name + '\'' +
                ", score=" + score +
                '}';
    }
}
