package machine;

public class LatteCoffee implements Coffee{
	
	final int WATER_COST = 350;
	final int MILK_COST = 75;
	final int BEAN_COST = 20;
	final int MONEY_COST = 7;

	
	@Override
	public void buy() {
	
	}
	
	@Override
	public int getWaterCost() {
		return this.WATER_COST;
	}
	
	@Override
	public int getMilkCost() {
		return this.MILK_COST;
	}
	
	@Override
	public int getBeanCost() {
		return this.BEAN_COST;
	}
	
	@Override
	public int getMoneyCost() {
		return this.MONEY_COST;
	}
	
}
