
public class Assassin extends GamePiece {
	
	private final double sneakMultiplier;
	/**
	 * Constructor for an assassin at position @param row, @param column and team @param isBlueTeam
	 * Assassin has preset stats.
	 */
	public Assassin(boolean isBlueTeam) {
		super(isBlueTeam);
		maxHitPoints = 10;
		hitPoints = maxHitPoints;
		attackDamage = 8;
		attackRange = 1;
		moveSpeed = 3;
		critChance = 0.4;
		pieceType = "Assassin";
		description = "The asassin has the ability to sneak strike enemies. A sneak strike will deal "
				+ "1.25x the damage if the enemy piece is not adjacent to another enemy piece.";
		sneakMultiplier = 1.25;
	}
	
	public double getSneakMultiplier() {
		return sneakMultiplier;
	}

}
