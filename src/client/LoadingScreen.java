package src.client;

import javax.swing.*;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;
import java.awt.*;


public class LoadingScreen extends JFrame {

	private final JPanel panel2;

    //Constructor
    public LoadingScreen() {
		this.setTitle("Loading...");
		this.setSize(500, 400);
		this.setResizable(false);

		//pane with null layout
		final JPanel contentPane = new JPanel(null);
		contentPane.setPreferredSize(new Dimension(500, 400));
		contentPane.setBackground(new Color(70, 70, 70));


		final JLabel label1 = new JLabel();
		label1.setBounds(100, 150, 300, 100);
		label1.setBackground(new Color(214, 217, 223));
		label1.setForeground(new Color(0, 0, 0));
		label1.setEnabled(true);
		label1.setFont(new Font("SansSerif", Font.PLAIN, 70));
		label1.setText("SEARCH");
		label1.setVisible(true);

		final JPanel panel1 = new JPanel(null);
		panel1.setBorder(BorderFactory.createEtchedBorder(1));
		panel1.setBounds(102, 230, 290, 19);
		panel1.setBackground(new Color(214, 217, 223));
		panel1.setForeground(new Color(0, 0, 0));
		panel1.setEnabled(true);
		panel1.setFont(new Font("sansserif", Font.PLAIN, 12));
		panel1.setVisible(true);

		panel2 = new JPanel(null);
		panel2.setBorder(BorderFactory.createEtchedBorder(1));
		panel2.setBounds(102, 230, 150, 19);
		panel2.setBackground(new Color(0, 217, 0));
		panel2.setForeground(new Color(0, 0, 0));
		panel2.setEnabled(true);
		panel2.setFont(new Font("sansserif", Font.PLAIN, 12));
		panel2.setVisible(true);

		//adding components to contentPane panel
		contentPane.add(label1);
		contentPane.add(panel1);
		contentPane.add(panel2);

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
		javax.swing.SwingUtilities.invokeLater(LoadingScreen::new);
	}

	public void setLoadingLength(int prz) {
		panel2.setBounds(102, 230, prz * 290, 19);
	}
}
        

