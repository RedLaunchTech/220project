import java.util.Random;

public class Shieldbearer extends GamePiece {
	
	/**
	 * Constructor for a shieldbearer for team @param isBlueTeam
	 * Shieldbearer has preset stats.
	 */
	public Shieldbearer(boolean isBlueTeam) {
		//TODO balance stats please
		super(isBlueTeam);
		maxHitPoints = 25;
		hitPoints = maxHitPoints;
		attackDamage = 8;
		attackRange = 1;
		moveSpeed = 1;
		critChance = 0.2;
		pieceType = "Shieldbearer";
		description = "The shieldbearer has a 50% chance to block some incoming damage, cutting the "
				+ "damage taken in half.";
	}

	/**
	 * Shieldbearer has a 50% chance to block some of the incoming @param damage, 
	 * reducing the damage taken to half
	 */
	@Override
	public void takeDamage(int damage) {
		Random rand = new Random();
		if(rand.nextDouble() < 0.5) {
			hitPoints -= damage / 2;
		} else {
			hitPoints -= damage;
		}
	}
}
