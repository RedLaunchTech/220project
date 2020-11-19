
public class Knight extends GamePiece {
	
	/**
	 * Constructor for a knight at position @param row, @param column and team @param isBlueTeam
	 * Knight has preset stats.
	 */
	public Knight(int row, int column, boolean isBlueTeam) {
		super(row, column, isBlueTeam);
	}

	/**
	 * When the knight dies, it is as if the horse dies and it becomes a swordsman
	 */
	@Override
	public boolean isAlive() {
		// TODO Auto-generated method stub
		return super.isAlive();
	}
}
