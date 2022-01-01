package machine;

import java.util.Scanner;

public class CoffeeMachine {
    
    private int currentMoney;
    private int currentWaterLevel;
    private int currentMilkLevel;
    private int currentBeanLevel;
    private int currentCupCount;
    
    final private Coffee LatteObject;
    final private Coffee EspressoObject;
    final private Coffee CappObject;
    
    // State Variables
    private CurrentState currentState;
    private boolean exit = false;

    
    public CoffeeMachine(int startingMoney, int startingWater, int startingMilk, int startingBeans, int startingCups,
                         Coffee Latte, Coffee Espresso, Coffee Capp)
    {
        this.currentMoney = startingMoney;
        this.currentWaterLevel = startingWater;
        this.currentMilkLevel = startingMilk;
        this.currentBeanLevel = startingBeans;
        this.currentCupCount = startingCups;
        this.currentState = CurrentState.CHOOSE_ACTION;
        this.LatteObject =  Latte;
        this.EspressoObject = Espresso;
        this.CappObject = Capp;
    }
    
    public static void main(String[] args)
    {
        // Coffee Factory Method
        CoffeeFactory coffeeFactory = new CoffeeFactory();
        //
        CoffeeMachine coffeeMachine = new CoffeeMachine(550, 400, 540, 120, 9,
                coffeeFactory.createCoffee("latte"), coffeeFactory.createCoffee("espresso"),
                coffeeFactory.createCoffee("capp"));
        
        
        Scanner sc = new Scanner(System.in);
        
        //
        coffeeMachine.startMachine(sc);
    
    }
    
    public void startMachine(Scanner sc)
    {
        
        while (this.currentState != CurrentState.EXIT)
        {
            
            switch (this.currentState)
            {
                case BUY:
                    System.out.println("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino: ");
                    String inputChoice = sc.nextLine();
                    // Parse user input.
                    Coffee coffee = this.handleBuyInput(inputChoice);
                    
                    // Check for case where "back" or anything else is typed.
                    if(coffee == null)
                    {
                        this.resetState();
                        break;
                    }
                    
                    this.buyCoffee(coffee);
                    
                    this.resetState();
                    break;
                case FILL:
                    int[] itemsToFill = this.fillHandler(sc);
                    this.fillController(itemsToFill);
                    break;
                case TAKE:
                    this.takeMoney();
                    break;
                case REMAINING:
                    this.getRemaining();
                    break;
                case EXIT:
                    this.handleExit();
                    break;
                case CHOOSE_ACTION:
                    this.handleChooseAction(sc);
                    break;
                default:
                    this.resetState();
                    break;
            }
        }
    }
    public Coffee handleBuyInput(String argument)
    {
        
        // depending on input, point to correct instance field object.
    
        if (inputChoice.equalsIgnoreCase("1"))
        {
            return EspressoObject;
        } else if (inputChoice.equalsIgnoreCase("2"))
        {
            return LatteObject;
        } else if (inputChoice.equalsIgnoreCase("3"))
        {
            return CappObject;
        }
        return null;
    }
    
    public void handleExit() {
        this.currentState = CurrentState.EXIT;
    }
    
    public void handleChooseAction(Scanner sc) {
        
        System.out.println("Write action (buy, fill, take, remaining, exit): ");
        
        String input = sc.nextLine();
        
        // Parse user input for STATE ENUM
        this.currentState = input.equalsIgnoreCase("buy") ? CurrentState.BUY : input.equalsIgnoreCase("fill") ?
                CurrentState.FILL : input.equalsIgnoreCase("take") ? CurrentState.TAKE : input.equalsIgnoreCase("remaining") ?
                CurrentState.REMAINING : input.equalsIgnoreCase("exit") ? CurrentState.EXIT : CurrentState.CHOOSE_ACTION;
        
        // Change Object State.
        
    }
    
    public void buyCoffee(Coffee coffeeTypeToBuy)
    {
        
        
        if (currentWaterLevel < coffeeTypeToBuy.getWaterCost())
        {
            System.out.println("Sorry not enough water!");
            return;
           
        }
        if (currentBeanLevel < coffeeTypeToBuy.getBeanCost())
        {
            System.out.println("Sorry not enough beans!");
            return;
        }
        if (currentMilkLevel < coffeeTypeToBuy.getBeanCost())
        {
            System.out.println("Sorry not enough milk!");
            return;
        }
        if (currentCupCount  == 0)
        {
            System.out.println("Sorry not enough cups!");
            return;
        }
        
        this.currentWaterLevel -= coffeeTypeToBuy.getWaterCost();
        this.currentMilkLevel -= coffeeTypeToBuy.getMilkCost();
        this.currentBeanLevel -= coffeeTypeToBuy.getBeanCost();
        this.currentMoney += coffeeTypeToBuy.getMoneyCost();
        this.currentCupCount--;

        System.out.println("I have enough resources, making you a coffee!");
    }
    public int[] fillHandler(Scanner sc){
        
        System.out.println("Write how many ml of water you want to add: ");
        int waterToAdd = sc.nextInt();
        System.out.println("Write how many ml of milk you want to add: ");
        int milkToAdd = sc.nextInt();
        System.out.println("Write how many grams of coffee beans you want to add: ");
        int beansToAdd = sc.nextInt();
        System.out.println("Write how many disposable cups of coffee you want to add: ");
        int cupsToAdd = sc.nextInt();
        int[] itemsToFill = {waterToAdd, milkToAdd,beansToAdd,cupsToAdd};
        
        return itemsToFill;
        
        
    }
    private void fillController(int[] ingrediants) {
        this.currentWaterLevel += ingrediants[0];
        this.currentMilkLevel += ingrediants[1];
        this.currentBeanLevel += ingrediants[2];
        this.currentCupCount += ingrediants[3];
        this.resetState();
    }
    
    public void takeMoney(){
        System.out.println("I gave you " + this.currentMoney);
        this.currentMoney = 0;
        this.resetState();
    }

    public void getRemaining(){
        System.out.println("The coffee machine has:\n" +
                this.currentWaterLevel + " ml of water\n" +
                this.currentMilkLevel + " ml of milk\n" +
                this.currentBeanLevel + " g of coffee beans\n" +
                this.currentCupCount + " disposable cups\n" +
                "$" + this.currentMoney+ " of money");
        this.resetState();
    }
    public enum CoffeeTypes {
        LATTE,
        CAPP,
        ESPRESSO,
        BACK
    }
    public enum CurrentState {
        BUY,
        FILL,
        TAKE,
        REMAINING,
        EXIT,
        CHOOSE_ACTION
    }
    public void resetState() {
        this.currentState = CurrentState.CHOOSE_ACTION;
    }
}
