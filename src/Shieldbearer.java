
public class Shieldbearer extends GamePiece {
	
	/**
	 * Constructor for a shieldbearer at position @param row, @param column and team @param isBlueTeam
	 * Shieldbearer has preset stats.
	 */
	public Shieldbearer(int row, int column, boolean isBlueTeam) {
		super(row, column, isBlueTeam);
	}

	/**
	 * Shieldbearer can block some of the incoming damage, reducing the damage taken
	 */
	@Override
	public void takeDamage(int damage) {
		// TODO Auto-generated method stub
		
	}
}
