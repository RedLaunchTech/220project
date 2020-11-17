
public class Swordsman extends GamePiece {
	
	public Swordsman(int row, int column, boolean isBlueTeam) {
		super(row, column, isBlueTeam);
		this.hitPoints = 10;
		this.attackDamage = 3;
		this.attackRange = 1;
		this.moveSpeed = 2;
		this.critChance = 0.1;
	}
	
	public void takeDamage(int damage) {
		hitPoints -= damage;
	}

	public int dealDamage() {
		return attackDamage;
	}
	
	public void getStats() {
		if (isBlueTeam) {
			System.out.println("Team: Blue");
		}
		else {
			System.out.println("Team: Red");
		}
		
		System.out.println("Hitpoints: " + hitPoints);
		System.out.println("Attack Damage: " + attackDamage);
		System.out.println("Attack Range: " + attackRange);
		System.out.println("Move Speed: " + moveSpeed);
		System.out.println("Crit Chance: " + critChance);
	}

}
