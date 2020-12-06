import java.util.Random;

public abstract class GamePiece {

	protected boolean isBlueTeam;
	protected int maxHitPoints;
	protected int hitPoints;
	protected int attackDamage;
	protected int attackRange;
	protected int moveSpeed;
	protected double critChance;
	protected String pieceType;
	protected String description;
	protected boolean isMovable;
	
	/**
	 * Constructor for abstract GamePiece for team @param isBlueTeam
	 */
	public GamePiece(boolean isBlueTeam) {
		this.isBlueTeam = isBlueTeam;
	}
	
	/**
	 * @return true if piece is on the blue team
	 */
	public boolean isBlueTeam() {
		return isBlueTeam;
	}
	
	/**
	 * @return maximum value of hit points for the piece
	 */
	public int getMaxHitPoints() {
		return maxHitPoints;
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
	 * @return type of piece
	 */
	public String getPieceType() {
		return pieceType;
	}
	
	/**
	 * @return description of piece and its functions
	 */
	public String getDescription() {
		return description;
	}
	
	/**
	 * Set isMovable with @param isMovable
	 */
	public void canMove(boolean isMovable) {
		this.isMovable = isMovable;
	}
	/**
	 * @return isMovable if the piece can still be moved this turn
	 */
	public boolean isMoveable() {
		return isMovable;
	}
	
	/**
	 * @return true if piece's hit points are > 0
	 */
	public boolean isAlive() {
		return (hitPoints > 0);
	}
	
	/**
	 * Piece takes damage, health points reduced by @param damage
	 */
	public void takeDamage(int damage) {
		this.hitPoints -= damage;
	}

	/**
	 * @return damage dealt to enemy piece
	 */
	public int dealDamage() {
		Random rand = new Random();
		int damage = attackDamage;
		if(rand.nextDouble() < critChance) {
			damage = (int)(damage*1.5);
		}
		return damage;	
	}
	
	/**
	 * @return formatted name with the combination of the team and type of the piece
	 */
	public String formatName() {
		String formattedName;
		if(isBlueTeam) {
			formattedName = "b" + pieceType;
		} else {
			formattedName = "r" + pieceType;
		}
		
		return formattedName;
	}
	
}
