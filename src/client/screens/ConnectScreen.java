package src.client.screens;

import src.client.Player;
import src.util.namegen.Nomen;

import javax.swing.*;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ConnectScreen extends JFrame {

    private final JLabel lblErrMsgUSR;
    private final JLabel lblErrorIP;
    private final JTextField tfIPAddress;
    private final JTextField tfUsername;
    private final JButton btnChooseImage;

    //Constructor
    public ConnectScreen() {
        this.setTitle("Connect to Server");
        this.setSize(700, 450);
        this.setResizable(false);
        //menu generate method

        //pane with null layout
        final JPanel contentPane = new JPanel(null);
        contentPane.setPreferredSize(new Dimension(700, 450));
        contentPane.setBackground(new Color(50, 50, 50));

        final JButton btnConnect = new JButton();
        btnConnect.setBounds(187, 250, 120, 40);
        btnConnect.setBackground(new Color(70, 70, 70));
        btnConnect.setForeground(new Color(210, 210, 210));
        btnConnect.setEnabled(true);
        btnConnect.setFont(new Font("sansserif", 0, 12));
        btnConnect.setText("Connect");
        btnConnect.setVisible(true);
        btnConnect.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                Player.INSTANCE.tryConnect(tfIPAddress.getText(), tfUsername.getText());
            }
        });

        btnChooseImage = new JButton();
        btnChooseImage.setBounds(300, 350, 140, 48);
        btnChooseImage.setBackground(new Color(70, 70, 70));
        btnChooseImage.setForeground(new Color(210, 210, 210));
        btnChooseImage.setEnabled(true);
        btnChooseImage.setFont(new Font("sansserif", 0, 12));
        btnChooseImage.setText("Choose Profile Pic");
        btnChooseImage.setVisible(true);
        btnChooseImage.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                Player.INSTANCE.chooseProfilePic();
            }
        });

        this.lblErrMsgUSR = new JLabel();
        lblErrMsgUSR.setBounds(210, 225, 190, 20);
        lblErrMsgUSR.setBackground(new Color(214, 217, 223));
        lblErrMsgUSR.setForeground(new Color(255, 0, 0));
        lblErrMsgUSR.setEnabled(true);
        lblErrMsgUSR.setFont(new Font("sansserif", 0, 12));
        lblErrMsgUSR.setText("Username already exists!");
        lblErrMsgUSR.setVisible(false);

        this.lblErrorIP = new JLabel();
        lblErrorIP.setBounds(210, 210, 190, 20);
        lblErrorIP.setBackground(new Color(214, 217, 223));
        lblErrorIP.setForeground(new Color(255, 0, 0));
        lblErrorIP.setEnabled(true);
        lblErrorIP.setFont(new Font("sansserif", 0, 12));
        lblErrorIP.setText("IP-Adress incorrect!");
        lblErrorIP.setVisible(false);

        this.tfIPAddress = new JTextField();
        tfIPAddress.setBounds(200, 120, 200, 40);
        tfIPAddress.setBackground(new Color(70, 70, 70));
        tfIPAddress.setForeground(new Color(210, 210, 210));
        tfIPAddress.setEnabled(true);
        tfIPAddress.setFont(new Font("sansserif", 0, 12));
        tfIPAddress.setText("10.147.48.");
        tfIPAddress.setVisible(true);

        final JLabel label1 = new JLabel();
        label1.setBounds(90, 120, 100, 40);
        label1.setBackground(new Color(255, 255, 255));
        label1.setForeground(new Color(210, 210, 210));
        label1.setEnabled(true);
        label1.setFont(new Font("SansSerif", 0, 12));
        label1.setText("Enter Server IP:");
        label1.setVisible(true);

        final JLabel label2 = new JLabel();
        label2.setBounds(90, 170, 100, 40);
        label2.setBackground(new Color(214, 217, 223));
        label2.setForeground(new Color(210, 210, 210));
        label2.setEnabled(true);
        label2.setFont(new Font("sansserif", 0, 12));
        label2.setText("Enter Username:");
        label2.setVisible(true);

        this.tfUsername = new JTextField();
        tfUsername.setBounds(200, 170, 200, 40);
        tfUsername.setBackground(new Color(70, 70, 70));
        tfUsername.setForeground(new Color(210, 210, 210));
        tfUsername.setEnabled(true);
        tfUsername.setFont(new Font("sansserif", 0, 12));
        tfUsername.setText(Nomen.randomName());
        tfUsername.setVisible(true);

        //Add avatar selector
        for (int i = 1, yOffset = 0; i < 9; i++) {
            final JButton profileButton = new JButton(new ImageIcon(getClass().getResource("/src/client/icons/48/" + i + ".png")));
            profileButton.setBackground(new Color(70, 70, 70));

            if (i % 2 == 0) {
                profileButton.setBounds(576, 50 + yOffset, 48, 48);
                yOffset += 100;
            } else {
                profileButton.setBounds(476, 50 + yOffset, 48, 48);
            }

            final int selected = i;
            profileButton.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    Player.INSTANCE.setProfilePic(selected);
                    super.mouseClicked(e);
                }
            });
            contentPane.add(profileButton);
        }
        //adding components to contentPane panel
        contentPane.add(btnConnect);
        contentPane.add(lblErrMsgUSR);
        contentPane.add(lblErrorIP);
        contentPane.add(tfIPAddress);
        contentPane.add(label1);
        contentPane.add(label2);
        contentPane.add(tfUsername);
        contentPane.add(btnChooseImage);

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

        javax.swing.SwingUtilities.invokeLater(ConnectScreen::new);
    }

    public void setCustomImageButtonTooltip(final String tooltip) {
        btnChooseImage.setToolTipText(tooltip);
    }

    public void setIPErrVis(boolean toggle) {
        lblErrorIP.setVisible(toggle);
    }

    public void setUsrErrVis(boolean toggle) {
        lblErrMsgUSR.setVisible(toggle);
    }
}