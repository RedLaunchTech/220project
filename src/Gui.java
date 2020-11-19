import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.DebugGraphics;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class Gui  {
	final int ROWS = 10;
	final int COLS = 15;
	final int SQUARE_SIZE = 60;
	final int NUMSPACES = ROWS*COLS;

	
	JFrame frame;
	
	JPanel gameMap;
	JPanel gameMapTile;
	JPanel statsMap;
	
	Color red = new Color(255, 26, 26);
	Color blue = new Color(26, 26, 255);
	Color redSelect = new Color(255, 106, 106);
	Color blueSelect = new Color(106, 106, 255);
	Color moveAvailable = new Color(255, 255, 153);
	Color moveAvailableRed = new Color(255, 153, 51);
	Color moveAvailableBlue = new Color(51, 153, 255);
	
	JLabel label;
	JLabel topStats;
	JLabel health;
	
	
	ArrayList<JButton> gameBoard = new ArrayList<JButton>();
	
	public Gui(ActionListener o) {
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
		
		int i = 0;
		for (JButton button : gameBoard) {
			button.setBorder(BorderFactory.createLineBorder(Color.black));
			
			button.addActionListener(o);
			button.setActionCommand("MATRIX_BUTTON_" + i);
			i++;
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
		frame.setTitle("This is a game");
		frame.pack();
		frame.setVisible(true);
	}
	
	public void setCharacter(int buttonNum, String name) {
		switch(name) {
		case "Archer":
			gameBoard.get(buttonNum).setIcon(new ImageIcon("gui/bow.png"));
			break;
		case "Remove":
			gameBoard.get(buttonNum).setIcon(null);
			break;
		}
		;
	}
	
	public void setButtonColor(int buttonNum, String color) {
		switch(color) {
		case "red":
			gameBoard.get(buttonNum).setBackground(red);
			break;
		case "blue":
			gameBoard.get(buttonNum).setBackground(blue);
			break;
		}
	}
	
	public void placePieces(Map map) {
		for (int i = 0; i <= NUMSPACES-1; i++) {
			String piece = (String) map.getOrDefault(i, "");
			
			if (piece.length() > 0) {
				if (piece.charAt(0) == 'r') {
					this.setButtonColor(i, "red");
				}
				else {
					this.setButtonColor(i, "blue");
				}
				
				setCharacter(i, piece.substring(1, piece.length()));
			}
			
		}
		
		this.pieceButtons(map);
	}
	
	public void pieceButtons(Map map) {
		for (int i = 0; i < NUMSPACES-1; i++) {
			if (map.containsKey(i)) {
				gameBoard.get(i).setEnabled(true);
			}
			else {
				gameBoard.get(i).setEnabled(false);
			}
			
		}
	}
	
	public void moveButtons(Map map) {
		for (int i = 0; i < NUMSPACES-1; i++) {
			if (map.containsKey(i)) {
				gameBoard.get(i).setEnabled(true);
				
			}
			else {
				gameBoard.get(i).setEnabled(false);
			}
			
		}
	}
	
	
	
	
//	public static void main(String[] args) {
//		new Gui();
//	}


}
