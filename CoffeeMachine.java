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
    
    // Espresso constants
    final int ESPRESSO_WATER_COST = 250;
    final int ESPRESSO_BEAN_COST = 16;
    final int ESPRESSO_MONEY_COST = 4;
    
    // Latte constants
    final int LATTE_WATER_COST = 350;
    final int LATTE_MILK_COST = 75;
    final int LATTE_BEAN_COST = 20;
    final int LATTE_MONEY_COST = 7;
    
    // Cappuccino constants
    final int CAPP_WATER_COST = 200;
    final int CAPP_MILK_COST = 100;
    final int CAPP_BEAN_COST = 12;
    final int CAPP_MONEY_COST = 6;
    
    public CoffeeMachine(int startingMoney, int startingWater, int startingMilk, int startingBeans, int startingCups) {
        this.currentMoney = startingMoney;
        this.currentWaterLevel = startingWater;
        this.currentMilkLevel = startingMilk;
        this.currentBeanLevel = startingBeans;
        this.currentCupCount = startingCups;
        this.currentState = CurrentState.CHOOSE_ACTION;
    }
    
    public static void main(String[] args) {
        CoffeeMachine coffeeMachine = new CoffeeMachine(550, 400, 540, 120, 9);
        
        Scanner sc = new Scanner(System.in);
        
        coffeeMachine.startMachine(sc);
    
    }
    
    public void startMachine(Scanner sc) {
        
        while (this.currentState != CurrentState.EXIT){
            
            switch (this.currentState) {
                case BUY:
                    System.out.println("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino: ");
                    String inputChoice = sc.nextLine();
                    
                    
                    
                    CoffeeTypes choice = inputChoice.equalsIgnoreCase("1") ? CoffeeTypes.ESPRESSO : inputChoice.equalsIgnoreCase("2") ? CoffeeTypes.LATTE : inputChoice.equalsIgnoreCase("3") ? CoffeeTypes.CAPP : CoffeeTypes.BACK;
                    if (choice != CoffeeTypes.BACK) {
                        this.buyCoffee(choice);
                    }
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
    
    public void buyCoffee(CoffeeTypes typeOfCoffee) {
        
        switch (typeOfCoffee){
            case LATTE:
                if (currentWaterLevel < LATTE_WATER_COST) {
                    System.out.println("Sorry not enough water!");
                    break;
                }
                if (currentBeanLevel < LATTE_BEAN_COST) {
                    System.out.println("Sorry not enough beans!");
                    break;
                }
                if (currentMilkLevel < LATTE_MILK_COST) {
                    System.out.println("Sorry not enough milk!");
                    break;
                }
                if (currentCupCount  == 0) {
                    System.out.println("Sorry not enough cups!");
                    break;
                }
    
                currentWaterLevel -= LATTE_WATER_COST;
                currentMilkLevel -= LATTE_MILK_COST;
                currentBeanLevel -= LATTE_BEAN_COST;
                currentMoney += LATTE_MONEY_COST;
                currentCupCount--;
    
                System.out.println("I have enough resources, making you a coffee!");
                break;
            case CAPP:
                if (currentWaterLevel < CAPP_WATER_COST) {
                    System.out.println("Sorry not enough water!");
                    break;
                }
                if (currentBeanLevel < CAPP_BEAN_COST) {
                    System.out.println("Sorry not enough beans!");
                    break;
                }
                if (currentMilkLevel < CAPP_MILK_COST) {
                    System.out.println("Sorry not enough milk!");
                    break;
                }
                if (currentCupCount == 0) {
                    System.out.println("Sorry not enough cups");
                    break;
                }
                currentWaterLevel -= CAPP_WATER_COST;
                currentMilkLevel -= CAPP_MILK_COST;
                currentBeanLevel -= CAPP_BEAN_COST;
                currentMoney += CAPP_MONEY_COST;
                currentCupCount--;
    
                System.out.println("I have enough resources, making you a coffee!");
                break;
            case ESPRESSO:
                if (currentWaterLevel < ESPRESSO_WATER_COST) {
                    System.out.println("Sorry not enough water!");
                    break;
                }
                if (currentBeanLevel < ESPRESSO_BEAN_COST) {
                    System.out.println("Sorry not enough beans!");
                    break;
                }
                if (currentCupCount == 0) {
                    System.out.println("Sorry not enough cups!");

                    break;
                }
                currentWaterLevel -= ESPRESSO_WATER_COST;
                currentBeanLevel -= ESPRESSO_BEAN_COST;
                currentMoney += ESPRESSO_MONEY_COST;
                currentCupCount--;
                System.out.println("I have enough resources, making you a coffee!");

                break;
        }
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
