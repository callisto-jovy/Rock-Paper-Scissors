package src.client;

public class ClientLogin {

    private final ConnectPage connectPage;
    public GameScreen gameScreen;
    private Player player;
    private int choice;
    //attributes
    private String name;
    private boolean searchesMatch;
    private int scoreInMatch;
    private int profilePic;

    public ClientLogin() {
        this.connectPage = new ConnectPage();
        connectPage.setInvoker(this);
    }

    public void tryConnect(String ipAddress, String pUsername) {
        this.player = new Player(ipAddress);
        this.name = pUsername;

        connectPage.SetIPErrVis(true);
        connectPage.SetUsrErrVis(true);
        connectPage.hide();

        startGameScreen();
    }

    public void startGameScreen() {
        gameScreen = new GameScreen();
        gameScreen.setInvoker(this);
        gameScreen.setProfilePicSelf(profilePic);
        gameScreen.setProfilePicOpp(profilePic + 1);
        gameScreen.setEnemySelection(0);
        gameScreen.setUsernameSelf(getName());
        gameScreen.setUsernameOpp(getName()); //remove
        gameScreen.setOpponentPoints(getScoreInMatch());
        gameScreen.setSelfPoints(getScoreInMatch());
        count();
    }

    public void btnClicked(int pChoice) {
        choice = pChoice;
        gameScreen.setSelfSelection(pChoice);
        gameScreen.setEnemySelection(pChoice);//remove
    }

    public Player getPlayer() {
        return player;
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

    public boolean isSearchesMatch() {
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

    synchronized void count() {
        gameScreen.setCounter("4");
        gameScreen.setCounter("3");
        gameScreen.setCounter("2");
        gameScreen.setCounter("1");
        gameScreen.setCounter("TIE!");
        gameScreen.setCounter("WIN!");
        gameScreen.setCounter("LOST!");
    }
}
