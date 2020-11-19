import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.DebugGraphics;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class Gui implements ActionListener {
	final int ROWS = 8;
	final int COLS = 8;
	final int SQUARE_SIZE = 60;
	final int NUMSPACES = ROWS*COLS;
	
	JFrame frame;
	
	JPanel gameMap;
	JPanel gameMapTile;
	JPanel statsMap;
	
	JLabel label;
	JLabel topStats;
	JLabel health;
	
	
	ArrayList<JButton> gameBoard = new ArrayList<JButton>();
	
	public Gui() {
		frame = new JFrame("This is a program");
		
		
		gameMap = new JPanel();
		gameMapTile = new JPanel();
		statsMap = new JPanel();
		
		for (int i = 0; i <= NUMSPACES-1; i++) {
			gameBoard.add(new JButton());
		}
		
		gameMapTile.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		gameMapTile.setBackground(Color.LIGHT_GRAY);
		statsMap.setBorder(BorderFactory.createBevelBorder(0, Color.GRAY, Color.BLACK));
		
		gameMap.setLayout(new GridLayout(ROWS,COLS,5,5));
		
		for (JButton button : gameBoard) {
			button.setBorder(BorderFactory.createLineBorder(Color.black));
			
			button.addActionListener(this);
			button.setOpaque(true);
			gameMap.add(button);
		}
		
		frame.setPreferredSize(new Dimension(260+SQUARE_SIZE*COLS, 70+SQUARE_SIZE*ROWS));
		
		
		statsMap.setPreferredSize(new Dimension(200, SQUARE_SIZE*ROWS));
		gameMap.setPreferredSize(new Dimension(SQUARE_SIZE*COLS,SQUARE_SIZE*ROWS));
		gameMapTile.add(gameMap, BorderLayout.SOUTH);
		
		health = new JLabel("Piece Health: ", SwingConstants.LEFT);
		
		
		
		statsMap.add(health);
		
	
		frame.add(gameMapTile, BorderLayout.EAST);
		frame.add(statsMap, BorderLayout.WEST);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("TicTacToe");
		frame.pack();
		frame.setVisible(true);
	}
	
	public static void main(String[] args) {
		new Gui();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		for (int i = 0; i <= NUMSPACES-1; i++) {
			if (e.getSource() == gameBoard.get(i)) {
				System.out.println(i);
				gameBoard.get(i).setIcon(new ImageIcon("gui/bow.png"));
			}
		}
	}
}
