package machine;

public class CappCoffee implements Coffee
{
	
	final int WATER_COST = 200;
	final int MILK_COST = 100;
	final int BEAN_COST = 12;
	final int MONEY_COST = 6;

	
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
