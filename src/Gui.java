import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.LayoutManager;
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
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.SwingConstants;

public class Gui  {
	final int ROWS = 10;
	final int COLS = 15;
	final int SQUARE_SIZE = 60;
	final int NUMSPACES = ROWS*COLS;

	//the master frame.
	JFrame frame;
	
	//top menu
	JMenu menu;
	JMenuBar menuBar;
	JMenuItem menuItem;
	
	//Popups
	JPopupMenu exitWindow, gameWin, about, info;
	
	//panels used to format the look of the game.
	JPanel gameMap;
	JPanel gameMapTile;
	JPanel statsMap;
	
	String[] menuButtons;
	Boolean[] seperators;
	
	//the colors used for the buttons.
	Color red = new Color(255, 26, 26);
	Color blue = new Color(26, 26, 255);
	Color redSelect = new Color(255, 106, 106);
	Color blueSelect = new Color(106, 106, 255);
	Color moveAvailable = new Color(255, 255, 153);
	Color moveAvailableRed = new Color(255, 153, 51);
	Color moveAvailableBlue = new Color(51, 153, 255);
	
	//labels used to show information.
	JLabel label;
	JLabel topStats;
	JLabel currentTeam;
	JLabel type;
	JLabel health;
	JLabel damage;
	JLabel critChance;
	
	//Labels 
	JLabel gameWinLabel;
	JLabel genericLabel;
	
