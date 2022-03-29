package src.client;

import javax.swing.*;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class GameScreen extends JFrame {

    private JMenuBar menuBar;
    private final JButton BtnPaper;
    private final JButton BtnRock;
    private final JButton BtnScissors;
    private final JLabel LblCountdown;
    private final JLabel LblDoppelrPunkt;
    private final JLabel LblErr;
    private final JLabel LblOppPoints;
    private final JLabel LblOppResult;
    private final JLabel LblSelfPoints;
    private final JLabel LblSelfResult;
    private final JLabel LblUserNameOpp;
    private final JLabel LblUserNameSelf;
    private final JLabel LblProfilePicOpp;
    private final JLabel LblProfilePicSelf;

    ClientLogin invoker;

    //Constructor
    public GameScreen() {

        this.setTitle("Rock Paper Scissors");
        this.setSize(800, 600);
        //menu generate method
        generateMenu();
        this.setJMenuBar(menuBar);

        //pane with null layout
        JPanel contentPane = new JPanel(null);
        contentPane.setPreferredSize(new Dimension(800, 600));
        contentPane.setBackground(new Color(50, 50, 50));


        BtnRock = new JButton(new ImageIcon(getClass().getResource("/src/client/icons/icons-hand-felsen.png")));
        BtnRock.setBounds(208, 480, 64, 64);
        BtnRock.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                invoker.btnClicked(1);
            }
        });
        BtnRock.setBackground(new Color(50, 50, 50));
        BtnRock.setForeground(new Color(0, 0, 0));
        BtnRock.setEnabled(true);
        BtnRock.setFont(new Font("sansserif", 0, 12));
        BtnRock.setText("Rock");
        BtnRock.setVisible(true);

        BtnPaper = new JButton(new ImageIcon(getClass().getResource("/src/client/icons/icons-hand-papier.png")));
        BtnPaper.setBounds(368, 480, 64, 64);
        BtnPaper.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                invoker.btnClicked(2);
            }
        });
        BtnPaper.setBackground(new Color(50, 50, 50));
        BtnPaper.setForeground(new Color(0, 0, 0));
        BtnPaper.setEnabled(true);
        BtnPaper.setFont(new Font("sansserif", 0, 12));
        BtnPaper.setText("Paper");
        BtnPaper.setVisible(true);

        BtnScissors = new JButton(new ImageIcon(getClass().getResource("/src/client/icons/icons-hand-schere.png")));
        BtnScissors.setBounds(528, 480, 64, 64);
        BtnScissors.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                invoker.btnClicked(3);
            }
        });
        BtnScissors.setBackground(new Color(50, 50, 50));
        BtnScissors.setForeground(new Color(0, 0, 0));
        BtnScissors.setEnabled(true);
        BtnScissors.setFont(new Font("sansserif", 0, 12));
        BtnScissors.setText("Scissors");
        BtnScissors.setVisible(true);

        LblCountdown = new JLabel();
        LblCountdown.setBounds(200, 240, 400, 90);
        LblCountdown.setBackground(new Color(214, 217, 223));
        LblCountdown.setForeground(new Color(210, 210, 210));
        LblCountdown.setEnabled(true);
        LblCountdown.setFont(new Font("SansSerif", 1, 70));
        LblCountdown.setText("3");
        LblCountdown.setVisible(true);

        LblDoppelrPunkt = new JLabel();
        LblDoppelrPunkt.setBounds(640, 260, 10, 40);
        LblDoppelrPunkt.setBackground(new Color(214, 217, 223));
        LblDoppelrPunkt.setForeground(new Color(210, 210, 210));
        LblDoppelrPunkt.setEnabled(true);
        LblDoppelrPunkt.setFont(new Font("SansSerif", 1, 24));
        LblDoppelrPunkt.setText(":");
        LblDoppelrPunkt.setVisible(true);

        LblErr = new JLabel();
        LblErr.setBounds(47, 241, 400, 20);
        LblErr.setBackground(new Color(255, 0, 0));
        LblErr.setForeground(new Color(255, 0, 0));
        LblErr.setEnabled(true);
        LblErr.setFont(new Font("sansserif", 0, 12));
        LblErr.setText("Err 404");
        LblErr.setVisible(false);

        LblOppPoints = new JLabel();
        LblOppPoints.setBounds(600, 200, 90, 40);
        LblOppPoints.setBackground(new Color(214, 217, 223));
        LblOppPoints.setForeground(new Color(255, 50, 50));
        LblOppPoints.setEnabled(true);
        LblOppPoints.setFont(new Font("SansSerif", 1, 18));
        LblOppPoints.setText("3");
        LblOppPoints.setVisible(true);

        LblOppResult = new JLabel();
        LblOppResult.setBounds(443, 153, 64, 64);
        LblOppResult.setBackground(new Color(180, 180, 180));
        LblOppResult.setForeground(new Color(140, 140, 140));
        LblOppResult.setEnabled(true);
        LblOppResult.setFont(new Font("SansSerif", 0, 40));
        LblOppResult.setText("?");
        LblOppResult.setVisible(true);

        LblSelfPoints = new JLabel();
        LblSelfPoints.setBounds(680, 320, 90, 40);
        LblSelfPoints.setBackground(new Color(214, 217, 223));
        LblSelfPoints.setForeground(new Color(100, 100, 255));
        LblSelfPoints.setEnabled(true);
        LblSelfPoints.setFont(new Font("SansSerif", 1, 32));
        LblSelfPoints.setText("5");
        LblSelfPoints.setVisible(true);

        LblSelfResult = new JLabel();
        LblSelfResult.setBounds(470, 350, 64, 64);
        LblSelfResult.setBackground(new Color(180, 180, 180));
        LblSelfResult.setForeground(new Color(140, 140, 140));
        LblSelfResult.setEnabled(true);
        LblSelfResult.setFont(new Font("SansSerif", 0, 40));
        LblSelfResult.setText("?");
        LblSelfResult.setVisible(true);

        LblUserNameOpp = new JLabel();
        LblUserNameOpp.setBounds(451, 106, 300, 40);
        LblUserNameOpp.setBackground(new Color(214, 217, 223));
        LblUserNameOpp.setForeground(new Color(255, 50, 50));
        LblUserNameOpp.setEnabled(true);
        LblUserNameOpp.setFont(new Font("SansSerif", 1, 18));
        LblUserNameOpp.setText("Player2");
        LblUserNameOpp.setVisible(true);

        LblUserNameSelf = new JLabel();
        LblUserNameSelf.setBounds(215, 400, 530, 60);
        LblUserNameSelf.setBackground(new Color(214, 217, 223));
        LblUserNameSelf.setForeground(new Color(100, 100, 255));
        LblUserNameSelf.setEnabled(true);
        LblUserNameSelf.setFont(new Font("SansSerif", 1, 32));
        LblUserNameSelf.setText("Player1");
        LblUserNameSelf.setVisible(true);

        LblProfilePicOpp = new JLabel();
        LblProfilePicOpp.setBounds(340, 75, 96, 96);
        LblProfilePicOpp.setBackground(new Color(214, 217, 223));
        LblProfilePicOpp.setEnabled(true);
        LblProfilePicOpp.setVisible(true);

        LblProfilePicSelf = new JLabel();
        LblProfilePicSelf.setBounds(100, 380, 96, 96);
        LblProfilePicSelf.setBackground(new Color(214, 217, 223));
        LblProfilePicSelf.setEnabled(true);
        LblProfilePicSelf.setVisible(true);

        //adding components to contentPane panel
        contentPane.add(BtnPaper);
        contentPane.add(BtnRock);
        contentPane.add(BtnScissors);
        contentPane.add(LblCountdown);
        contentPane.add(LblDoppelrPunkt);
        contentPane.add(LblErr);
        contentPane.add(LblOppPoints);
        contentPane.add(LblOppResult);
        contentPane.add(LblSelfPoints);
        contentPane.add(LblSelfResult);
        contentPane.add(LblUserNameOpp);
        contentPane.add(LblUserNameSelf);
        contentPane.add(LblProfilePicOpp);
        contentPane.add(LblProfilePicSelf);

        //adding panel to JFrame and seting of window position and close operation
        this.add(contentPane);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.pack();
        this.setVisible(true);
    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(NimbusLookAndFeel.class.getCanonicalName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }
        javax.swing.SwingUtilities.invokeLater(GameScreen::new);
    }

    public void setInvoker(ClientLogin pInvoker) {
        invoker = pInvoker;
    }

    //method for generate menu
    public void generateMenu() {
        menuBar = new JMenuBar();
    }

    public void setEnemySelection(int s) { //0: nichts; 1: rock; 2: paper; 3: scissors
        switch (s) {
            case 0:
                LblOppResult.setText("?");
                break;
            case 1:
                LblOppResult.setIcon(new ImageIcon(getClass().getResource("/src/client/icons/icons-hand-felsen.png")));
                break;
            case 2:
                LblOppResult.setIcon(new ImageIcon(getClass().getResource("/src/client/icons/icons-hand-papier.png")));
                break;
            case 3:
                LblOppResult.setIcon(new ImageIcon(getClass().getResource("/src/client/icons/icons-hand-schere.png")));
                break;
        }
    }

    public void setSelfSelection(int s) { //0: nichts; 1: rock; 2: paper; 3: scissors
        switch (s) {
            case 0:
                LblSelfResult.setText("?");
                break;
            case 1:
                LblSelfResult.setIcon(new ImageIcon(getClass().getResource("/src/client/icons/icons-hand-felsen.png")));
                break;
            case 2:
                LblSelfResult.setIcon(new ImageIcon(getClass().getResource("/src/client/icons/icons-hand-papier.png")));
                break;
            case 3:
                LblSelfResult.setIcon(new ImageIcon(getClass().getResource("/src/client/icons/icons-hand-schere.png")));
                break;
        }
    }

    public void setOpponentPoints(int points) {
        LblOppPoints.setText(points + "");
    }

    public void setSelfPoints(int points) {
        LblSelfPoints.setText(points + "");
    }

    public void setCounter(String text) {
        LblCountdown.setText(text);
    }

    public void setUsernameSelf(String name) {
        LblUserNameSelf.setText(name);
    }

    public void setUsernameOpp(String name) {
        LblUserNameOpp.setText(name);
    }

    public void setErrMsg(String errMsg) {
        LblErr.setVisible(true);
        LblErr.setText(errMsg);
        LblErr.setText(errMsg);
    }

    public void hideErrMsg(String errMsg) {
        LblErr.setVisible(false);
    }

    public void setProfilePicSelf(int pPic) {
        if (pPic == 0) {
            LblProfilePicSelf.setText("?");
        } else {
            LblProfilePicSelf.setIcon(new ImageIcon(getClass().getResource("/src/client/icons/96/" + pPic + ".png")));
        }
    }

    public void setProfilePicOpp(int pPic) {
        if (pPic == 0) {
            LblProfilePicSelf.setText("?");
        } else {
            LblProfilePicSelf.setIcon(new ImageIcon(getClass().getResource("/src/client/icons/96/" + pPic + ".png")));
        }
    }
}