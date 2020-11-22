
public class Knight extends GamePiece {
	
	/**
	 * Constructor for a knight at position @param row, @param column and team @param isBlueTeam
	 * Knight has preset stats.
	 */
	public Knight(boolean isBlueTeam) {
		super(isBlueTeam);
		maxHitPoints = 30;
		hitPoints = maxHitPoints;
		attackDamage = 10;
		attackRange = 1;
		moveSpeed = 4;
		critChance = 0.2;
		pieceType = "Knight";
	}
}
