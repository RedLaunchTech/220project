import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Gui {
	
	JFrame frame;
	
	JPanel gameMap;
	JPanel statsMap;
	
	JLabel label;
	JLabel health;
	
	ArrayList<JButton> gameBoard = new ArrayList<JButton>();
	
	public Gui() {
		frame = new JFrame("This is a program");
		
		
		gameMap = new JPanel();
		statsMap = new JPanel();
		
		gameMap.setBorder(BorderFactory.createEmptyBorder(30, 30, 10, 30));
		
		statsMap.setBorder(BorderFactory.createBevelBorder(0, Color.GRAY, Color.BLACK));
		
		frame.setPreferredSize(new Dimension(600, 600));
		
		health = new 
		
		statsMap.add(health);
		
		frame.add(gameMap, BorderLayout.EAST);
		frame.add(statsMap, BorderLayout.WEST);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("TicTacToe");
		frame.pack();
		frame.setVisible(true);
	}
	
	public static void main(String[] args) {
		new Gui();
	}
}
