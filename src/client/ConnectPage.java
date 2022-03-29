package src.client;

/**
 *Text genereted by Simple GUI Extension for BlueJ
 */
import javax.swing.UIManager.LookAndFeelInfo;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import javax.swing.border.Border;
import javax.swing.*;

public class ConnectPage extends JFrame {
    private JMenuBar menuBar;
    private JButton BtnConnect;
    private JLabel LblErrMsgUSR;
    private JLabel LblErrorIP;
    private JTextField TFIPAdress;
    private JLabel label1;
    private JLabel label2;
    private JTextField TFUserName;

    private JButton BtnIcon1;
    private JButton BtnIcon2;
    private JButton BtnIcon3;
    private JButton BtnIcon4;
    private JButton BtnIcon5;
    private JButton BtnIcon6;
    private JButton BtnIcon7;
    private JButton BtnIcon8;

    ClientLogin invoker;
    public void setInvoker(ClientLogin pInvoker){
        invoker = pInvoker;
    }
    //Constructor 
    public ConnectPage(){

        this.setTitle("Connect to Server");
        this.setSize(700,450);
        //menu generate method
        //generateMenu();
        this.setJMenuBar(menuBar);

        //pane with null layout
        JPanel contentPane = new JPanel(null);
        contentPane.setPreferredSize(new Dimension(700,450));
        contentPane.setBackground(new Color(50,50,50));

        BtnConnect = new JButton();
        BtnConnect.setBounds(187,280,120,40);
        BtnConnect.setBackground(new Color(70,70,70));
        BtnConnect.setForeground(new Color(210,210,210));
        BtnConnect.setEnabled(true);
        BtnConnect.setFont(new Font("sansserif",0,12));
        BtnConnect.setText("Connect");
        BtnConnect.setVisible(true);
        BtnConnect.addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent evt) {
                    invoker.tryConnect(TFIPAdress.getText(), TFUserName.getText()); 
                }  
            });

        LblErrMsgUSR = new JLabel();
        LblErrMsgUSR.setBounds(210,225,190,20);
        LblErrMsgUSR.setBackground(new Color(214,217,223));
        LblErrMsgUSR.setForeground(new Color(255,0,0));
        LblErrMsgUSR.setEnabled(true);
        LblErrMsgUSR.setFont(new Font("sansserif",0,12));
        LblErrMsgUSR.setText("Username already exists!");
        LblErrMsgUSR.setVisible(false);

        LblErrorIP = new JLabel();
        LblErrorIP.setBounds(210,210,190,20);
        LblErrorIP.setBackground(new Color(214,217,223));
        LblErrorIP.setForeground(new Color(255,0,0));
        LblErrorIP.setEnabled(true);
        LblErrorIP.setFont(new Font("sansserif",0,12));
        LblErrorIP.setText("IP-Adress incorrect!");
        LblErrorIP.setVisible(false);

        TFIPAdress = new JTextField();
        TFIPAdress.setBounds(200,120,200,40);
        TFIPAdress.setBackground(new Color(70,70,70));
        TFIPAdress.setForeground(new Color(210,210,210));
        TFIPAdress.setEnabled(true);
        TFIPAdress.setFont(new Font("sansserif",0,12));
        TFIPAdress.setText("10.147.48.");
        TFIPAdress.setVisible(true);

        label1 = new JLabel();
        label1.setBounds(90,120,100,40);
        label1.setBackground(new Color(255,255,255));
        label1.setForeground(new Color(210,210,210));
        label1.setEnabled(true);
        label1.setFont(new Font("SansSerif",0,12));
        label1.setText("Enter Server IP:");
        label1.setVisible(true);

        label2 = new JLabel();
        label2.setBounds(90,170,100,40);
        label2.setBackground(new Color(214,217,223));
        label2.setForeground(new Color(210,210,210));
        label2.setEnabled(true);
        label2.setFont(new Font("sansserif",0,12));
        label2.setText("Enter Username:");
        label2.setVisible(true);

        TFUserName = new JTextField();
        TFUserName.setBounds(200,170,200,40);
        TFUserName.setBackground(new Color(70,70,70));
        TFUserName.setForeground(new Color(210,210,210));
        TFUserName.setEnabled(true);
        TFUserName.setFont(new Font("sansserif",0,12));
        TFUserName.setText("Player1");
        TFUserName.setVisible(true);

        BtnIcon1 = new JButton(new ImageIcon(getClass().getResource("Icons/fortnite-llama-48.png")));
        BtnIcon1.setBounds(476,50,48,48);
        BtnIcon1.setBackground(new Color(70,70,70));
        BtnIcon1.addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent evt) {
                    invoker.setProfilePic(1); 
                }  
            });
        BtnIcon2 = new JButton(new ImageIcon(getClass().getResource("Icons/batman-48.png")));
        BtnIcon2.setBounds(576,50,48,48);
        BtnIcon2.setBackground(new Color(70,70,70));
        BtnIcon2.addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent evt) {
                    invoker.setProfilePic(2);
                }  
            });
        BtnIcon3 = new JButton(new ImageIcon(getClass().getResource("Icons/naruto-48.png")));
        BtnIcon3.setBounds(476,150,48,48);
        BtnIcon3.setBackground(new Color(70,70,70));
        BtnIcon3.addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent evt) {
                    invoker.setProfilePic(3);
                }  
            });
        BtnIcon4 = new JButton(new ImageIcon(getClass().getResource("Icons/thanos-48.png")));
        BtnIcon4.setBounds(576,150,48,48);
        BtnIcon4.setBackground(new Color(70,70,70));
        BtnIcon4.addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent evt) {
                    invoker.setProfilePic(4);
                }  
            });
        BtnIcon5 = new JButton(new ImageIcon(getClass().getResource("Icons/darth-vader-48.png")));
        BtnIcon5.setBounds(476,250,48,48);
        BtnIcon5.setBackground(new Color(70,70,70));
        BtnIcon5.addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent evt) {
                    invoker.setProfilePic(5);
                }  
            });
        BtnIcon6 = new JButton(new ImageIcon(getClass().getResource("Icons/super-mario-48.png")));
        BtnIcon6.setBounds(576,250,48,48);
        BtnIcon6.setBackground(new Color(70,70,70));
        BtnIcon6.addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent evt) {
                    invoker.setProfilePic(6);
                }  
            });
        BtnIcon7 = new JButton(new ImageIcon(getClass().getResource("Icons/eric-cartman-48.png")));
        BtnIcon7.setBounds(476,350,48,48);
        BtnIcon7.setBackground(new Color(70,70,70));
        BtnIcon7.addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent evt) {
                    invoker.setProfilePic(7);
                }  
            });
        BtnIcon8 = new JButton(new ImageIcon(getClass().getResource("Icons/john-wick-48.png")));
        BtnIcon8.setBounds(576,350,48,48);
        BtnIcon8.setBackground(new Color(70,70,70));
        BtnIcon8.addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent evt) {
                    invoker.setProfilePic(8);
                }  
            });

        //adding components to contentPane panel
        contentPane.add(BtnConnect);
        contentPane.add(LblErrMsgUSR);
        contentPane.add(LblErrorIP);
        contentPane.add(TFIPAdress);
        contentPane.add(label1);
        contentPane.add(label2);
        contentPane.add(TFUserName);

        contentPane.add(BtnIcon1);
        contentPane.add(BtnIcon2);
        contentPane.add(BtnIcon3);
        contentPane.add(BtnIcon4);
        contentPane.add(BtnIcon5);
        contentPane.add(BtnIcon6);
        contentPane.add(BtnIcon7);
        contentPane.add(BtnIcon8);

        //adding panel to JFrame and seting of window position and close operation
        this.add(contentPane);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.pack();
        this.setVisible(true);
    }

    //method for generate menu
    /*public void generateMenu(){
    menuBar = new JMenuBar();

    JMenu file = new JMenu("File");
    JMenu tools = new JMenu("Tools");
    JMenu help = new JMenu("Help");

    JMenuItem open = new JMenuItem("Open   ");
    JMenuItem save = new JMenuItem("Save   ");
    JMenuItem exit = new JMenuItem("Exit   ");
    JMenuItem preferences = new JMenuItem("Preferences   ");
    JMenuItem about = new JMenuItem("About   ");

    file.add(open);
    file.add(save);
    file.addSeparator();
    file.add(exit);
    tools.add(preferences);
    help.add(about);

    menuBar.add(file);
    menuBar.add(tools);
    menuBar.add(help);
    }*/

    public static void main(String[] args){
        System.setProperty("swing.defaultlaf", "com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
                public void run() {
                    new ConnectPage();
                }
            });
    }

    public void SetIPErrVis(boolean b){
        LblErrorIP.setVisible(b);
    }

    public void SetUsrErrVis(boolean b){
        LblErrMsgUSR.setVisible(b);
    }
}