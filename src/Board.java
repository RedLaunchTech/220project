import java.util.ArrayList;

public class Board {
	private final int width;
	private final int height;
	private ArrayList<GamePiece> gamePieces;
	private boolean [][] isOccupied;

	public Board(int width, int height) {
		this.width = width;
		this.height = height;
		gamePieces = new ArrayList<>();
		// add isOccupied to constructor
	}
	
	/**
	 * @return ArrayList of game pieces on the board
	 */
	public ArrayList<GamePiece> getGamePieces() {
		return gamePieces;
	}

	/**
	 * Generates roster of pieces for the @param currPlayer
	 */
	public void generateRoster(boolean currPlayer) {
		//do some things
	}
	
	
	public void setGamePieces(ArrayList<GamePiece> gamePieces) {
		this.gamePieces = gamePieces;
	}

	/**
	 * Gives each game piece a position (row, column) on the board
	 */
	public void placePieces() {
		Swordsman s1 = new Swordsman(0, 0, true);
		Swordsman s2 = new Swordsman(9, 9, false);
		gamePieces.add(s1);
		gamePieces.add(s2);
	}
	
	/**
	 * Removes a piece from the board
	 * @param piece
	 */
	public void removePiece(GamePiece piece) {
		int pieceRow = piece.getRow();
		int pieceColumn = piece.getColumn();
		
		for (int i = 0; i < gamePieces.size(); ++i ) {
			if (gamePieces.get(i).getRow() == pieceRow && gamePieces.get(i).getColumn() == pieceColumn) {
				gamePieces.remove(i);
			}
		}
		
	}

	/**
	 * Changes the position of a game piece using starting coordinates @param startRow and @param startColumn
	 * to ending coordinates @param endRow and @param endColumn
	 */
	public void movePiece(int startRow, int startColumn, int endRow, int endColumn) {
		for (int i = 0; i < gamePieces.size(); ++i ) {
			if (gamePieces.get(i).getRow() == startRow && gamePieces.get(i).getColumn() == startColumn) {
				gamePieces.get(i).setRow(endRow);
				gamePieces.get(i).setColumn(endColumn);
			}
		}
	}
	
	/**
	 * Two pieces "fight". The @param attacker deals damage to the @param defender.
	 */
	public void fight(GamePiece attacker, GamePiece defender) {
		defender.takeDamage(attacker.dealDamage());
	}
	
	/**
	 * @return piece at @param row, @param column
	 */
	public GamePiece getPieceAt(int row, int column) {
		for (GamePiece piece : gamePieces) {
			if (piece.getRow() == row && piece.getColumn()== column) {
				return piece;
			}
		}
		return null;
	}
	
	/**
	 * @return string representation of the board and the pieces on the board
	 */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Board is " + width + " by " + height + ".\n");
		for (GamePiece piece : gamePieces) {
			if (piece.isBlueTeam()) {
				sb.append("Blue team has a piece at position " + piece.getRow() + ", " + piece.getColumn() + ".\n");
			}
			else {
				sb.append("Red team has a piece at position " + piece.getRow() + ", " + piece.getColumn() + ".\n");
			}
		}
		return sb.toString();
	}
	
	/**
	 * @return true if @param piece can move to the space at @param row, @param column
	 * Valid move if space is not occupied by another piece
	 */
	public boolean moveIsValid(GamePiece piece, int row, int column) {
		if (isOccupied[row][column]) {
			return false;
		}
		return true;
	}
	
	/** 
	 * @return true if @param attacker is within attack range of @param defender
	 */
	public boolean canFight(GamePiece attacker, GamePiece defender) {
		return false;
	}
	
}