	//Buttons for the game board and for switching turns.
	ArrayList<JButton> gameBoard = new ArrayList<JButton>();
	JButton turnButton;
	JButton popupButton;
	
	
	/*
	 * the default constructor for the gui.
	 */
	public Gui(ActionListener o) {
		
		//start by initializing all the panels and the frame.
		frame = new JFrame("This is a program");
		gameMap = new JPanel();
		gameMapTile = new JPanel();
		statsMap = new JPanel();
		
		//initialize all the buttons of the gameboard.
		for (int i = 0; i <= NUMSPACES-1; i++) {
			gameBoard.add(new JButton());
		}
		
		//set the layout of the overall game.
		gameMapTile.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		gameMapTile.setBackground(Color.LIGHT_GRAY);
		statsMap.setBorder(BorderFactory.createBevelBorder(0, Color.GRAY, Color.BLACK));
		
		//Create a layout for the board buttons.
		gameMap.setLayout(new GridLayout(ROWS,COLS,5,5));
		
		//Initialize all of the buttons.
		int i = 0;
		for (JButton button : gameBoard) {
			button.setBorder(BorderFactory.createLineBorder(Color.black));
			
			button.addActionListener(o);
			button.setActionCommand("MATRIX_BUTTON_" + i);
			button.setEnabled(false);
			i++;
			gameMap.add(button);
		}
		
		//set the preferred size of the game window based on the board size.
		frame.setPreferredSize(new Dimension(260+SQUARE_SIZE*COLS, 90+SQUARE_SIZE*ROWS));
		
		//set the preferred size of the panels within the frame
		statsMap.setPreferredSize(new Dimension(200, SQUARE_SIZE*ROWS));
		gameMap.setPreferredSize(new Dimension(SQUARE_SIZE*COLS,SQUARE_SIZE*ROWS));
		gameMapTile.add(gameMap, BorderLayout.SOUTH);
		
		//Initialize all of the info text on the side.
		currentTeam = new JLabel("");
		type = new JLabel("Unit Type: ", SwingConstants.LEFT);
		type.setVerticalAlignment(SwingConstants.TOP);
		health = new JLabel("Unit Health: ", SwingConstants.LEFT);
		health.setVerticalAlignment(SwingConstants.TOP);
		damage = new JLabel("Unit damage: ", SwingConstants.LEFT);
		damage.setVerticalAlignment(SwingConstants.TOP);
		critChance = new JLabel("Unit Critical Chance: ", SwingConstants.LEFT);
		critChance.setVerticalAlignment(SwingConstants.TOP);
		
		//Initialize the turn change button.
		turnButton = new JButton("Switch Turn");
		turnButton.addActionListener(o);
		turnButton.setActionCommand("NEXT_TURN");
		turnButton.setVerticalAlignment(SwingConstants.BOTTOM);
		turnButton.setSize(new Dimension(180, 100));
		
		//set the layout of the left pane.
		statsMap.setLayout(new BoxLayout(statsMap, BoxLayout.Y_AXIS));
		
		//add the info text to the left pane.
		statsMap.add(currentTeam);
		statsMap.add(type);
		statsMap.add(health);
		statsMap.add(damage);
		statsMap.add(critChance);
		statsMap.add(turnButton);
		
		//Create the top menu.
		menuBar = new JMenuBar();
		menu = new JMenu("Game");
		
		menuItem = new JMenuItem("New Game");
		menuItem.addActionListener(o);
		menuItem.setActionCommand("New Game");
		menu.add(menuItem);
		
		menu.addSeparator();
		
		menuItem = new JMenuItem("Exit");
		menuItem.addActionListener(o);
		menuItem.setActionCommand("Exit");
		menu.add(menuItem);
		
		menuBar.add(menu);
		
		menu = new JMenu("Info");
		
		menuItem = new JMenuItem("How to Play");
		menuItem.getAccessibleContext().setAccessibleDescription("How to Play");
		menu.add(menuItem);
		
		menu.addSeparator();
		
		menuItem = new JMenuItem("About");
		menuItem.addActionListener(o);
		menuItem.setActionCommand("About");
		menu.add(menuItem);
		
		menuBar.add(menu);
		
		//Exit popup
		exitWindow = new JPopupMenu("Exit the game?");
		exitWindow.setLayout(new BoxLayout(exitWindow, BoxLayout.Y_AXIS));
		exitWindow.setPreferredSize(new Dimension(150, 100));
		
		JPanel exitWindowMenu = new JPanel();
		exitWindowMenu.setLayout(new BoxLayout(exitWindowMenu, BoxLayout.X_AXIS));
		exitWindowMenu.setAlignmentX((float) .5);
		
		JPanel space = new JPanel();
		space.setPreferredSize(new Dimension(180, 80));
		
		JLabel exitWindowLabel = new JLabel("Exit the game?");
		exitWindowLabel.setAlignmentX((float) .5);
		
		
		popupButton = new JButton("Yes");
		popupButton.setActionCommand("exitOk");
		popupButton.addActionListener(o);
		
		exitWindowMenu.add(popupButton);
		
		popupButton = new JButton("No");
		popupButton.setActionCommand("exitNo");
		popupButton.addActionListener(o);
		
		exitWindowMenu.add(popupButton);
		
		exitWindow.add(exitWindowLabel);
		exitWindow.add(space);
		exitWindow.add(exitWindowMenu);
		
		//Game Win popup
		gameWin = new JPopupMenu("Exit the game?");
		gameWin.setLayout(new BoxLayout(gameWin, BoxLayout.Y_AXIS));
		gameWin.setPreferredSize(new Dimension(200, 100));
		
		JPanel gameWinMenu = new JPanel();
		gameWinMenu.setLayout(new BoxLayout(gameWinMenu, BoxLayout.X_AXIS));
		gameWinMenu.setAlignmentX((float) .5);
		
		space = new JPanel();
		space.setPreferredSize(new Dimension(180, 80));
		
		gameWinLabel = new JLabel("The game has been won");
		gameWinLabel.setAlignmentX((float) .5);
		
		popupButton = new JButton("New Game");
		popupButton.setActionCommand("New Game");
		popupButton.addActionListener(o);
		
		gameWinMenu.add(popupButton);
		
		popupButton = new JButton("Exit Game");
		popupButton.setActionCommand("exitOk");
		popupButton.addActionListener(o);
		
		gameWinMenu.add(popupButton);
		
		gameWin.add(gameWinLabel);
		gameWin.add(space);
		gameWin.add(gameWinMenu);
		
		//About popup
		about = new JPopupMenu("About");
		about.setLayout(new BoxLayout(about, BoxLayout.Y_AXIS));
		about.setPreferredSize(new Dimension(200, 100));
		
		space = new JPanel();
		space.setPreferredSize(new Dimension(180, 80));
		
		genericLabel = new JLabel("A message about the game");
		genericLabel.setAlignmentX((float) .5);
		
		popupButton = new JButton("Done");
		popupButton.setActionCommand("exitAbout");
		popupButton.addActionListener(o);
		
		about.add(gameWinLabel);
		about.add(space);
		about.add(popupButton);
		
		
		//add the two main pains to the frame and make it visible.
		frame.add(gameMapTile, BorderLayout.EAST);
		frame.add(statsMap, BorderLayout.WEST);
		frame.setJMenuBar(menuBar);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Game of Ages");
		frame.pack();
		frame.setVisible(true);
	}
	
	public void showAbout() {
		gameWin.setVisible(true);
		gameWin.setLocation((int)(frame.getSize().getWidth()/2 - gameWin.getSize().getWidth()/2), (int)(frame.getSize().getHeight()/2 - gameWin.getSize().getHeight()/2));
	}
	public void closeAbout() {
		gameWin.setVisible(false);
		gameWin.setLocation((int)(frame.getSize().getWidth()/2 - gameWin.getSize().getWidth()/2), (int)(frame.getSize().getHeight()/2 - gameWin.getSize().getHeight()/2));
	}
	
