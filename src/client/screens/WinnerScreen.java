package src.client.screens;

import src.client.Player;

import javax.swing.*;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class WinnerScreen extends JFrame {

	//Constructor 
	public WinnerScreen() {
		this.setTitle("WinnerScreen");
		this.setSize(500, 400);
		this.setResizable(false);

		//pane with null layout
		final JPanel contentPane = new JPanel(null);
		contentPane.setPreferredSize(new Dimension(500, 400));
		contentPane.setBackground(new Color(50, 50, 50));

		final JButton button1 = new JButton();
		button1.setBounds(195, 313, 120, 32);
		button1.setBackground(new Color(230, 230, 230));
		button1.setForeground(new Color(255, 0, 0));
		button1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Player.INSTANCE.displayMainMenu();
				setVisible(false);
				dispose();
				super.mouseClicked(e);
			}
		});
		button1.setEnabled(true);
		button1.setFont(new Font("sansserif", 0, 12));
		button1.setText("Back to Menu");
		button1.setVisible(true);

		final JLabel label1 = new JLabel();
		label1.setBounds(100, 150, 314, 92);
		label1.setBackground(new Color(255, 215, 0));
		label1.setForeground(new Color(255, 215, 0));
		label1.setEnabled(true);
		label1.setFont(new Font("SansSerif", 1, 75));
		label1.setText("WINNER");
		label1.setVisible(true);

		final JLabel label2 = new JLabel();
		label2.setBounds(170, 230, 182, 31);
		label2.setBackground(new Color(255, 255, 255));
		label2.setForeground(new Color(255, 255, 255));
		label2.setEnabled(true);
		label2.setFont(new Font("SansSerif", 0, 24));
		label2.setText("Congratulations");
		label2.setVisible(true);

		//adding components to contentPane panel
		contentPane.add(button1);
		contentPane.add(label1);
		contentPane.add(label2);

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
		javax.swing.SwingUtilities.invokeLater(WinnerScreen::new);
	}
}
