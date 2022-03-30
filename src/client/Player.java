package src.client;

import src.client.packets.SearchPacket;
import src.util.LogUtil;
import src.util.PacketFormatter;
import src.util.eventapi.EventManager;
import src.util.eventapi.EventTarget;
import src.util.events.AuthPacketEvent;
import src.util.events.IPErrorEvent;
import src.util.events.MatchFoundEvent;
import src.util.events.UsernameErrorEvent;

import java.util.logging.Level;

public class Player {

    public static final Player INSTANCE = new Player();


    private PlayerClient playerClient;
    private int choice;
    //attributes
    private String name;
    private boolean searchesMatch = true;
    private int scoreInMatch;
    private int profilePic;

    /* ----------Screens---------- */
    private ConnectPage connectPage;
    private GameScreen gameScreen;
    private LoadingScreen loadingScreen;

    public Player() {
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
        this.searchesMatch = false; //Not longer searching

        loadingScreen.setVisible(false);
        loadingScreen.dispose();

        this.gameScreen = new GameScreen();
        gameScreen.setProfilePicSelf(profilePic);
        gameScreen.setProfilePicOpp(event.getEnemyProfilePicture());
        gameScreen.setEnemySelection(-1); //None selected
        gameScreen.setUsernameSelf(getName());
        gameScreen.setUsernameOpp(event.getEnemyName());
        gameScreen.setOpponentPoints(0);
        gameScreen.setSelfPoints(getScoreInMatch());
        this.count();
    }


    public void btnClicked(int pChoice) {
        choice = pChoice;
        gameScreen.setSelfSelection(pChoice);
        gameScreen.setEnemySelection(pChoice);//remove
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

    public int getChoice() {
        return choice;
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
