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
        //try{wait(1000);} catch(Exception e){}
        c.SetIPErrVis(true);
        //try{wait(1000);} catch(Exception e){}
        c.SetUsrErrVis(true);
        //try{wait(1000);} catch(Exception e){}
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
        //while(true){
            g.setCounter("4");
            //try{wait(1000);} catch(Exception e){}
            g.setCounter("3");
            //try{wait(1000);} catch(Exception e){}
            g.setCounter("2");
            //try{wait(1000);} catch(Exception e){}
            g.setCounter("1");
            //try{wait(1000);} catch(Exception e){}
            g.setCounter("TIE!");
            //try{wait(1000);} catch(Exception e){}
            g.setCounter("WIN!");
            //try{wait(1000);} catch(Exception e){}
            g.setCounter("LOST!");
            //try{wait(1000);} catch(Exception e){}
        //}
    }
}
