
public class Mage extends GamePiece {
	
	/**
	 * Constructor for a mage at position @param row, @param column and team @param isBlueTeam
	 * Mage has preset stats.
	 */
	public Mage(int row, int column, boolean isBlueTeam) {
		super(row, column, isBlueTeam);
	}
	

	/**
	 * Increases the attack damage of @param target, target must be within attack range
	 */
	public void castBuff(GamePiece target) {
		
	}
	
	/**
	 * Restores the hit points of @param target, targer must be within attack range
	 * Hit points cannot exceed than maxHitPoints
	 */
	public void castHeal(GamePiece target) {
		
	}
}
