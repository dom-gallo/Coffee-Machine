package machine;

public class EspressoCoffee implements Coffee{
	
	final int WATER_COST = 250;
	final int BEAN_COST = 16;
	final int MILK_COST = 0;
	final int MONEY_COST = 4;
	
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
