package src.client;

public class ClientLogin
{
    ConnectPage c;
    public GameScreen g;
    String username;
    int profilePic = 0;
    int choice;
    int points = 0;

    public ClientLogin(){
        c = new ConnectPage();
        c.setInvoker(this);
    }

    public void tryConnect(String pIPAdress, String pUsername){
        username = pUsername;
        c.SetIPErrVis(true);
        c.SetUsrErrVis(true);
        c.hide();
        startGameScreen();
    }

    void startGameScreen(){
        g = new GameScreen();
        g.setInvoker(this);
        g.setProfilePicSelf(profilePic);
        g.setProfilePicOpp(profilePic+1);
        g.setEnemySelection(0);
        g.setUsernameSelf(username);
        g.setUsernameOpp(username); //remove
        g.setOpponentPoints(points);
        g.setSelfPoints(points);
        count();
    }

    public void btnClicked(int pChoice){
        choice = pChoice;
        g.setSelfSelection(pChoice);
        g.setEnemySelection(pChoice);//remove
    }

    public void setProfilePic(int pPic){
        profilePic = pPic;
    }

    synchronized void count(){
        g.setCounter("4");
        g.setCounter("3");
        g.setCounter("2");
        g.setCounter("1");
        g.setCounter("TIE!");
        g.setCounter("WIN!");
        g.setCounter("LOST!");
    }
}
