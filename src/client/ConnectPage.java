package src.client;

import javax.swing.*;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ConnectPage extends JFrame {

    private final JButton BtnConnect;
    private final JLabel LblErrMsgUSR;
    private final JLabel LblErrorIP;
    private final JTextField TFIPAdress;
    private final JLabel label1;
    private final JLabel label2;
    private final JTextField TFUserName;

    private ClientLogin invoker;

    //Constructor
    public ConnectPage() {
        this.setResizable(false);
        this.setTitle("Connect to Server");
        this.setSize(700, 450);
        //menu generate method

        //pane with null layout
        JPanel contentPane = new JPanel(null);
        contentPane.setPreferredSize(new Dimension(700, 450));
        contentPane.setBackground(new Color(50, 50, 50));

        BtnConnect = new JButton();
        BtnConnect.setBounds(187, 280, 120, 40);
        BtnConnect.setBackground(new Color(70, 70, 70));
        BtnConnect.setForeground(new Color(210, 210, 210));
        BtnConnect.setEnabled(true);
        BtnConnect.setFont(new Font("sansserif", 0, 12));
        BtnConnect.setText("Connect");
        BtnConnect.setVisible(true);
        BtnConnect.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                invoker.tryConnect(TFIPAdress.getText(), TFUserName.getText());
            }
        });

        LblErrMsgUSR = new JLabel();
        LblErrMsgUSR.setBounds(210, 225, 190, 20);
        LblErrMsgUSR.setBackground(new Color(214, 217, 223));
        LblErrMsgUSR.setForeground(new Color(255, 0, 0));
        LblErrMsgUSR.setEnabled(true);
        LblErrMsgUSR.setFont(new Font("sansserif", 0, 12));
        LblErrMsgUSR.setText("Username already exists!");
        LblErrMsgUSR.setVisible(false);

        LblErrorIP = new JLabel();
        LblErrorIP.setBounds(210, 210, 190, 20);
        LblErrorIP.setBackground(new Color(214, 217, 223));
        LblErrorIP.setForeground(new Color(255, 0, 0));
        LblErrorIP.setEnabled(true);
        LblErrorIP.setFont(new Font("sansserif", 0, 12));
        LblErrorIP.setText("IP-Adress incorrect!");
        LblErrorIP.setVisible(false);

        TFIPAdress = new JTextField();
        TFIPAdress.setBounds(200, 120, 200, 40);
        TFIPAdress.setBackground(new Color(70, 70, 70));
        TFIPAdress.setForeground(new Color(210, 210, 210));
        TFIPAdress.setEnabled(true);
        TFIPAdress.setFont(new Font("sansserif", 0, 12));
        TFIPAdress.setText("10.147.48.");
        TFIPAdress.setVisible(true);

        label1 = new JLabel();
        label1.setBounds(90, 120, 100, 40);
        label1.setBackground(new Color(255, 255, 255));
        label1.setForeground(new Color(210, 210, 210));
        label1.setEnabled(true);
        label1.setFont(new Font("SansSerif", 0, 12));
        label1.setText("Enter Server IP:");
        label1.setVisible(true);

        label2 = new JLabel();
        label2.setBounds(90, 170, 100, 40);
        label2.setBackground(new Color(214, 217, 223));
        label2.setForeground(new Color(210, 210, 210));
        label2.setEnabled(true);
        label2.setFont(new Font("sansserif", 0, 12));
        label2.setText("Enter Username:");
        label2.setVisible(true);

        TFUserName = new JTextField();
        TFUserName.setBounds(200, 170, 200, 40);
        TFUserName.setBackground(new Color(70, 70, 70));
        TFUserName.setForeground(new Color(210, 210, 210));
        TFUserName.setEnabled(true);
        TFUserName.setFont(new Font("sansserif", 0, 12));
        TFUserName.setText("Player1");
        TFUserName.setVisible(true);


        for (int i = 1, yOffset = 0; i <= 8; i++) {
            final JButton profileButton = new JButton(new ImageIcon(getClass().getResource("/src/client/icons/48/" + i + ".png")));
            profileButton.setBackground(new Color(70, 70, 70));

            if (i % 2 == 0) {
                profileButton.setBounds(576, 50 + yOffset, 48, 48);
            } else if (i > 2) {
                profileButton.setBounds(476, 50 + yOffset, 48, 48);
                yOffset += 100;
            }

            final int selected = i;
            profileButton.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    invoker.setProfilePic(selected);
                    super.mouseClicked(e);
                }
            });
            contentPane.add(profileButton);
        }
        //adding components to contentPane panel
        contentPane.add(BtnConnect);
        contentPane.add(LblErrMsgUSR);
        contentPane.add(LblErrorIP);
        contentPane.add(TFIPAdress);
        contentPane.add(label1);
        contentPane.add(label2);
        contentPane.add(TFUserName);


        //adding panel to JFrame and seting of window position and close operation
        this.add(contentPane);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.pack();
        this.setVisible(true);
    }

    public void setInvoker(ClientLogin pInvoker) {
        this.invoker = pInvoker;
    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(NimbusLookAndFeel.class.getCanonicalName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }

        javax.swing.SwingUtilities.invokeLater(ConnectPage::new);
    }

    public void SetIPErrVis(boolean b) {
        LblErrorIP.setVisible(b);
    }

    public void SetUsrErrVis(boolean b) {
        LblErrMsgUSR.setVisible(b);
    }
}