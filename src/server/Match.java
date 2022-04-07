package src.server;

import src.server.packets.MatchFoundPacket;
import src.server.packets.MatchPacket;
import src.server.packets.MatchStalematePacket;
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

        user1.setSearchesMatch(false);
        user2.setSearchesMatch(false);
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
    
    public boolean containsUser(final User user) {
        return getUser1() == user || getUser2() == user;
    }

    public void decideWinner() {
        //One player has already won, return
        if (checkWinner()) {
            return;
        }

        if (decision1 != -1 && decision2 != -1) {
            //Stalemate
            if (decision1 == decision2) {
                final MatchStalematePacket stalematePacket = new MatchStalematePacket();
                stalematePacket.send();

                ApplicationServer.INSTANCE.sendToUser(user1, stalematePacket);
                ApplicationServer.INSTANCE.sendToUser(user2, stalematePacket);
                this.resetDecisions();
                return;
            }
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
            if (!checkWinner())
                this.closeRound(looser);
        }
    }

    private boolean checkWinner() {
        //One player has already won, return
        if ((score1 == 3 || score2 == 3) && (score1 != score2)) {
            final User looser = score1 > score2 ? user2 : user1;
            this.matchDone(looser);
            return true;
        }
        return false;
    }

    private void closeRound(final User looser) {
        final MatchPacket mPacketUser1 = new MatchPacket(winner, looser, decision2);
        final MatchPacket mPacketUser2 = new MatchPacket(winner, looser, decision1);

        ApplicationServer.INSTANCE.sendToUser(user1, mPacketUser1);
        ApplicationServer.INSTANCE.sendToUser(user2, mPacketUser2);

        this.resetDecisions();
    }

    private void resetDecisions() {
        //Reset decisions
        this.decision1 = -1;
        this.decision2 = -1;
    }

    private void matchDone(final User looser) {
        //Deduct and increase points
        //looser.deductPoints();
        winner.increasePoints();

        final ResultPacket resultPacket = new ResultPacket(winner, Math.max(score1, score2));
        ApplicationServer.INSTANCE.sendToUser(user1, resultPacket);
        ApplicationServer.INSTANCE.sendToUser(user2, resultPacket);

        //Remove match from ongoing matches
        ApplicationServer.INSTANCE.matchList.toFirst();
        while (ApplicationServer.INSTANCE.matchList.hasAccess()) {
            if (ApplicationServer.INSTANCE.matchList.getContent() == this) {
                ApplicationServer.INSTANCE.matchList.remove();
                break;
            }
            ApplicationServer.INSTANCE.matchList.next();
        }
        //Highscore list
        ApplicationServer.INSTANCE.highscoreList.toFirst();
        while (ApplicationServer.INSTANCE.highscoreList.hasAccess()) {
            //If user is already in the list:
            final Highscore hs = ApplicationServer.INSTANCE.highscoreList.getContent();
            if (hs.getName().equals(winner.getName())) {
                hs.setScore(winner.getScore());
                return;
            } 
            
            /*
            else if(hs.getName().equals(looser.getName())) {
                hs.setScore(looser.getScore());
            }
            */
            ApplicationServer.INSTANCE.highscoreList.next();
        }
        
        
        System.out.println("Adding user to highscore list");
        //User not in high score list
        ApplicationServer.INSTANCE.highscoreList.toFirst();
        ApplicationServer.INSTANCE.highscoreList.append(new Highscore(winner.getName(), 1));
    }

    public void start() {
        //Send a packet which indicates that a match has been found to both users.
        ApplicationServer.INSTANCE.sendToUser(user1, new MatchFoundPacket(user2));
        ApplicationServer.INSTANCE.sendToUser(user2, new MatchFoundPacket(user1));
    }

    @Override
    public String toString() {
        return "Match{" +
                "user1=" + user1 +
                ", user2=" + user2 +
                ", winner=" + winner +
                ", decision1=" + decision1 +
                ", decision2=" + decision2 +
                ", score1=" + score1 +
                ", score2=" + score2 +
                '}';
    }
}
