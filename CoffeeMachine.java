package machine;

import java.util.Scanner;

public class CoffeeMachine {
    
    private int currentMoney;
    private int currentWaterLevel;
    private int currentMilkLevel;
    private int currentBeanLevel;
    private int currentCupCount;
    

    // State Variables
    private CurrentState currentState;
    private boolean exit = false;

    
    public CoffeeMachine(int startingMoney, int startingWater, int startingMilk, int startingBeans, int startingCups)
    {
        this.currentMoney = startingMoney;
        this.currentWaterLevel = startingWater;
        this.currentMilkLevel = startingMilk;
        this.currentBeanLevel = startingBeans;
        this.currentCupCount = startingCups;
        this.currentState = CurrentState.CHOOSE_ACTION;

    }
    
    public static void main(String[] args)
    {
        CoffeeMachine coffeeMachine = new CoffeeMachine(550, 400, 540, 120, 9);
        
        
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
                    CoffeeFactory coffeeFactory = new CoffeeFactory();
                    
                    // Parse user input.
                    Coffee coffee = coffeeFactory.createCoffee(inputChoice);
                    
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
        
        // Subtract out costs for making coffee
        this.setCurrentWaterLevel(this.getCurrentWaterLevel() - coffeeTypeToBuy.getWaterCost());
        this.setCurrentMilkLevel(this.getCurrentMilkLevel() - coffeeTypeToBuy.getMilkCost());
        this.setCurrentBeanLevel(this.getCurrentBeanLevel() - coffeeTypeToBuy.getBeanCost());
        this.setCurrentCupCount(this.getCurrentCupCount() - 1);
        // Increase money in register.
        this.setCurrentMoney(this.getCurrentMoney() + coffeeTypeToBuy.getMoneyCost());
//
//
//        this.currentWaterLevel -= coffeeTypeToBuy.getWaterCost();
//        this.currentMilkLevel -= coffeeTypeToBuy.getMilkCost();
//        this.currentBeanLevel -= coffeeTypeToBuy.getBeanCost();
//        this.currentMoney += coffeeTypeToBuy.getMoneyCost();
//        this.currentCupCount--;

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
    private void fillController(int[] ingredients) {
        
        this.setCurrentWaterLevel(this.getCurrentWaterLevel() + ingredients[0]);
        this.setCurrentMilkLevel(this.getCurrentMilkLevel() + ingredients[1]);
        this.setCurrentBeanLevel(this.getCurrentBeanLevel() + ingredients[2]);
        this.setCurrentCupCount(this.getCurrentCupCount() + ingredients[3]);
        this.resetState();
    }
    
    public void takeMoney(){
        System.out.println("I gave you " + this.currentMoney);
        this.setCurrentMoney(0);
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
    public int getCurrentMoney() {
        return currentMoney;
    }
    
    public void setCurrentMoney(int currentMoney) {
        this.currentMoney = currentMoney;
    }
    
    public int getCurrentWaterLevel() {
        return currentWaterLevel;
    }
    
    public void setCurrentWaterLevel(int currentWaterLevel) {
        this.currentWaterLevel = currentWaterLevel;
    }
    
    public int getCurrentMilkLevel() {
        return currentMilkLevel;
    }
    
    public void setCurrentMilkLevel(int currentMilkLevel) {
        this.currentMilkLevel = currentMilkLevel;
    }
    
    public int getCurrentBeanLevel() {
        return currentBeanLevel;
    }
    
    public void setCurrentBeanLevel(int currentBeanLevel) {
        this.currentBeanLevel = currentBeanLevel;
    }
    
    public int getCurrentCupCount() {
        return currentCupCount;
    }
    
    public void setCurrentCupCount(int currentCupCount) {
        this.currentCupCount = currentCupCount;
    }
}
