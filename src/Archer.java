
public class Archer extends GamePiece {
	
	/**
	 * Constructor for an archer at position @param row, @param column and team @param isBlueTeam
	 * Archer has preset stats.
	 */
	public Archer(boolean isBlueTeam) {
		super(isBlueTeam);
		maxHitPoints = 15;
		hitPoints = maxHitPoints;
		attackDamage = 8;
		attackRange = 4;
		moveSpeed = 2;
		critChance = 0.2;
		pieceType = "Archer";
	}

	/**
	 * Archer can deal damage at range
	 */
	@Override
	public int dealDamage() {
		// TODO Auto-generated method stub
		return 0;
	}
}
