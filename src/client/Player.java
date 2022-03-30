package src.client;

import src.client.packets.MatchPacket;
import src.client.packets.SearchPacket;
import src.util.LogUtil;
import src.util.PacketFormatter;
import src.util.eventapi.EventManager;
import src.util.eventapi.EventTarget;
import src.util.events.*;

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

    /* ----------Screens---------- */
    private ConnectPage connectPage;
    private GameScreen gameScreen;
    private LoadingScreen loadingScreen;

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
        this.loadingScreen = new LoadingScreen();
        //Loading animation
        for (int i = 0; i < 100; i++) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            loadingScreen.setLoadingLength(i);
        }
    }

    @EventTarget
    public void matchFound(final MatchFoundEvent event) {
        this.searchesMatch = false; //No longer searching
        this.scoreInMatch = 0; //Reset

        loadingScreen.setVisible(false);
        loadingScreen.dispose();

        this.gameScreen = new GameScreen();
        gameScreen.setProfilePicSelf(event.getEnemyProfilePicture());
        gameScreen.setEnemySelection(-1); //None selected
        gameScreen.setUsernameSelf(getName());
        gameScreen.setUsernameEnemy(event.getEnemyName());
        gameScreen.setEnemyPoints(0);
        gameScreen.setSelfPoints(getScoreInMatch());
        this.count();
    }


    public void btnClicked(int pChoice) {
        if (!blockInput) {
            this.decision = pChoice;
            gameScreen.setSelfSelection(pChoice);
            //Send match packet
            final MatchPacket matchPacket = new MatchPacket();
            matchPacket.send();
            getPlayer().send(PacketFormatter.formatPacket(matchPacket));
            blockInput = true;
        }
    }

    @EventTarget
    public void matchRound(final MatchEvent event) {
        gameScreen.setEnemySelection(event.getEnemyDecision());
        if (event.getLooser().equals(name)) {
            gameScreen.setEnemyPoints(gameScreen.getEnemyPoints() + 1);
        } else {
            scoreInMatch++;
            gameScreen.setSelfPoints(scoreInMatch);
        }
        //"Clear" canvas
        blockInput = false;
        gameScreen.setEnemySelection(-1);
        gameScreen.setSelfSelection(-1);
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
