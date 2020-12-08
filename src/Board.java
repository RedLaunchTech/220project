import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.TreeMap;

public class Board {
	private final int width;
	private final int height;
	private final ArrayList<String> PIECETYPES ;
	private ArrayList<GamePiece> gamePieces;
	private TreeMap<Integer, GamePiece> positionsAndPieces;

	public Board(int height, int width) {
		this.width = width;
		this.height = height;
		PIECETYPES = new ArrayList<>(List.of("Shieldbearer","Assassin","Archer","Swordsman","Knight",
				"Mage","Swordsman","Archer","Assassin","Shieldbearer"));
		gamePieces = new ArrayList<>();
		positionsAndPieces = new TreeMap<>();
	}
	
	/**
	 * 
	 * @return TreeMap of Integer keys with String values. The key corresponds to spaces on the board and the value
	 * corresponds to a String to be parsed and interpreted by Game and Gui
	 */
	public TreeMap<Integer, String> getPositionsAndPieces() {
		TreeMap<Integer, String> positionsAndPieceTypes = new TreeMap<>();
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
	
	/**
	 * Generates rosters for teams and places those rosters on hard coded locations on the board
	 */
	public void initializeGame() {
		this.generateRoster(false);
		this.generateRoster(true);
		this.placePieces();
	}

	/**
	 * Generates roster of pieces for @param currPlayer, adding the pieces to the ArrayList gamePieces
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
				Shieldbearer newPiece = new Shieldbearer(currPlayer);
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
		Integer red = 149;
		for (GamePiece piece : gamePieces) {
			if (piece.isBlueTeam) {
				positionsAndPieces.put(blue, piece);
				blue += 15;
			}
			else {
				positionsAndPieces.put(red, piece);
				red -= 15;
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
	public void movePiece(Integer startPosition, Integer endPosition) {
		for (Integer i : positionsAndPieces.keySet()) {
			if (i.equals(startPosition)) {
				GamePiece value = positionsAndPieces.get(startPosition);
				positionsAndPieces.put(endPosition, value);
				positionsAndPieces.remove(i);
				break;
			}
		}
	}
	
	/**
	 * Two pieces "fight". The piece at @param attacker deals damage to the piece at @param attackee.
	 */
	public void fight(Integer attacker, Integer attackee) {
		if(positionsAndPieces.get(attacker).getPieceType().equals("Assassin")) {
			stealthStrike(attacker, attackee);
		} else {
			positionsAndPieces.get(attackee).takeDamage(positionsAndPieces.get(attacker).dealDamage());
		}
		
		if (!positionsAndPieces.get(attackee).isAlive()) {
			if (positionsAndPieces.get(attackee).getPieceType().equals("Knight")) {
				knightDeath(attackee, positionsAndPieces.get(attackee).isBlueTeam());
			} else {
				this.removePiece(attackee);
			}
		}
	}
	
	/**
	 * Assassin piece at @param attacker deals extra damage if @param attackee has no friendly pieces 
	 * within its movement range.
	 */
	public void stealthStrike(Integer attacker, Integer attackee) {
		Assassin assassin = (Assassin)positionsAndPieces.get(attacker);
		GamePiece target = positionsAndPieces.get(attackee);		
		
		target.takeDamage(assassin.dealDamage(isIsolated(attackee)));
	}
	
	/**
	 * @return true if @param man has no friendly pieces within one space
	 */
	public boolean isIsolated(Integer man) {
		//right
		if (!(man + 1 % width == 0)) {
			if (positionsAndPieces.containsKey(man + 1)) {
				if (isFriend(man, man + 1)) {
					return false;
				}
			}
		}

		//left
		if (!((man - 1) % width == (width - 1))) {
			if (positionsAndPieces.containsKey(man - 1)) {
				if (isFriend(man, man - 1)) {
					return false;
				}
			}
		} 
		
		//down
		if (!((man + width) > width*height)) {
			if(positionsAndPieces.containsKey(man + width)) {
				if(isFriend(man, man + width)) {
					return false;
				}
			}
		}
		
		//up
		if (!((man - width) < 0)) {
			if(positionsAndPieces.containsKey(man - width)) {
				if(isFriend(man, man - width)) {
					return false;
				}
			}
		}
		
		return true;
	}
	
	/**
	 * 
	 * @param index is used to locate the piece in question and retrieve its available actions
	 * @return TreeMap with Integer key tied to locations on the board and String values that are parsed in other classes
	 * and are used to tell the other classes what actions can be taken at the index of the Integer key
	 */
	public TreeMap<Integer, String> availableActions(Integer index) {
		TreeMap<Integer, String> availableActions = new TreeMap<>();
		availableActions.putAll(this.getMoves(index));
		availableActions.putAll(this.getInteractions(index));
		return availableActions;
	}
	
	/**
	 * 
	 * @param index is used to locate the piece in question and retrieve its available moves
	 * @return TreeMap of Integer key that contains the unoccupied board locations that are within the piece's move speed
	 * and has value String parsed elsewhere to communicate that the piece can move to the location at the key's Integer
	 */
	private TreeMap<Integer, String> getMoves(Integer index) {
		TreeMap<Integer, String> availableMoves = new TreeMap<>();
		GamePiece piece = this.getPieceAt(index);
		int moveSpeed = piece.getMoveSpeed();
		
		// right
		for (int i = 0; i < moveSpeed; ++i) {
			if ((index + i + 1) % width == 0) {
				break;
			}
			else if ((index + i +1) > (width*height)) {
				break;
			}
			else {
				if (!positionsAndPieces.containsKey(index+i+1)) {
					availableMoves.put(index+i+1, "m right");
				}
			}
		}
		
		// left
		for (int i = 0; i < moveSpeed; ++i) {
			if ((index - (i+1)) % width == width-1) {
				break;
			}
			else if ((index - (i +1)) < 0) {
				break;
			}
			else {
				if (!positionsAndPieces.containsKey(index - (i+1))) {
					availableMoves.put(index - (i+1), "m left");
				}
			}
		}
		
		// down
		for (int i = 0; i < moveSpeed; ++i) {
			if ((index + (i+1)*width) > (width*height)) {
				break;
			}
			else {
				if (!positionsAndPieces.containsKey(index + (i+1)*width)) {
					availableMoves.put(index + (i+1)*width, "m down");
				}
			}
		}
		
		// up
		for (int i = 0; i < moveSpeed; ++i) {
			if ((index - (i+1)*width) < 0) {
				break;
			}
			else {
				if (!positionsAndPieces.containsKey(index - (i+1)*width)) {
					availableMoves.put(index - (i+1)*width, "m up");
				}
			}
		}
		
		int colIndex = moveSpeed - 1;
		int rowIndex;
		
		// down and right
		while (colIndex != 0) {
			rowIndex = colIndex;
			if ((index + (moveSpeed-colIndex) + width*rowIndex) % width == 0) {
				break;
			}
			
			while (rowIndex != 0) {
				if (index + (moveSpeed-colIndex) + width*rowIndex > width*height) {
					--rowIndex;
					continue;
				}
				if (!positionsAndPieces.containsKey(index + (moveSpeed-colIndex) + width*rowIndex)) {
					availableMoves.put(index + (moveSpeed-colIndex) + width*rowIndex, "m down right");
				}
				--rowIndex;
			}
			--colIndex;
		}
		
		colIndex = moveSpeed - 1;
		
		// up and right
		while (colIndex != 0) {
			rowIndex = colIndex;
			if ((index + (moveSpeed-colIndex) - width*rowIndex) % 15 == 0) {
				break;
			}
			
			while (rowIndex != 0) {
				if (index + (moveSpeed-colIndex) - width*rowIndex < 0) {
					--rowIndex;
					continue;
				}
				if (index + (moveSpeed-colIndex) + 1 > width*height) { // line might be bugged, "+1" in if shouldn't be there? But only works if it is
					break;
				}
				if (!positionsAndPieces.containsKey(index + (moveSpeed-colIndex) - width*rowIndex)) {
					availableMoves.put(index + (moveSpeed-colIndex) - width*rowIndex, "m up right");
				}
				
				--rowIndex;
			}
			--colIndex;
		}
		
		colIndex = moveSpeed - 1;
		
		// down and left
		while (colIndex != 0) {
			rowIndex = colIndex;
			if ((index - (moveSpeed-colIndex) + width*rowIndex) % 15 == width-1) {
				break;
			}
			
			while (rowIndex != 0) {
				if (index - (moveSpeed-colIndex) + width*rowIndex > width*height) {
					--rowIndex;
					continue;
				}
				if (index - (moveSpeed - colIndex) < 0) {
					break;
				}
				if (!positionsAndPieces.containsKey(index - (moveSpeed-colIndex) + width*rowIndex)) {
					availableMoves.put(index - (moveSpeed-colIndex) + width*rowIndex, "m down left");
				}
				
				--rowIndex;
			}
			--colIndex;
		}
		
		colIndex = moveSpeed - 1;
		
		// up and left
		while (colIndex != 0) {
			rowIndex = colIndex;
			if ((index - (moveSpeed-colIndex) - width*rowIndex) % 15 == width-1) {
				break;
			}
			
			while (rowIndex != 0) {
				if (index - (moveSpeed-colIndex) - width*rowIndex < 0) {
					--rowIndex;
					continue;
				}
				if (!positionsAndPieces.containsKey(index - (moveSpeed-colIndex) - width*rowIndex)) {
					availableMoves.put(index - (moveSpeed-colIndex) - width*rowIndex, "m up left");
				}
				
				--rowIndex;
			}
			--colIndex;
		}
		
		return  availableMoves;
	}
	
	/**
	 * 
	 * @param index is used to locate the piece in question and retrieve its available interactions
	 * @return TreeMap of Integer key that contains the occupied board locations that are within the piece's attack range
	 * and has value String parsed elsewhere to communicate that the piece can interact witho the location at the key's
	 *  Integer
	 */
	private TreeMap<Integer, String> getInteractions(Integer index) {
		TreeMap<Integer, String> availableInteractions = new TreeMap<>();
		GamePiece piece = this.getPieceAt(index);
		int attackRange = piece.getAttackRange();
		
		// right
		for (int i = 0; i < attackRange; ++i) {
			if ((index + i + 1) % width == 0) {
				break;
			} else if ((index + i + 1) > (width * height)) {
				break;
			} else {
				StringBuilder sb = new StringBuilder();
				if (positionsAndPieces.containsKey(index + i + 1)) {
					if (isFriend(index, index + i + 1) && piece.getPieceType() == "Mage") {
						sb.append("h");
					} else if (!isFriend(index, index + i + 1)) {
						sb.append("a");
					}
					sb.append(positionsAndPieces.get(index + i + 1).formatName());
					availableInteractions.put(index + i + 1, sb.toString());
				}
			}
		}
		
		// left
		for (int i = 0; i < attackRange; ++i) {
			if ((index - (i + 1)) % width == width-1) {
				break;
			} else if ((index - (i + 1)) < 0) {
				break;
			} else {
				StringBuilder sb = new StringBuilder();
				if (positionsAndPieces.containsKey(index - (i + 1))) {
					if (isFriend(index, (index - (i + 1))) && piece.getPieceType() == "Mage") {
						sb.append("h");
					} else if (!isFriend(index, (index - (i + 1)))) {
						sb.append("a");
					}
					sb.append(positionsAndPieces.get((index - (i + 1))).formatName());
					availableInteractions.put((index - (i + 1)), sb.toString());
				}
			}
		}
		
		// down
		for (int i = 0; i < attackRange; ++i) {
			if ((index + (i+1)*width) > (width*height)) {
				break;
			}
			else {
				StringBuilder sb = new StringBuilder();
				if (positionsAndPieces.containsKey(index + (i+1)*width)) {
					if (isFriend(index, index + (i+1)*width) && piece.getPieceType() == "Mage") {
						sb.append("h");
					} else if (!isFriend(index, index + (i+1)*width)) {
						sb.append("a");
					}
					sb.append(positionsAndPieces.get(index + (i+1)*width).formatName());
					availableInteractions.put(index + (i+1)*width, sb.toString());
				}
			}
		}
		
		// up
		for (int i = 0; i < attackRange; ++i) {
			if ((index - (i+1)*width) < 0) {
				break;
			}
			else {
				StringBuilder sb = new StringBuilder();
				if (positionsAndPieces.containsKey(index - (i+1)*width)) {
					if (isFriend(index, index - (i+1)*width) && piece.getPieceType() == "Mage") {
						sb.append("h");
					} else if (!isFriend(index, index - (i+1)*width)) {
						sb.append("a");
					}
					sb.append(positionsAndPieces.get(index - (i+1)*width).formatName());
					availableInteractions.put(index - (i+1)*width, sb.toString());
				}
			}
		}
		
		int colIndex = attackRange - 1;
		int rowIndex;
		
		// down and right
		while (colIndex != 0) {
			rowIndex = colIndex;
			if ((index + (attackRange-colIndex) + width*rowIndex) % 15 == 0) {
				break;
			}
			
			while (rowIndex != 0) {
				if (index + (attackRange-colIndex) + width*rowIndex > width*height) {
					--rowIndex;
					continue;
				}
				StringBuilder sb = new StringBuilder();
				if (positionsAndPieces.containsKey(index + (attackRange-colIndex) + width*rowIndex)) {
					if (isFriend(index, index + (attackRange-colIndex) + width*rowIndex) && piece.getPieceType() == "Mage") {
						sb.append("h");
					} else if (!isFriend(index, index + (attackRange-colIndex) + width*rowIndex)) {
						sb.append("a");
					}
					sb.append(positionsAndPieces.get(index + (attackRange-colIndex) + width*rowIndex).formatName());
					availableInteractions.put(index + (attackRange-colIndex) + width*rowIndex, sb.toString());
				}
				--rowIndex;
			}
			--colIndex;
		}
		
		colIndex = attackRange - 1;
		
		// up and right
		while (colIndex != 0) {
			rowIndex = colIndex;
			if ((index + (attackRange-colIndex) - width*rowIndex) % 15 == 0) {
				break;
			}
			
			while (rowIndex != 0) {
				if (index + (attackRange-colIndex) - width*rowIndex < 0) {
					--rowIndex;
					continue;
				}
				if (index + (attackRange-colIndex) + 1 > width*height) { // line might be bugged, "+1" in if shouldn't be there? But only works if it is
					break;
				}
				StringBuilder sb = new StringBuilder();
				if (positionsAndPieces.containsKey(index + (attackRange-colIndex) - width*rowIndex)) {
					if (isFriend(index, index + (attackRange-colIndex) - width*rowIndex) && piece.getPieceType() == "Mage") {
						sb.append("h");
					} else if (!isFriend(index, index + (attackRange-colIndex) - width*rowIndex)) {
						sb.append("a");
					}
					sb.append(positionsAndPieces.get(index + (attackRange-colIndex) - width*rowIndex).formatName());
					availableInteractions.put(index + (attackRange-colIndex) - width*rowIndex, sb.toString());
				}
				
				--rowIndex;
			}
			--colIndex;
		}
		
		colIndex = attackRange - 1;

		// down and left
		while (colIndex != 0) {
			rowIndex = colIndex;
			if ((index - (attackRange - colIndex) + width * rowIndex) % 15 == width-1) {
				break;
			}

			while (rowIndex != 0) {
				if (index - (attackRange - colIndex) + width * rowIndex > width * height) {
					--rowIndex;
					continue;
				}
				if (index - (attackRange - colIndex) < 0) {
					break;
				}
				StringBuilder sb = new StringBuilder();
				if (positionsAndPieces.containsKey(index - (attackRange - colIndex) + width * rowIndex)) {
					if (isFriend(index, index - (attackRange - colIndex) + width * rowIndex) && piece.getPieceType() == "Mage") {
						sb.append("h");
					} else if (!isFriend(index, index - (attackRange - colIndex) + width * rowIndex)) {
						sb.append("a");
					}
					sb.append(positionsAndPieces.get(index - (attackRange - colIndex) + width * rowIndex).formatName());
					availableInteractions.put(index - (attackRange - colIndex) + width * rowIndex, sb.toString());
				}

				--rowIndex;
			}
			--colIndex;
		}
		
		colIndex = attackRange - 1;
		
		// up and left
		while (colIndex != 0) {
			rowIndex = colIndex;
			if ((index - (attackRange-colIndex) + width*rowIndex) % 15 == width-1) {
				break;
			}
			
			while (rowIndex != 0) {
				if (index - (attackRange-colIndex) - width*rowIndex < 0) {
					--rowIndex;
					continue;
				}
				StringBuilder sb = new StringBuilder();
				if (positionsAndPieces.containsKey(index - (attackRange-colIndex) - width*rowIndex)) {
					if (isFriend(index, index - (attackRange-colIndex) - width*rowIndex) && piece.getPieceType() == "Mage") {
						sb.append("h");
					} else if (!isFriend(index, index - (attackRange-colIndex) - width*rowIndex)) {
						sb.append("a");
					}
					sb.append(positionsAndPieces.get(index - (attackRange-colIndex) - width*rowIndex).formatName());
					availableInteractions.put(index - (attackRange-colIndex) - width*rowIndex, sb.toString());
				}
				
				--rowIndex;
			}
			--colIndex;
		}

		return availableInteractions;
	}

	/**
	 * 
	 * @param i is an Integer index of a board space containing a piece
	 * @param j is an Integer index of a board space containing a piece
	 * @return true if the piece at @param i is on the same team as the piece at @param j
	 */
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
	 * @return piece at @param i
	 */
	public GamePiece getPieceAt(int i) {
		return positionsAndPieces.get(i);
	}

	/**
	 * When the knight at @param i dies, it is as if the horse dies and it becomes a swordsman
	 */
	public void knightDeath(Integer i, boolean team) {
		Swordsman swordman = new Swordsman(team);
		positionsAndPieces.put(i, swordman);
	}
	
	/**
	 * Restores the hit points of a friendly @param target, target must be within attack range
	 * Hit points cannot exceed than maxHitPoints
	 */
	public void castHeal(Integer healer, Integer healee) {
		positionsAndPieces.get(healee).setHitPoints((positionsAndPieces.get(healee).getHitPoints() + ((Mage)positionsAndPieces.get(healer)).heal()));
		if (positionsAndPieces.get(healee).getHitPoints() > positionsAndPieces.get(healee).getMaxHitPoints()) {
			positionsAndPieces.get(healee).setHitPoints(positionsAndPieces.get(healee).getMaxHitPoints());
		}
	}
}