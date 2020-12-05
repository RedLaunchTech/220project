
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
		description = "The knight is a horse mounted unit that can move quickly and deal large amounts "
				+ "of damage. When a knight piece dies, it loses its horse and becomes a lowly swordsman.";
	}
}
