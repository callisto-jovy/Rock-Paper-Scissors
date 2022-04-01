package src.client;

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


public class WinnerScreen extends JFrame {

	private JMenuBar menuBar;
	private JLabel label1;
	private JLabel label2;
	private JButton button1;

	//Constructor 
	public WinnerScreen(){

		this.setTitle("WinnerScreen");
		this.setSize(500,400);
		//menu generate method
		generateMenu();
		this.setJMenuBar(menuBar);

		//pane with null layout
		JPanel contentPane = new JPanel(null);
		contentPane.setPreferredSize(new Dimension(500,400));
		contentPane.setBackground(new Color(70,70,70));

		button1 = new JButton();
		button1.setBounds(195,313,120,32);
		button1.setBackground(new Color(230,230,230));
		button1.setForeground(new Color(255,0,0));
		button1.setEnabled(true);
		button1.setFont(new Font("sansserif",0,12));
		button1.setText("Back to Menu");
		button1.setVisible(true);
		
		label1 = new JLabel();
		label1.setBounds(100,150,314,92);
		label1.setBackground(new Color(255,215,0));
		label1.setForeground(new Color(255,215,0));
		label1.setEnabled(true);
		label1.setFont(new Font("SansSerif",1,75));
		label1.setText("WINNER");
		label1.setVisible(true);

		label2 = new JLabel();
		label2.setBounds(170,230,182,31);
		label2.setBackground(new Color(255,255,255));
		label2.setForeground(new Color(255,255,255));
		label2.setEnabled(true);
		label2.setFont(new Font("SansSerif",0,24));
		label2.setText("Congratulations");
		label2.setVisible(true);

		//adding components to contentPane panel
		contentPane.add(button1);
		contentPane.add(label1);
		contentPane.add(label2);

		//adding panel to JFrame and seting of window position and close operation
		this.add(contentPane);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.pack();
		this.setVisible(true);
	}

	//method for generate menu
	public void generateMenu(){
		menuBar = new JMenuBar();





	}



	 public static void main(String[] args){
		System.setProperty("swing.defaultlaf", "com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new WinnerScreen();
			}
		});
	}

}
