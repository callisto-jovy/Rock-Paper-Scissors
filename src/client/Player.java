package src.client;

import src.client.packets.MatchPacket;
import src.client.packets.SearchPacket;
import src.util.LogUtil;
import src.util.PacketFormatter;
import src.util.eventapi.EventManager;
import src.util.eventapi.EventTarget;
import src.util.events.*;
import java.awt.*;
import javax.swing.JFileChooser;
import java.io.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileFilter;
import javax.swing.ImageIcon;

import java.util.logging.Level;

public class Player {

    public static final Player INSTANCE = new Player();
    /**
     * The player client to send and receive packets and in order to connect to the host
     */
    private PlayerClient playerClient;


    //attributes
    private String name;
    private boolean searchesMatch = true;
    private int scoreInMatch;
    private int profilePic;
    private ImageIcon profilePicImg;
    private int decision;
    private boolean blockInput;
    
    private String customProfilePicture;

    /* ----------Screens---------- */
    private ConnectPage connectPage;
    private GameScreen gameScreen;
    private SearchingScreen searchingScreen;

    public Player() {
        //TODO: Profile pictures..
        EventManager.register(this); //Register as event receiver
        /*
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            EventManager.unregister(this);
        }));

         */
    }

    public void displayConnectPage() {
        this.connectPage = new ConnectPage(); //New connect page
    }

    public void tryConnect(String ipAddress, String pUsername) {
        LogUtil.getLogger().log(Level.INFO, "Trying to connect to server: " + ipAddress);
        this.playerClient = new PlayerClient(ipAddress);
        this.playerClient.connect(); //Connect to host
        this.name = pUsername;
    }

    @EventTarget
    public void setIPError(final IPErrorEvent event) {
        if (connectPage != null)
            connectPage.SetIPErrVis(true);
    }

    @EventTarget
    public void usernameError(final UsernameErrorEvent event) {
        if (connectPage != null)
            connectPage.SetUsrErrVis(true);
    }


    @EventTarget
    public void startGameScreen(final AuthPacketEvent event) {
        connectPage.setVisible(false);
        connectPage.dispose();

        final SearchPacket searchPacket = new SearchPacket();
        searchPacket.send();
        getPlayer().send(PacketFormatter.formatPacket(searchPacket));

        //Set loading screen
        this.searchingScreen = new SearchingScreen();
        //Loading animation
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @EventTarget
    public void matchFound(final MatchFoundEvent event) {
        this.searchesMatch = false; //No longer searching
        this.scoreInMatch = 0; //Reset

        searchingScreen.setVisible(false);
        searchingScreen.dispose();

        this.gameScreen = new GameScreen();
        //Set profile pictures...
        if(profilePicImg == null) gameScreen.setProfilePicSelf(profilePic);
        else gameScreen.setProfilePicSelf(profilePicImg);
        gameScreen.setProfilePicEnemy(event.getEnemyProfilePicture());
        //Set usernames
        gameScreen.setUsernameEnemy(event.getEnemyName());
        gameScreen.setUsernameSelf(getName());
        //"Clear canvas"
        this.clearGameScreen();
        //Set enemy's points (Set points to 0)
        gameScreen.setEnemyPoints(0);
        gameScreen.setSelfPoints(getScoreInMatch()); //At this point equal to 0
    }


    public void lockInChoice(int pChoice) {
        if (!blockInput) { //Don't do anything if the input is blocked
            this.decision = pChoice;
            //Reset decisions
            this.resetDecisions();


            gameScreen.setSelfSelection(pChoice);
            //Send match packet
            final MatchPacket matchPacket = new MatchPacket();
            matchPacket.send();
            getPlayer().send(PacketFormatter.formatPacket(matchPacket));
            //Block any future input
            blockInput = true;
        }
    }

    @EventTarget
    public void matchStalemate(final MatchStalemateEvent event) {
        this.clearGameScreen();
        gameScreen.setCounter("TIE!");
    }

    @EventTarget
    public void matchRound(final MatchEvent event) {
        gameScreen.setEnemySelection(event.getEnemyDecision());
        if (event.getLooser().equals(name)) {
            gameScreen.setCounter("YOU LOOSE!");
            gameScreen.setEnemyPoints(gameScreen.getEnemyPoints() + 1);
        } else {
            scoreInMatch++;
            gameScreen.setSelfPoints(scoreInMatch);
            gameScreen.setCounter("YOU WIN!");
        }
        this.blockInput = false; //Remove input block (Don't clear the canvas anymore, the enemy's decision shall be visible till the next selection)
    }

    @EventTarget
    public void resultMatch(final ResultEvent event) {
        if (event.getWinner().equals(name)) {
            //gameScreen.setCounter("YOU WON!");
            gameScreen.setVisible(false);
            WinnerScreen win = new WinnerScreen();
        } else {
            //gameScreen.setCounter("YOU LOST!");
            gameScreen.setVisible(false);
            LoserScreen loser = new LoserScreen();
            gameScreen.setEnemyPoints(event.getScore());
        }
        //TODO: Maybe wait for keypress?

        //Remove game screen, connect page
        gameScreen.setVisible(false);
        connectPage.setVisible(true);
    }

    /**
     * Resets the gamescreen to its starting look
     */
    private void clearGameScreen() {
        blockInput = false; //Disable input block
        this.resetDecisions(); // Reset decisions
    }

    private void resetDecisions() {
        gameScreen.setEnemySelection(-1); //Reset decisions
        gameScreen.setSelfSelection(-1);
        gameScreen.setCounter("GO!");
    }
    
    public void chooseProfilePic(){
        JFileChooser chooser = new JFileChooser();
        FileFilter filter = new FileNameExtensionFilter("Bilder","gif", "png", "jpg");
        chooser.setFileFilter(filter);
        chooser.showOpenDialog(null);
        File f = chooser.getSelectedFile();
        String filename = f.getAbsolutePath();
        profilePicImg = new ImageIcon(new ImageIcon("path").getImage().getScaledInstance(96, 96, Image.SCALE_DEFAULT));
       }

    public String getCustomProfilePic() {
        return customProfilePicture;
    }
    
    public void setCustomProfilePicture(final String customBase64) {
        this.customProfilePicture = customBase64;
    }

    public PlayerClient getPlayer() {
        return playerClient;
    }

    public ConnectPage getConnectPage() {
        return connectPage;
    }

    public GameScreen getGameScreen() {
        return gameScreen;
    }

    public int getDecision() {
        return decision;
    }

    public String getName() {
        return name;
    }

    public boolean searchesMatch() {
        return searchesMatch;
    }

    public int getScoreInMatch() {
        return scoreInMatch;
    }

    public int getProfilePic() {
        return profilePic;
    }

    public void setProfilePic(int pPic) {
        this.profilePic = pPic;
    }

    public synchronized void count() {
        gameScreen.setCounter("4");
        gameScreen.setCounter("3");
        gameScreen.setCounter("2");
        gameScreen.setCounter("1");
        gameScreen.setCounter("TIE!");
        gameScreen.setCounter("WIN!");
        gameScreen.setCounter("LOST!");
    }
}
