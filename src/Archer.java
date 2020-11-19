
public class Archer extends GamePiece {
	
	/**
	 * Constructor for an archer at position @param row, @param column and team @param isBlueTeam
	 * Archer has preset stats.
	 */
	public Archer(int row, int column, boolean isBlueTeam) {
		super(row, column, isBlueTeam);
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
