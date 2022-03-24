package src.server;

public class Match {


    private final User user1, user2;

    /**
     * Constructor for objects of class Match
     */
    public Match(User user1, User user2) {
        this.user1 = user1;
        this.user2 = user2;
    }

    public User getUser1() {
        return user1;
    }

    public User getUser2() {
        return user2;
    }

    public void start() {

    }
}
