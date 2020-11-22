import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

import javax.swing.ImageIcon;



public class Game implements ActionListener{
	//This class belongs to nick now
	
	
	final int ROWS = 10;
	final int COLS = 15;
	final int NUMSPACES = ROWS*COLS;
	
	private boolean isBlueTurn;
	private Board board;
	private String userInput;
	
	String action;
	int activePiece;
	int secondPoint;
	
	Scanner in;
	
	Gui gui;
	
	Map<Integer, String> map = new TreeMap<Integer, String>();
	
	
	/**
	 * Constructor for a new game instance
	 */
	public Game() {
		map.put(0, "rArcher");
		map.put(NUMSPACES-1, "bArcher");
		map.put(9, "bArcher");
		
		board = new Board(ROWS, COLS);
		isBlueTurn = true;
		
		action = "click";
		
		gui = new Gui(this);
		gui.placePieces(map);
		
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		for (int i = 0; i <= NUMSPACES-1; i++) {
			if (e.getActionCommand().equals("MATRIX_BUTTON_" + i)) {
				gameRun(i);
				
			}
		}
	}
	
	/**
	 * Loops the game with each player taking turns until a player has won.
	 * Actions during a turn include: move, attack, view board, show stats, and pass
	 */
	public void gameRun(int userInput) {
		
		switch(action) {
		case "click":
			activePiece = userInput;
			//gui.moveButtons(board.getMovable());
			action = "move";
			break;
		case "move":
			secondPoint = userInput;
			break;
		case "attack":
			
			break;
		}
		
		
	}

	private ArrayList<GamePiece> generateRoster() {
		return null;
	}
	
	/**
	 * Prints the menu of move options to the player
	 */
	private void printMenu() {
		System.out.println("Actions:");
		System.out.println("1) MOVE");
		System.out.println("2) ATTACK");
		System.out.println("3) VIEW BOARD");
		System.out.println("4) SHOW STATS");
		System.out.println("5) PASS");
		System.out.println();
	}
	
	/**
	 * @return true if red is out of pieces and blue has won the game
	 */
	private boolean blueHasWon() {
		return false;
	}
	
	/**
	 * @return true if blue is out of pieces and red has won the game
	 */
	private boolean redHasWon() {
		return false;
	}
	
}
