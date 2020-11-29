
public class Mage extends GamePiece {
	
	/**
	 * Constructor for a mage at position @param row, @param column and team @param isBlueTeam
	 * Mage has preset stats.
	 */
	public Mage(boolean isBlueTeam) {
		super(isBlueTeam);
		maxHitPoints = 10;
		hitPoints = maxHitPoints;
		attackDamage = 4;
		attackRange = 3;
		moveSpeed = 2;
		critChance = 0.2;
		pieceType = "Mage";
	}
	
	public double buff() {
		return 1.25;
	}
	
	public int heal() {
		return 5;
	}
}
