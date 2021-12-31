package machine;

public class CoffeeFactory
{
	public Coffee createCoffee(String preparation)
	{
		if (preparation == null || preparation.isEmpty())
		{
			return null;
		}
		if ("latte".equalsIgnoreCase(preparation))
		{
			return new LatteCoffee();
		}
		if ("espresso".equalsIgnoreCase(preparation))
		{
			return new EspressoCoffee();
		}
		if ("capp".equalsIgnoreCase(preparation))
		{
			return new CappCoffee();
		}
		return null;
	}
}
