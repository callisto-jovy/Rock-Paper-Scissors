package src.client.screens;

import src.util.eventapi.EventManager;
import src.util.events.RetrieveActiveUsersEvent;
import src.util.events.RetrieveHighscoreListEvent;
import src.util.events.SearchMatchEvent;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.Collections;
import java.util.List;
import java.util.TimerTask;

public class MainMenuScreen extends JFrame {

    private final DefaultListModel<String> listModel;

    /**
     * Create the frame.
     */
    public MainMenuScreen() {
        this.setTitle("Schere-Stein-Papier");
        this.setSize(812, 549);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setLocationRelativeTo(null);

        java.util.Timer timer = new java.util.Timer();

        final Thread activeUserThread = new Thread(() -> {
            timer.scheduleAtFixedRate(new TimerTask() {
                public void run() {
                    EventManager.call(new RetrieveActiveUsersEvent());
                }
            }, 10, 5000);
        });

        this.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {
                activeUserThread.start();
            }

            @Override
            public void windowClosing(WindowEvent e) {
            }

            @Override
            public void windowClosed(WindowEvent e) {
                timer.cancel();
            }

            @Override
            public void windowIconified(WindowEvent e) {

            }

            @Override
            public void windowDeiconified(WindowEvent e) {

            }

            @Override
            public void windowActivated(WindowEvent e) {

            }

            @Override
            public void windowDeactivated(WindowEvent e) {
            }
        });

        JPanel contentPanes = new JPanel();
        contentPanes.setFont(new Font("SansSerif", Font.PLAIN, 12));
        contentPanes.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        contentPanes.setBackground(new Color(50, 50, 50));
        contentPanes.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPanes);
        contentPanes.setLayout(null);

        JLabel lblNewLabel = new JLabel("Schere-Stein-Papier");
        lblNewLabel.setFont(new Font("SansSerif", Font.PLAIN, 38));
        lblNewLabel.setBackground(new Color(214, 217, 223));
        lblNewLabel.setForeground(new Color(210, 210, 210));
        lblNewLabel.setBounds(0, 0, 348, 60);
        contentPanes.add(lblNewLabel);

        JPanel activeUsersPanel = new JPanel();
        activeUsersPanel.setBounds(488, 0, 308, 510);
        activeUsersPanel.setBackground(new Color(70, 70, 70));
        contentPanes.add(activeUsersPanel);


        this.listModel = new DefaultListModel<>();
        final JList<String> activeUserList = new JList<>(listModel);
        activeUserList.setLocation(10, 41);
        activeUserList.setAlignmentX(Component.LEFT_ALIGNMENT);
        activeUserList.setAlignmentY(Component.TOP_ALIGNMENT);
        activeUserList.setSize(new Dimension(288, 424));
        activeUserList.setFont(new Font("SansSerif", Font.PLAIN, 12));
        activeUserList.setToolTipText("Active Users List");
        activeUserList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        activeUserList.setBackground(new Color(80, 80, 80));


        JLabel activeUsersHeading = new JLabel("Active Users");
        activeUsersHeading.setHorizontalAlignment(SwingConstants.CENTER);
        activeUsersHeading.setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
        activeUsersHeading.setBounds(106, 11, 104, 19);
        activeUsersHeading.setFont(new Font("SansSerif", Font.PLAIN, 14));
        activeUsersHeading.setVerticalAlignment(SwingConstants.TOP);
        activeUsersHeading.setBackground(new Color(214, 217, 223));
        activeUsersHeading.setForeground(new Color(210, 210, 210));
        activeUsersPanel.setLayout(null);
        activeUsersPanel.add(activeUserList);
        activeUsersPanel.add(activeUsersHeading);

        JButton highscoreListButton = new JButton("Highscores");
        highscoreListButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                EventManager.call(new RetrieveHighscoreListEvent());
            }
        });
        highscoreListButton.setOpaque(false);
        highscoreListButton.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        highscoreListButton.setBounds(178, 476, 120, 23);
        highscoreListButton.setBackground(new Color(70, 70, 70));
        highscoreListButton.setForeground(new Color(210, 210, 210));
        highscoreListButton.setFont(new Font("SansSerif", Font.PLAIN, 12));
        activeUsersPanel.add(highscoreListButton);

        JButton challengeUserButton = new JButton("Challenge User");
        challengeUserButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                //TODO: Marcel user challenging
            }
        });
        challengeUserButton.setForeground(new Color(210, 210, 210));
        challengeUserButton.setFont(new Font("SansSerif", Font.PLAIN, 12));
        challengeUserButton.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        challengeUserButton.setBackground(new Color(70, 70, 70));
        challengeUserButton.setBounds(10, 476, 120, 23);
        activeUsersPanel.add(challengeUserButton);

        JButton searchMatchButton = new JButton("Search Match");
        searchMatchButton.setFont(new Font("SansSerif", Font.PLAIN, 12));
        searchMatchButton.setBackground(new Color(70, 70, 70));
        searchMatchButton.setForeground(new Color(210, 210, 210));
        searchMatchButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                EventManager.call(new SearchMatchEvent());
            }
        });
        searchMatchButton.setBounds(10, 476, 208, 23);
        contentPanes.add(searchMatchButton);

        final String[] creators = {"Roman", "Marcel", "Soufian", "Aziz", "Jonas"};
        final List<String> shuffledCreators = new java.util.ArrayList<>(List.of(creators));
        Collections.shuffle(shuffledCreators);

        JLabel creatorsHeading = new JLabel("Created by: " + String.join(", ", shuffledCreators));
        creatorsHeading.setHorizontalAlignment(SwingConstants.LEFT);
        creatorsHeading.setForeground(new Color(210, 210, 210));
        creatorsHeading.setFont(new Font("SansSerif", Font.PLAIN, 14));
        creatorsHeading.setBackground(new Color(214, 217, 223));
        creatorsHeading.setBounds(10, 59, 338, 19);
        contentPanes.add(creatorsHeading);

        setVisible(true);
    }

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(NimbusLookAndFeel.class.getCanonicalName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }
        EventQueue.invokeLater(() -> {
            try {
                MainMenuScreen frame = new MainMenuScreen();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    @Override
    public void dispose() {

        super.dispose();
    }

    public DefaultListModel<String> getActiveUserList() {
        return listModel;
    }
}