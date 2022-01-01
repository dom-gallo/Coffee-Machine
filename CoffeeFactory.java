package machine;

public class CoffeeFactory
{
	public Coffee createCoffee(String preparation)
	{
		switch (preparation)
		{
			case "1":
				return new EspressoCoffee();
			case "2":
				return new LatteCoffee();
			case "3":
				return new CappCoffee();
			default:
				return null;
		}
	}
}