	public void showGameWin(String winner) {
		gameWin.setVisible(true);
		gameWinLabel.setText(winner + " has won the game!");
		gameWin.setLocation((int)(frame.getSize().getWidth()/2 - gameWin.getSize().getWidth()/2), (int)(frame.getSize().getHeight()/2 - gameWin.getSize().getHeight()/2));
	}
	
	public void hideGameWin() {
		gameWin.setVisible(false);
	}
	
	public void showExitWindow() {
		exitWindow.setVisible(true);
		exitWindow.setLocation((int)(frame.getSize().getWidth()/2 - exitWindow.getSize().getWidth()/2), (int)(frame.getSize().getHeight()/2 - exitWindow.getSize().getHeight()/2));
	}
	
	public void hideExitWindow() {
		exitWindow.setVisible(false);
	}
	
	/**
	 * sets the icon for a particular button based off of its unit type
	 * @param buttonNum - the location of the button to have the character placed in.
	 * @param name - the type of character to create.
	 */
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
		case "Assassin":
			gameBoard.get(buttonNum).setIcon(new ImageIcon("gui/assassin.png"));
			gameBoard.get(buttonNum).setDisabledIcon(new ImageIcon("gui/assassin.png"));
			break;
		case "Remove":
			gameBoard.get(buttonNum).setIcon(null);
			gameBoard.get(buttonNum).setDisabledIcon(null);
			break;
		}
	}
	
	/**
	 * Sets the color of an individual button on the map.
	 * @param buttonNum - The location of the buttons color to change
	 * @param color - the color to change the button to.
	 */
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
		case "moveAvailableBlue":
			gameBoard.get(buttonNum).setBackground(moveAvailableBlue);
			break;
		case "moveAvailableRed":
			gameBoard.get(buttonNum).setBackground(moveAvailableRed);
			break;
		case "move":
			gameBoard.get(buttonNum).setBackground(moveAvailable);
			break;
		case "defualt":
			gameBoard.get(buttonNum).setBackground(new JButton().getBackground());
		}
	}
	
	
	/**
	 * places the pieces on the initial board and enables the buttons of whoevers turn it is.
	 * @param map - the map as outlined by board.
	 * @param isBlueTurn - the current players turn.
	 */
	public void placePieces(Map<Integer, String> map, boolean isBlueTurn) {
		for (int i = 0; i < NUMSPACES; i++) {
			String piece = map.getOrDefault(i, "");
			
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
				setCharacter(i, "Remove");
			}	
		}
		
		this.pieceButtons(map, isBlueTurn);
	}
	
	/**
	 * enables the buttons of a team based off of a tree map.
	 * given by the board class.
	 * @param map - the map with all the game pieces on the board.
	 * @param isBlueTurn - the current players turn.
	 */
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
	
	/**
	 * Activates all the buttons which correlate to an action for the selected piece.
	 * @param map - a treemap of actions available to the piece.
	 * @param self - the location of the active piece.
	 */
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
					if (input.charAt(1) == 'b') {
						this.setButtonColor(i, "moveAvailableBlue");
					}
					else if (input.charAt(1) == 'r') {
						this.setButtonColor(i, "moveAvailableRed");
					}
				}
				else if (input.charAt(0) == 'a') {
					gameBoard.get(i).setEnabled(true);
					if (input.charAt(1) == 'b') {
						this.setButtonColor(i, "moveAvailableBlue");
					}
					else if (input.charAt(1) == 'r') {
						this.setButtonColor(i, "moveAvailableRed");
					}
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
	
	/**
	 * Display the states of the selected game piece.
	 * @param g - the game piece selected.
	 */
	public void setStats(GamePiece g) {
		type.setText("Unit Type: " + g.getPieceType());
		health.setText("Unit Health: " + g.getHitPoints());
		damage.setText("Unit Damage: " + g.getAttackDamage());
		critChance.setText("Unit Critical Chance: " + String.format("%1.0f%%",g.getCritChance()*100));
		g.getHitPoints();
	}
	
	/**
	 * reset all states to the default values.
	 */
	public void clearStats() {
		type.setText("Unit type:");
		health.setText("Unit Health:");
		damage.setText("Unit damage:");
		critChance.setText("Unit Critical Chance:");
	}
	
	/**
	 * updates the players turn text based off of who's turn it is.
	 * @param isBlue - a boolean that is true when it is blues turn.
	 */
	public void updateTeam(boolean isBlue) {
		if (isBlue) {
			currentTeam.setText("It is blue's turn");
		}
		else {
			currentTeam.setText("It is red's turn");
		}
	}

}
