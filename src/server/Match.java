package src.server;

import src.server.packets.MatchFoundPacket;
import src.util.PacketFormatter;

public class Match {

    private final User user1, user2;
    private User winner;
    /*
    Every decision is represented with a number:
    Rock = 0,
    Paper = 1,
    Scissors = 2.
     */
    private int decision1 = -1, decision2 = -1;

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

    public void setDecision1(int decision1) {
        this.decision1 = decision1;
    }

    public void setDecision2(int decision2) {
        this.decision2 = decision2;
    }

    public void decideWinner() {
        if (decision1 != -1 && decision2 != -1) {
            //Stone beats scissors (user 1)
            if (decision1 == 0 && decision2 == 2) {
                winner = user1;
                //Stone beats scissors (user 2)
            } else if (decision1 == 2 && decision2 == 0) {
                winner = user2;
                //Paper beats rock (User 1)
            } else if (decision1 == 1 && decision2 == 0) {
                winner = user1;
                //Paper beats rock (User 2)
            } else if (decision1 == 0 && decision2 == 1) {
                winner = user2;
                //Scissor beats paper (User 1)
            } else if (decision1 == 2 && decision2 == 1) {
                winner = user1;
                //Scissor beats paper (User 2)
            } else if (decision1 == 1 && decision2 == 2) {
                winner = user2;
            }
            //Increase the winners current score
            winner.setCurrentScore(winner.getCurrentScore() + 1);

            //TODO: Send packet


            //Reset decisions
            decision1 = -1;
            decision2 = -1;
        }
    }

    public void start() {
        final MatchFoundPacket matchFoundPacket = new MatchFoundPacket();
        //Send a packet which indicates that a match has been found to both users.
        ApplicationServer.INSTANCE.send(user1.getClientIP(), user1.getClientPort(), PacketFormatter.formatPacket(matchFoundPacket));
        ApplicationServer.INSTANCE.send(user2.getClientIP(), user2.getClientPort(), PacketFormatter.formatPacket(matchFoundPacket));
    }
}
