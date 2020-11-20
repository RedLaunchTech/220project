
public class Swordsman extends GamePiece {
	
	/**
	 * Constructor for a swordsman at position @param row, @param column and team @param isBlueTeam
	 * Swordsman has preset stats.
	 */
	public Swordsman(boolean isBlueTeam) {
		super(isBlueTeam);
		maxHitPoints = 20;
		hitPoints = maxHitPoints;
		attackDamage = 8;
		attackRange = 1;
		moveSpeed = 2;
		critChance = 0.2;
		pieceType = "Swordsman";
	}
}
