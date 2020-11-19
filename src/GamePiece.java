
public abstract class GamePiece {
	protected int maxHitPoints;
	protected int row;
	protected int column;
	protected int hitPoints;
	protected int attackDamage;
	protected int attackRange;
	protected int moveSpeed;
	protected double critChance;
	protected boolean isBlueTeam;
	protected String pieceType;
	protected char boardSymbol;
	
	/**
	 * Constructor for abstract GamePiece.
	 * All pieces will have @param row, @param column, and @param isBlueTeam
	 */
	public GamePiece(int row, int column, boolean isBlueTeam) {

	}
	
	/**
	 * @return maximum value of hit points for the piece
	 */
	public int getMaxHitPoints() {
		return maxHitPoints;
	}
	
	/**
	 * @return type of piece
	 */
	public String getPieceType() {
		return pieceType;
	}
	/**
	 * Sets value of pieceType to @param pieceType
	 */
	public void setPieceType(String pieceType) {
		this.pieceType = pieceType;
	}
	
	/**
	 * @return char of symbol that represents the piece
	 */
	public char getBoardSymbol() {
		return boardSymbol;
	}

	/**
	 * @return row position of the piece
	 */
	public int getRow() {
		return row;
	}
	/**
	 * Sets row position to @param row
	 */
	public void setRow(int row) {
		this.row = row;
	}
	
	/**
	 * @return column position of the piece
	 */
	public int getColumn() {
		return column;
	}
	/**
	 * Sets column position to @param column
	 */
	public void setColumn(int column) {
		this.column = column;
	}
	
	/**
	 * @return current hit points of the piece
	 */
	public int getHitPoints() {
		return hitPoints;
	}
	/**
	 * Sets current hit points of the piece to @param hitPoints
	 */
	public void setHitPoints(int hitPoints) {
		this.hitPoints = hitPoints;
	}
	
	/**
	 * @return attack damage of the piece
	 */
	public int getAttackDamage() {
		return attackDamage;
	}
	/**
	 * Sets attack damage of the piece to @param attackDamage
	 */
	public void setAttackDamage(int attackDamage) {
		this.attackDamage = attackDamage;
	}
	
	/**
	 * @return attack range of the piece
	 */
	public int getAttackRange() {
		return attackRange;
	}
	/**
	 * Sets attack range of the piece to @param attackRange
	 */
	public void setAttackRange(int attackRange) {
		this.attackRange = attackRange;
	}
	
	/**
	 * @return move speed of the piece
	 */
	public int getMoveSpeed() {
		return moveSpeed;
	}
	/**
	 * Sets move speed of the piece to @param moveSpeed
	 */
	public void setMoveSpeed(int moveSpeed) {
		this.moveSpeed = moveSpeed;
	}
	
	/**
	 * @return crit change of the piece
	 */
	public double getCritChance() {
		return critChance;
	}
	/**
	 * Sets crit chance of the piece to @param critChance
	 */
	public void setCritChance(double critChance) {
		this.critChance = critChance;
	}
	
	/**
	 * @return true if piece is on the blue team
	 */
	public boolean isBlueTeam() {
		return isBlueTeam;
	}
	/**
	 * Sets the team of the piece based on @param isBlueTeam
	 */
	public void setBlueTeam(boolean isBlueTeam) {
		this.isBlueTeam = isBlueTeam;
	}
	
	/**
	 * @return true if piece's hit points are > 0
	 */
	public boolean isAlive() {
		return (this.hitPoints > 0);
	}
	
	/**
	 * Piece takes damage, health points reduced by @param damage
	 */
	protected void takeDamage(int damage) {
		
	}

	/**
	 * @return damage dealt to enemy piece
	 */
	protected int dealDamage() {
		return 0;	
	}

	/**
	 * @return stats of the piece
	 */
	protected String getStats() {
		return "";
	}
	
}
