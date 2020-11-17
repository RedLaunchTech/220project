
public abstract class GamePiece {
	//protected final int maxHitPoints;
	protected int row;
	protected int column;
	protected int hitPoints;
	protected int attackDamage;
	protected int attackRange;
	protected int moveSpeed;
	protected double critChance;
	protected boolean isBlueTeam;
	protected String name;
	protected char boardSymbol;
	
	public GamePiece(int row, int column, boolean isBlueTeam) {
		this.row = row;
		this.column = column;
		this.isBlueTeam = isBlueTeam;
	}
	
//	public int getMaxHitPoints() {
//		return maxHitPoints;
//	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public char getBoardSymbol() {
		return boardSymbol;
	}
	public void setBoardSymbol(char boardSymbol) {
		this.boardSymbol = boardSymbol;
	}
	public int getRow() {
		return row;
	}
	public void setRow(int row) {
		this.row = row;
	}
	public int getColumn() {
		return column;
	}
	public void setColumn(int column) {
		this.column = column;
	}
	public int getHitPoints() {
		return hitPoints;
	}
	public void setHitPoints(int hitPoints) {
		this.hitPoints = hitPoints;
	}
	public int getAttackDamage() {
		return attackDamage;
	}
	public void setAttackDamage(int attackDamage) {
		this.attackDamage = attackDamage;
	}
	public int getAttackRange() {
		return attackRange;
	}
	public void setAttackRange(int attackRange) {
		this.attackRange = attackRange;
	}
	public int getMoveSpeed() {
		return moveSpeed;
	}
	public void setMoveSpeed(int moveSpeed) {
		this.moveSpeed = moveSpeed;
	}
	public double getCritChance() {
		return critChance;
	}
	public void setCritChance(double critChance) {
		this.critChance = critChance;
	}
	public boolean isBlueTeam() {
		return isBlueTeam;
	}
	public void setBlueTeam(boolean isBlueTeam) {
		this.isBlueTeam = isBlueTeam;
	}
	public boolean isAlive() {
		return (this.hitPoints > 0);
	}
	
	
	protected abstract void takeDamage(int damage);

	protected abstract int dealDamage();

	protected abstract void getStats();
	
}
