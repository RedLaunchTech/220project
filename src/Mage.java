import java.util.Random;

public class Mage extends GamePiece {
	
	private final int HEAL;
	
	/**
	 * Constructor for a mage for team @param isBlueTeam
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
		HEAL = 5;
		pieceType = "Mage";
		description = "The mage can target friendly characters to restore 5 hitpoints. They can attack up "
				+ "to 3 spaces away and if the attack is a critical hit, it will deal triple the damage!";
	}
	
	/**
	 * @return damage dealt to enemy piece, mage deals 3x damage on a crit
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
	
	/**
	 * @return amount healed to friendly piece
	 */
	public int heal() {
		return HEAL;
	}
}
