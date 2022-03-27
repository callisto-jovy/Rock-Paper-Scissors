package src.server;

import src.server.packets.MatchFoundPacket;
import src.server.packets.MatchPacket;
import src.server.packets.ResultPacket;

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
    private int score1, score2;

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
        //One player has already won, return TODO: Close match 
        if (score1 == 3 || score2 == 3) {
            return;
        }

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

            //Set the winners score
            if (winner == user1) {
                score1++;
            } else if (winner == user2) {
                score2++;
            }

            final User looser = winner == user1 ? user2 : user1;

            //Match done.
            if (score1 + score2 >= 3) {
                //Deduct and increase points
                looser.deductPoints();
                winner.increasePoints();

                final ResultPacket rPacket = new ResultPacket(winner, Math.max(score1, score2));

                ApplicationServer.INSTANCE.sendToUser(user1, rPacket);
                ApplicationServer.INSTANCE.sendToUser(user2, rPacket);
                //Remove match from ongoing matches

                ApplicationServer.INSTANCE.matchList.toFirst();
                while (ApplicationServer.INSTANCE.matchList.hasAccess()) {
                    if (ApplicationServer.INSTANCE.matchList.getContent() == this) {
                        ApplicationServer.INSTANCE.matchList.remove();
                        return;
                    }
                    ApplicationServer.INSTANCE.matchList.next();
                }


                //TODO: Set highscore


            } else {
                //TODO: Send packet
                final MatchPacket mPacketUser1 = new MatchPacket(winner, looser, decision2);
                final MatchPacket mPacketUser2 = new MatchPacket(winner, looser, decision1);

                ApplicationServer.INSTANCE.sendToUser(user1, mPacketUser1);
                ApplicationServer.INSTANCE.sendToUser(user2, mPacketUser2);

                //Reset decisions
                decision1 = -1;
                decision2 = -1;
            }
        }
    }

    public void start() {
        final MatchFoundPacket matchFoundPacket = new MatchFoundPacket();
        //Send a packet which indicates that a match has been found to both users.
        ApplicationServer.INSTANCE.sendToUser(user1, matchFoundPacket);
        ApplicationServer.INSTANCE.sendToUser(user2, matchFoundPacket);
    }
}
