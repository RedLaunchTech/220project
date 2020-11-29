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
import javax.swing.BoxLayout;
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
	JLabel currentTeam;
	JLabel type;
	JLabel health;
	JLabel damage;
	JLabel critChance;
	
	
	ArrayList<JButton> gameBoard = new ArrayList<JButton>();
	JButton turnButton;
	
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
			button.setEnabled(false);
			i++;
			gameMap.add(button);
		}
		
		frame.setPreferredSize(new Dimension(260+SQUARE_SIZE*COLS, 70+SQUARE_SIZE*ROWS));
		
		
		statsMap.setPreferredSize(new Dimension(200, SQUARE_SIZE*ROWS));
		gameMap.setPreferredSize(new Dimension(SQUARE_SIZE*COLS,SQUARE_SIZE*ROWS));
		gameMapTile.add(gameMap, BorderLayout.SOUTH);
		
		currentTeam = new JLabel("");
		
		type = new JLabel("Unit Type: ", SwingConstants.LEFT);
		type.setVerticalAlignment(SwingConstants.TOP);
		health = new JLabel("Unit Health: ", SwingConstants.LEFT);
		health.setVerticalAlignment(SwingConstants.TOP);
		damage = new JLabel("Unit damage: ", SwingConstants.LEFT);
		damage.setVerticalAlignment(SwingConstants.TOP);
		critChance = new JLabel("Unit Critical Chance: ", SwingConstants.LEFT);
		critChance.setVerticalAlignment(SwingConstants.TOP);
		
		turnButton = new JButton("Switch Turn");
		turnButton.addActionListener(o);
		turnButton.setActionCommand("NEXT_TURN");
		turnButton.setVerticalAlignment(SwingConstants.BOTTOM);
		turnButton.setSize(new Dimension(180, 100));
		
		statsMap.setLayout(new BoxLayout(statsMap, BoxLayout.Y_AXIS));
		
		statsMap.add(currentTeam);
		statsMap.add(type);
		statsMap.add(health);
		statsMap.add(damage);
		statsMap.add(critChance);
		statsMap.add(turnButton);
		
	
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
			gameBoard.get(buttonNum).setDisabledIcon(new ImageIcon("gui/bow.png"));
			break;
		case "Knight":
			gameBoard.get(buttonNum).setIcon(new ImageIcon("gui/knight.png"));
			gameBoard.get(buttonNum).setDisabledIcon(new ImageIcon("gui/knight.png"));
			break;
		case "Mage":
			gameBoard.get(buttonNum).setIcon(new ImageIcon("gui/wizard.png"));
			gameBoard.get(buttonNum).setDisabledIcon(new ImageIcon("gui/wizard.png"));
			break;
		case "Shieldbearer":
			gameBoard.get(buttonNum).setIcon(new ImageIcon("gui/shieldbearer.png"));
			gameBoard.get(buttonNum).setDisabledIcon(new ImageIcon("gui/shieldbearer.png"));
			break;
		case "Swordsman":
			gameBoard.get(buttonNum).setIcon(new ImageIcon("gui/sword.png"));
			gameBoard.get(buttonNum).setDisabledIcon(new ImageIcon("gui/sword.png"));
			break;
		case "Remove":
			gameBoard.get(buttonNum).setIcon(null);
			gameBoard.get(buttonNum).setDisabledIcon(null);
			break;
		}
	}
	
	public void setButtonColor(int buttonNum, String color) {
		switch(color) {
		case "red":
			gameBoard.get(buttonNum).setBackground(red);
			break;
		case "blue":
			gameBoard.get(buttonNum).setBackground(blue);
			break;
		case "redSelect":
			gameBoard.get(buttonNum).setBackground(redSelect);
			break;
		case "blueSelect":
			gameBoard.get(buttonNum).setBackground(blueSelect);
			break;
		case "move":
			gameBoard.get(buttonNum).setBackground(moveAvailable);
			break;
		case "defualt":
			gameBoard.get(buttonNum).setBackground(new JButton().getBackground());
		}
	}
	
	public void placePieces(Map<Integer, String> map, boolean isBlueTurn) {
		for (int i = 0; i < NUMSPACES; i++) {
			String piece = (String) map.getOrDefault(i, "");
			
			if (piece.length() > 0) {
				if (piece.charAt(0) == 'r') {
					this.setButtonColor(i, "red");
				}
				else if (piece.charAt(0) == 'b') {
					this.setButtonColor(i, "blue");
				}
				
				setCharacter(i, piece.substring(1, piece.length()));
			}
			else {
				this.setButtonColor(i, "defualt");
			}
			
		}
		
		this.pieceButtons(map, isBlueTurn);
	}
	
	public void pieceButtons(Map<Integer, String> map , boolean isBlueTurn) {
		for (int i = 0; i < NUMSPACES; i++) {
			if (map.containsKey(i)) {
				if (isBlueTurn && (map.get(i).charAt(0) == 'b')) {
					gameBoard.get(i).setEnabled(true);
				}
				else if (!isBlueTurn && (map.get(i).charAt(0) == 'r')){
					gameBoard.get(i).setEnabled(true);
				}
				else {
					gameBoard.get(i).setEnabled(false);
				}
				
			}
			else {
				gameBoard.get(i).setEnabled(false);
			}
			
		}
		
		
	}
	
	public void moveButtons(Map<Integer, String> map, int self) {
		for (int i = 0; i < NUMSPACES; i++) {

			String input = map.getOrDefault(i, "");
			
			if (input.length() > 0) {
				if (input.charAt(0) == 'm') {
					gameBoard.get(i).setEnabled(true);
					this.setButtonColor(i, "move");
				}
				else if (input.charAt(0) == 'h') {
					gameBoard.get(i).setEnabled(true);
					this.setButtonColor(i, "move");
				}
				
				//TO FINNISH
				else if (input.charAt(0) == 'a') {
					
				}
				
			}
			else {
				gameBoard.get(i).setEnabled(false);
			}
			
		}
		gameBoard.get(self).setEnabled(true);
	}
	
	
	public void setStats(GamePiece g) {
		type.setText("Unit type: " + g.getPieceType());
		health.setText("Unit Health: " + g.getHitPoints());
		damage.setText("Unit damage: " + g.getAttackDamage());
		critChance.setText("Unit Critical Chance: " + g.getCritChance()*100);
		g.getHitPoints();
	}
	
	public void clearStats() {
		type.setText("Unit type:");
		health.setText("Unit Health:");
		damage.setText("Unit damage:");
		critChance.setText("Unit Critical Chance:");
	}
	
	public void updateTeam(boolean isBlue) {
		if (isBlue) {
			currentTeam.setText("It is blue's turn");
		}
		else {
			currentTeam.setText("It is red's turn");
		}
	}

}
