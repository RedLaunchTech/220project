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
	
	public ArrayList<GamePiece> getGamePieces() {
		return gamePieces;
	}

	public void setGamePieces(ArrayList<GamePiece> gamePieces) {
		this.gamePieces = gamePieces;
	}

	public void placePieces() {
		Swordsman s1 = new Swordsman(0, 0, true);
		Swordsman s2 = new Swordsman(9, 9, false);
		gamePieces.add(s1);
		gamePieces.add(s2);
	}
	
	public void removePiece(GamePiece piece) {
		int pieceRow = piece.getRow();
		int pieceColumn = piece.getColumn();
		
		for (int i = 0; i < gamePieces.size(); ++i ) {
			if (gamePieces.get(i).getRow() == pieceRow && gamePieces.get(i).getColumn() == pieceColumn) {
				gamePieces.remove(i);
			}
		}
		
	}

	public void movePiece(int startRow, int startColumn, int endRow, int endColumn) {
		for (int i = 0; i < gamePieces.size(); ++i ) {
			if (gamePieces.get(i).getRow() == startRow && gamePieces.get(i).getColumn() == startColumn) {
				gamePieces.get(i).setRow(endRow);
				gamePieces.get(i).setColumn(endColumn);
			}
		}
	}
	
	public void fight(GamePiece attacker, GamePiece defender) {
		defender.takeDamage(attacker.dealDamage());
	}
	
	public GamePiece getPieceAt(int row, int column) {
		for (GamePiece piece : gamePieces) {
			if (piece.getRow() == row && piece.getColumn()== column) {
				return piece;
			}
		}
		return null;
	}
	
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
	
	public boolean moveIsValid(GamePiece piece, int row, int column) {
		if (isOccupied[row][column]) {
			return false;
		}
		return true;
	}
	
	public boolean canFight(GamePiece attacker, GamePiece defender) {
		return false;
	}
	
}
