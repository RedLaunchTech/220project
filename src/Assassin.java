
public class Assassin extends GamePiece {
	
	/**
	 * Constructor for an assassin at position @param row, @param column and team @param isBlueTeam
	 * Assassin has preset stats.
	 */
	public Assassin(int row, int column, boolean isBlueTeam) {
		super(row, column, isBlueTeam);
	}

	/**
	 * Assassin can deal a stealth strike which does increased damage to isolated enemy pieces
	 */
	@Override
	public int dealDamage() {
		// TODO Auto-generated method stub
		return 0;
	}
}
