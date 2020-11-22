import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.TreeMap;

// comment
public class Board {
	private final int width;
	private final int height;
	private final ArrayList<String> PIECETYPES ;
	private ArrayList<GamePiece> gamePieces;
	private ArrayList<Integer> occupiedSpaces;
	private TreeMap<Integer, GamePiece> positionsAndPieces;

	public Board(int width, int height) {
		this.width = width;
		this.height = height;
		PIECETYPES = new ArrayList<>(List.of("Archer","Assassin","Knight","Mage","Shieldbearer","Swordsman"));
		gamePieces = new ArrayList<>();
		occupiedSpaces = new ArrayList();
		positionsAndPieces = new TreeMap();
	}
	
	/**
	 * @return ArrayList of game pieces on the board
	 */
	public ArrayList<GamePiece> getGamePieces() {
		return gamePieces;
	}
	
	public void initializeGame() {
		this.generateRoster(false);
		this.generateRoster(true);
		this.placePieces();
	}

	/**
	 * Generates roster of pieces for the @param currPlayer
	 */
	private void generateRoster(boolean currPlayer) {
		for (String s : PIECETYPES) {
			if (s.equals("Archer")) {
				Archer newPiece = new Archer(currPlayer);
				gamePieces.add(newPiece);
			}
			if (s.equals("Assassin")) {
				Assassin newPiece = new Assassin(currPlayer);
				gamePieces.add(newPiece);
			}
			if (s.equals("Knight")) {
				Knight newPiece = new Knight(currPlayer);
				gamePieces.add(newPiece);
			}
			if (s.equals("Mage")) {
				Mage newPiece = new Mage(currPlayer);
				gamePieces.add(newPiece);
			}
			if (s.equals("Shieldbearer")) {
				Mage newPiece = new Mage(currPlayer);
				gamePieces.add(newPiece);
			}
			if (s.equals("Swordsman")) {
				Swordsman newPiece = new Swordsman(currPlayer);
				gamePieces.add(newPiece);
			}
		}
	}

	/**
	 * Places the pieces as values in a map which ties them to their key of positions
	 */
	private void placePieces() {
		Integer blue = 0;
		Integer red = 154;
		for (GamePiece piece : gamePieces) {
			if (piece.isBlueTeam) {
				positionsAndPieces.put(blue, piece);
				occupiedSpaces.add(blue);
				++blue;
			}
			else {
				positionsAndPieces.put(red, piece);
				occupiedSpaces.add(red);
				++red;
			}
		}
	}

	
	/**
	 * Removes a piece from the board
	 * @param piece
	 */
	public void removePiece(Integer i) {
		positionsAndPieces.remove(i); 
	}

	/**
	 * Changes the position of a game piece using starting coordinates @param startRow and @param startColumn
	 * to ending coordinates @param endRow and @param endColumn
	 */
	public void movePiece(int startRow, int startColumn, int endRow, int endColumn) {

	}
	
	/**
	 * Two pieces "fight". The @param attacker deals damage to the @param defender.
	 */
	public void fight(GamePiece attacker, GamePiece defender) {
		defender.takeDamage(attacker.dealDamage());
	}
	
	/**
	 * @param assassin deals extra damage if @param targer has no friendly pieces within its movement range
	 */
	// i don't know if this works, but if the attacker is an assassin this should happen
	public void fight(Assassin assassin, GamePiece target) {
		boolean isIsolated = false;
		// TODO find if piece is isolated
		
		if(isIsolated) {
			target.takeDamage((int)(assassin.dealDamage() * assassin.getSneakMultiplier()));
		} else {
			target.takeDamage(assassin.dealDamage());
		}
	}
	
	public TreeMap<Integer, String> availableActions(Integer i) {
		return null;
	}
	/**
	 * @return piece at @param row, @param column
	 */
	public GamePiece getPieceAt(int i) {
		return positionsAndPieces.get(i);
	}
	
	/**
	 * @return string representation of the board and the pieces on the board
	 */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();

		return sb.toString();
	}
	
	/**
	 * @return true if @param piece can move to the space at @param row, @param column
	 * Valid move if space is not occupied by another piece
	 */
	public boolean moveIsValid(GamePiece piece, int row, int column) {

		return true;
	}
	
	/** 
	 * @return true if @param attacker is within attack range of @param defender
	 */
	public boolean canFight(GamePiece attacker, GamePiece defender) {
		return false;
	}
	
	/**
	 * When the knight dies, it is as if the horse dies and it becomes a swordsman
	 */
	public void knightDeath(Knight knight) {

	}
	
	/**
	 * Increases the attack damage of a friendly @param target, target must be within attack range
	 */
	public void castBuff(Mage mage, GamePiece target) {
		
	}
	
	/**
	 * Restores the hit points of a friendly @param target, target must be within attack range
	 * Hit points cannot exceed than maxHitPoints
	 */
	public void castHeal(Mage mage, GamePiece target) {
		
	}
	
	
	
}
