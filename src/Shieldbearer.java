import java.util.Random;

public class Shieldbearer extends GamePiece {
	
	/**
	 * Constructor for a shieldbearer at position @param row, @param column and team @param isBlueTeam
	 * Shieldbearer has preset stats.
	 */
	public Shieldbearer(boolean isBlueTeam) {
		super(isBlueTeam);
	}

	/**
	 * Shieldbearer can block some of the incoming damage, reducing the damage taken
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
