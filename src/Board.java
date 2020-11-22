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
	
	public TreeMap<Integer, String> getPositionsAndPieces() {
		TreeMap<Integer, String> positionsAndPieceTypes = new TreeMap();
		for (Integer i : this.positionsAndPieces.keySet()) {
			positionsAndPieceTypes.put(i, positionsAndPieces.get(i).formatName());
		}
		return positionsAndPieceTypes;
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
		Integer red = 144;
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
	
	// comment
	public TreeMap<Integer, String> availableActions(Integer index) {
		TreeMap<Integer, String> availableActions = new TreeMap();
		GamePiece piece = this.getPieceAt(index);
		int moveSpeed = piece.getMoveSpeed();
		int checks = (moveSpeed*4 + (moveSpeed-1)*4);
		for (int i = 0; i < moveSpeed; ++i) {
			if ((index + i) % width == 0) {
				break;
			}
			else if ((index + i) > (width*height)) {
				break;
			}
			else {
				StringBuilder sb = new StringBuilder();
				if (positionsAndPieces.containsKey(index+i)) {
					if (isFriend(index, index+i) && piece.getPieceType() == "Mage") {
						sb.append("h");
					}
					else if (!isFriend(index, index+i)) {
						sb.append("a");
					}
					sb.append(positionsAndPieces.get(index+i).formatName());
					availableActions.put(index+i, sb.toString());
				}
				else {
					availableActions.put(index+i, "m");
				}
			}
		}
		return  availableActions;
	}
	
	private boolean isFriend(Integer i, Integer j) {
		GamePiece pieceOne = getPieceAt(i);
		GamePiece pieceTwo = getPieceAt(j);
		if (pieceOne.isBlueTeam() == pieceTwo.isBlueTeam()) {
			return true;
		}
		else {
			return false;
		}
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
