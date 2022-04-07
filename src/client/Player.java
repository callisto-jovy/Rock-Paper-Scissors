package src.client;

import src.client.packets.*;
import src.client.screens.*;
import src.util.ImageUtil;
import src.util.LogUtil;
import src.util.eventapi.EventManager;
import src.util.eventapi.EventTarget;
import src.util.events.*;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;
import java.io.IOException;
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
    private int decision;
    private boolean blockInput;

    private String customProfilePicture;

    /* ----------Screens---------- */
    private ConnectScreen connectPage;
    private GameScreen gameScreen;
    private SearchingScreen searchingScreen;
    private MainMenuScreen mainMenuScreen;

    public Player() {
        EventManager.register(this); //Register as event receiver
        /*
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            EventManager.unregister(this);
        }));

         */
    }

    public void displayConnectPage() {
        this.connectPage = new ConnectScreen(); //New connect page
    }

    public void tryConnect(String ipAddress, String pUsername) {
        if (playerClient != null && playerClient.isConnected()) {
            this.name = pUsername;

            final AuthPacket authPacket = new AuthPacket();
            authPacket.send();
            getPlayer().sendPacket(authPacket);
        } else {
            LogUtil.getLogger().log(Level.INFO, "Trying to connect to server: " + ipAddress);
            this.playerClient = new PlayerClient(ipAddress);
            this.playerClient.connect(); //Connect to host
            this.name = pUsername;
        }
    }

    @EventTarget
    public void startGameScreen(final AuthPacketEvent event) {
        connectPage.setVisible(false);
        connectPage.dispose();
        //Display Main Menu
        this.displayMainMenu();
    }

    public void displayMainMenu() {
        this.mainMenuScreen = new MainMenuScreen();
    }

    @EventTarget
    public void searchMatch(final SearchMatchEvent event) {
        this.searchesMatch = true; //Set the player's status to searching...
        mainMenuScreen.setVisible(false);
        mainMenuScreen.dispose();

        final SearchPacket searchPacket = new SearchPacket();
        searchPacket.send();
        getPlayer().sendPacket(searchPacket);
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
    public void retrieveActiveUsers(final RetrieveActiveUsersEvent event) {
        if (mainMenuScreen != null) {
            final ListPacket listPacket = new ListPacket();
            listPacket.send();
            getPlayer().sendPacket(listPacket);
        }
    }

    @EventTarget
    public void receiveActiveUsers(final ReceiveActiveUsersEvent event) {
        if (mainMenuScreen != null) {
            //This is very bad and inefficient. Too bad...
            mainMenuScreen.getActiveUserList().clear();
            for (int i = 0; i < event.getUserListArray().length(); i++) {
                final String username = event.getUserListArray().getString(i);
                if (!username.equals(getName()))
                    mainMenuScreen.getActiveUserList().addElement(username);
            }
        }
    }

    @EventTarget
    public void retrieveHighscoreList(final RetrieveHighscoreListEvent event) {
        if (mainMenuScreen != null) {
            final HighscorePacket highscorePacket = new HighscorePacket();
            highscorePacket.send();
            getPlayer().sendPacket(highscorePacket);
        }
    }

    @EventTarget
    public void matchFound(final MatchFoundEvent event) {
        this.searchesMatch = false; //No longer searching
        this.scoreInMatch = 0; //Reset

        if (searchingScreen != null) {
            searchingScreen.setVisible(false);
            searchingScreen.dispose();
        }
        //Only needed now because of the match request system.
        if (mainMenuScreen != null && mainMenuScreen.isVisible()) {
            mainMenuScreen.setVisible(false);
            mainMenuScreen.dispose();
        }

        this.gameScreen = new GameScreen();
        //Set profile pictures...
        if (customProfilePicture == null)
            gameScreen.setProfilePicSelf(profilePic);
        else
            gameScreen.setProfilePicSelf(ImageUtil.getImageIcon(customProfilePicture, 96, 96));

        event.getCustomProfilePicture().ifPresentOrElse(
                s -> gameScreen.setProfilePicEnemy(ImageUtil.getImageIcon(s, 96, 96)),
                () -> gameScreen.setProfilePicEnemy(event.getEnemyProfilePicture()));

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
            getPlayer().sendPacket(matchPacket);
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
            new WinnerScreen();
        } else {
            gameScreen.setEnemyPoints(event.getScore());
            new LoserScreen();
        }
        //Remove game screen, connect page
        gameScreen.setVisible(false);
        gameScreen.dispose();
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

    public void chooseProfilePic() {
        final JFileChooser chooser = new JFileChooser();
        final FileFilter filter = new FileNameExtensionFilter("Bilder", "gif", "png", "jpg");
        chooser.setFileFilter(filter);
        chooser.showOpenDialog(null);
        final File chosenFile = chooser.getSelectedFile();

        try {
            this.customProfilePicture = ImageUtil.getImageBase64FromFile(chosenFile);
            this.connectPage.setCustomImageButtonTooltip(chosenFile.getName());
        } catch (IOException e) {
            e.printStackTrace();
            customProfilePicture = null;
        }
    }

    @EventTarget
    public void setIPError(final IPErrorEvent event) {
        if (connectPage != null)
            connectPage.setIPErrVis(true);
    }

    @EventTarget
    public void usernameError(final UsernameErrorEvent event) {
        if (connectPage != null)
            connectPage.setUsrErrVis(true);
    }

    @EventTarget
    public void matchRequest(final MatchRequestEvent event) {

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

    public ConnectScreen getConnectPage() {
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
}
