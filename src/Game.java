import java.util.ArrayList;
import java.util.Scanner;

public class Game {
	private boolean isBlueTurn;
	private Board board;
	private String userInput;
	Scanner in;
	
	/**
	 * Constructor for a new game instance
	 */
	public Game() {
		board = new Board(10, 10);
		isBlueTurn = true;
		
	}
	
	/**
	 * Loops the game with each player taking turns until a player has won.
	 * Actions during a turn include: move, attack, view board, show stats, and pass
	 */
	public void gameLoop() {
		Scanner in = new Scanner(System.in);
		board.placePieces();
		while (!this.blueHasWon() && !redHasWon()) {
			if (isBlueTurn) {
				System.out.println("Blue's Turn");
			}
			else {
				System.out.println("Red's turn");
			}
			this.printMenu();
			
			userInput = in.next();
			if (userInput.contentEquals("1")) {
				System.out.println("Enter the row coordinate of the piece you'd like to move:");
				int startRow = in.nextInt();
				System.out.println("Enter the column coordinate of the piece you'd like to move:");
				int startColumn = in.nextInt();
				System.out.println("Enter the row coordinate of the location you'd like to move to:");
				int endRow = in.nextInt();
				System.out.println("Enter the row coordinate of the location you'd like to move to:");
				int endColumn = in.nextInt();
				board.movePiece(startRow, startColumn, endRow, endColumn);
			}
			if (userInput.contentEquals("2")) {
				System.out.println("Enter the row coordinate of the attacking piece:");
				int attackerRow = in.nextInt();
				System.out.println("Enter the column coordinate of the attacking piece:");
				int attackerColumn = in.nextInt();
				System.out.println("Enter the row coordinate of the defending piece:");
				int defenderRow = in.nextInt();
				System.out.println("Enter the column coordinate of the defending piece:");
				int defenderColumn = in.nextInt();
				GamePiece attacker = board.getPieceAt(attackerRow, attackerColumn);
				GamePiece defender = board.getPieceAt(defenderRow, defenderColumn);
				board.fight(attacker, defender);
			}
			if (userInput.contentEquals("3")) {
				System.out.println(board.toString());
			}
			if (userInput.contentEquals("4")) {
				System.out.println("Enter the row coordinate of the piece:");
				int statRow = in.nextInt();
				System.out.println("Enter the column coordinate of the piece:");
				int statColumn = in.nextInt();
				board.getPieceAt(statRow, statColumn).getStats();
			}
			if (userInput.contentEquals("5")) {
				isBlueTurn = !isBlueTurn;
			}
			
		}
		System.out.println(board.toString());
		
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
