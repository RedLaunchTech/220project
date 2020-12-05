import java.util.Random;

public class Mage extends GamePiece {
	
	/**
	 * Constructor for a mage at position @param row, @param column and team @param isBlueTeam
	 * Mage has preset stats.
	 */
	public Mage(boolean isBlueTeam) {
		super(isBlueTeam);
		maxHitPoints = 10;
		hitPoints = maxHitPoints;
		attackDamage = 5;
		attackRange = 3;
		moveSpeed = 2;
		critChance = 0.05;
		pieceType = "Mage";
		description = "The mage wields the dark arts to restore 5 hitpoints to a friendly character. "
				+ "They can attack up to 3 spaces away and if the attack is a critical hit, it will deal "
				+ "triple the damage!";
	}
	
	/**
	 * @return damage dealt to enemy piece
	 */
	@Override
	public int dealDamage() {
		Random rand = new Random();
		int damage = attackDamage;
		if(rand.nextDouble() < critChance) {
			damage = (int)(damage*3);
		}
		return damage;	
	}
	
	public double buff() {
		return 1.25;
	}
	
	public int heal() {
		return 5;
	}
}
