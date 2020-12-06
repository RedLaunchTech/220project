
public class Assassin extends GamePiece {
	
	private final double SNEAK_MULT;
	/**
	 * Constructor for an assassin for team @param isBlueTeam
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
		SNEAK_MULT = 1.25;
		pieceType = "Assassin";
		description = "The asassin has the ability to sneak strike enemies. A sneak strike will deal "
				+ "1.25x the damage if the enemy piece is not adjacent to another enemy piece.";

	}
	
	/**
	 * @return amount of damage dealt to enemy, multiplies damage by SNEAK_MULT if @param isIsolated is true
	 */
	public int dealDamage(boolean isIsolated) {
		if (isIsolated) {
			return (int)(dealDamage() * SNEAK_MULT);
		} else {
			return dealDamage();
		}
	}

}
