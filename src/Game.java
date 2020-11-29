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
	
	/**
	 * Constructor for a new game instance
	 */
	public Game() {
		
		board = new Board(ROWS, COLS);
		board.initializeGame();
		isBlueTurn = true;
		
		gui = new Gui(this);
		gui.placePieces(board.getPositionsAndPieces(), isBlueTurn);
		gui.updateTeam(isBlueTurn);
		System.out.println(board.getPositionsAndPieces());
		
		action = "click";
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		for (int i = 0; i <= NUMSPACES-1; i++) {
			if (e.getActionCommand().equals("MATRIX_BUTTON_" + i)) {
				gameRun(i);
				System.out.println(i);
			}
			
		}
		if (e.getActionCommand().equals("NEXT_TURN")) {
			if (action.equals("click")) {
				isBlueTurn = !isBlueTurn;
				gui.pieceButtons(board.getPositionsAndPieces(), isBlueTurn);
				gui.updateTeam(isBlueTurn);
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
			if (isBlueTurn) {
				gui.setButtonColor(userInput, "blueSelect");
			}
			else {
				gui.setButtonColor(userInput, "redSelect");
			}
			gui.moveButtons(board.availableActions(userInput), activePiece);
			gui.setStats(board.getPieceAt(userInput));
			System.out.println(board.availableActions(userInput));
			action = "action";
			break;
		case "action":
			if (userInput == activePiece) {
				gui.placePieces(board.getPositionsAndPieces(), isBlueTurn);
				
			}
			secondPoint = userInput;
			gui.placePieces(board.getPositionsAndPieces(), isBlueTurn);
			gui.clearStats();
			action = "click";
			break;
		
		}
		
		
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
