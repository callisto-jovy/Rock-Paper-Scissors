package src.client;

import javax.swing.*;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class GameScreen extends JFrame {

    private final JLabel LblCountdown;
    private final JLabel LblErr;
    private final JLabel LblEnemyPoints;
    private final JLabel LblOppResult;
    private final JLabel LblSelfPoints;
    private final JLabel LblSelfResult;
    private final JLabel LblUserNameOpp;
    private final JLabel LblUserNameSelf;
    private final JLabel LblProfilePicSelf;

    private int enemyPoints;


    //Constructor
    public GameScreen() {
        this.setTitle("Rock Paper Scissors");
        this.setSize(800, 600);
        this.setResizable(false);
        //pane with null layout
        final JPanel contentPane = new JPanel(null);
        contentPane.setPreferredSize(new Dimension(800, 600));
        contentPane.setBackground(new Color(50, 50, 50));

        final String[] strings = {"Rock", "Paper", "Scissors"};

        int xOffset = 0;
        for (int i = 0; i < strings.length; i++) {
            final JButton btnRock = new JButton(new ImageIcon(getClass().getResource("/src/client/icons/selection/" + i + ".png")));

            btnRock.setBounds(208 + xOffset, 480, 64, 64);
            int finalI = i;
            btnRock.addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent evt) {
                    Player.INSTANCE.lockInChoice(finalI);
                }
            });
            btnRock.setBackground(new Color(50, 50, 50));
            btnRock.setForeground(new Color(0, 0, 0));
            btnRock.setEnabled(true);
            btnRock.setFont(new Font("sansserif", Font.PLAIN, 12));
            btnRock.setText(strings[i]);
            btnRock.setVisible(true);

            contentPane.add(btnRock);
            xOffset += 180;
        }

        LblCountdown = new JLabel();
        LblCountdown.setBounds(200, 240, 500, 90);
        LblCountdown.setBackground(new Color(214, 217, 223));
        LblCountdown.setForeground(new Color(210, 210, 210));
        LblCountdown.setEnabled(true);
        LblCountdown.setFont(new Font("SansSerif", Font.BOLD, 70));
        LblCountdown.setText("3");
        LblCountdown.setVisible(true);

        final JLabel lblDoppelrPunkt = new JLabel();
        lblDoppelrPunkt.setBounds(640, 260, 10, 40);
        lblDoppelrPunkt.setBackground(new Color(214, 217, 223));
        lblDoppelrPunkt.setForeground(new Color(210, 210, 210));
        lblDoppelrPunkt.setEnabled(true);
        lblDoppelrPunkt.setFont(new Font("SansSerif", Font.BOLD, 24));
        lblDoppelrPunkt.setText(":");
        lblDoppelrPunkt.setVisible(true);

        LblErr = new JLabel();
        LblErr.setBounds(47, 241, 400, 20);
        LblErr.setBackground(new Color(255, 0, 0));
        LblErr.setForeground(new Color(255, 0, 0));
        LblErr.setEnabled(true);
        LblErr.setFont(new Font("sansserif", Font.PLAIN, 12));
        LblErr.setText("Err 404");
        LblErr.setVisible(false);

        LblEnemyPoints = new JLabel();
        LblEnemyPoints.setBounds(600, 200, 90, 40);
        LblEnemyPoints.setBackground(new Color(214, 217, 223));
        LblEnemyPoints.setForeground(new Color(255, 50, 50));
        LblEnemyPoints.setEnabled(true);
        LblEnemyPoints.setFont(new Font("SansSerif", Font.BOLD, 18));
        LblEnemyPoints.setText("");
        LblEnemyPoints.setVisible(true);

        LblOppResult = new JLabel();
        LblOppResult.setBounds(443, 153, 64, 64);
        LblOppResult.setBackground(new Color(180, 180, 180));
        LblOppResult.setForeground(new Color(140, 140, 140));
        LblOppResult.setEnabled(true);
        LblOppResult.setFont(new Font("SansSerif", Font.PLAIN, 40));
        LblOppResult.setText("");
        LblOppResult.setVisible(true);

        LblSelfPoints = new JLabel();
        LblSelfPoints.setBounds(680, 320, 90, 40);
        LblSelfPoints.setBackground(new Color(214, 217, 223));
        LblSelfPoints.setForeground(new Color(100, 100, 255));
        LblSelfPoints.setEnabled(true);
        LblSelfPoints.setFont(new Font("SansSerif", Font.BOLD, 32));
        LblSelfPoints.setText("");
        LblSelfPoints.setVisible(true);

        LblSelfResult = new JLabel();
        LblSelfResult.setBounds(470, 350, 64, 64);
        LblSelfResult.setBackground(new Color(180, 180, 180));
        LblSelfResult.setForeground(new Color(140, 140, 140));
        LblSelfResult.setEnabled(true);
        LblSelfResult.setFont(new Font("SansSerif", Font.PLAIN, 40));
        LblSelfResult.setText("");
        LblSelfResult.setVisible(true);

        LblUserNameOpp = new JLabel();
        LblUserNameOpp.setBounds(451, 106, 300, 40);
        LblUserNameOpp.setBackground(new Color(214, 217, 223));
        LblUserNameOpp.setForeground(new Color(255, 50, 50));
        LblUserNameOpp.setEnabled(true);
        LblUserNameOpp.setFont(new Font("SansSerif", Font.BOLD, 18));
        LblUserNameOpp.setText("Player2");
        LblUserNameOpp.setVisible(true);

        LblUserNameSelf = new JLabel();
        LblUserNameSelf.setBounds(215, 400, 530, 60);
        LblUserNameSelf.setBackground(new Color(214, 217, 223));
        LblUserNameSelf.setForeground(new Color(100, 100, 255));
        LblUserNameSelf.setEnabled(true);
        LblUserNameSelf.setFont(new Font("SansSerif", Font.BOLD, 32));
        LblUserNameSelf.setText("Player1");
        LblUserNameSelf.setVisible(true);

        final JLabel lblProfilePicOpp = new JLabel();
        lblProfilePicOpp.setBounds(340, 75, 96, 96);
        lblProfilePicOpp.setBackground(new Color(214, 217, 223));
        lblProfilePicOpp.setEnabled(true);
        lblProfilePicOpp.setVisible(true);

        LblProfilePicSelf = new JLabel();
        LblProfilePicSelf.setBounds(100, 380, 96, 96);
        LblProfilePicSelf.setBackground(new Color(214, 217, 223));
        LblProfilePicSelf.setEnabled(true);
        LblProfilePicSelf.setVisible(true);

        //adding components to contentPane panel
        contentPane.add(LblCountdown);
        contentPane.add(lblDoppelrPunkt);
        contentPane.add(LblErr);
        contentPane.add(LblEnemyPoints);
        contentPane.add(LblOppResult);
        contentPane.add(LblSelfPoints);
        contentPane.add(LblSelfResult);
        contentPane.add(LblUserNameOpp);
        contentPane.add(LblUserNameSelf);
        contentPane.add(lblProfilePicOpp);
        contentPane.add(LblProfilePicSelf);

        //adding panel to JFrame and setting of window position and close operation
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

    public void setEnemySelection(int selection) { //-1: nichts; 0: rock; 1: paper; 2: scissors
        if (selection == -1) {
            LblOppResult.setText("?");
            LblOppResult.setIcon(null);// Reset icon
        } else {
            LblOppResult.setIcon(new ImageIcon(getClass().getResource("/src/client/icons/selection/" + selection + ".png")));
        }
    }

    public void setSelfSelection(int selection) { //-1: nichts; 1: rock; 2: paper; 3: scissors
        if (selection == -1) {
            LblSelfResult.setText("?");
            LblSelfResult.setIcon(null); //Reset icon
        } else {
            LblSelfResult.setIcon(new ImageIcon(getClass().getResource("/src/client/icons/selection/" + selection + ".png")));
        }
    }

    public int getEnemyPoints() {
        return enemyPoints;
    }

    public void setEnemyPoints(int points) {
        this.enemyPoints = points;
        LblEnemyPoints.setText(points + "");
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

    public void setUsernameEnemy(String name) {
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
}