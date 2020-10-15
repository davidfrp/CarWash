import java.io.IOException;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Machine {

    private final FileManipulator io;
    private final Database database;
    private final Statistics statistics;

    private final String[] menuItems = {"Buy Car Wash", "Recharge Balance of Wash Card"};

    private Scanner menuItemInput = new Scanner(System.in);

    private boolean isWashCardInserted = false;
    private String[] washTypes = {"Economy", "Standard", "De Luxe"};
    private WashCard currentWashCard;
    private Customer currentCustomer;


    public Machine(Database database, Statistics statistics, FileManipulator io) {
        this.io = io;
        this.database = database;
        this.statistics = statistics;
        mainLoop();

    }

    private void mainLoop() {
        System.out.println("Welcome to SuperShine carwash.");
        while (true) {
            try {
                mainMenu();
            } catch (NoSuchElementException e){
                System.out.println();
            }

            //menuItemInput.close();
            //menuItemInput = new Scanner(System.in);
        }
    }

    private void mainMenu() throws NoSuchElementException{

        if (isWashCardInserted) {
            System.out.println("Welcome, " + currentCustomer.getName());
            System.out.println("Current balance: " + currentCustomer.getBalance() + " DKK.");
            System.out.println("What would you like to do today?");
            System.out.println(makeMenuItems(menuItems));

            String answer = menuItemInput.next();
            int menuItemAsNumber = getAnswerAsNumber(answer);

            if (menuItemAsNumber > 0) {
                switch (menuItemAsNumber) {
                    case 1 -> {
                        buyCarWashMenu();
                    }
                    case 2 -> {
                        rechargeBalanceMenu();
                    }
                    default -> {
                        System.out.println("Please select an item from the menu:");
                        mainLoop();
                    }
                }
            } else {
                //answer is a String.
                if (answer.trim().toLowerCase().matches("(\\bbuy\\b)?(\\bcar\\b)?")) {
                    buyCarWashMenu();
                } else if (answer.trim().toLowerCase().matches("(\\brecharge\\b)?(\\bbalance\\b)?(\\bof\\b)?(\\bcard\\b)?")) {
                    rechargeBalanceMenu();

                } else {
                    System.out.println("I did not quite understand that.\nPlease select an item from the menu:");
                    mainLoop();
                }
            }
        } else {                                                                                                            //if no washcard has been inserted.
            System.out.println("Please insert your SuperShine Wash Card.");
            currentWashCard = insertWashCard();

            if(currentWashCard != null){
                if(getCustomerFromWashCard(currentWashCard) != null){
                    currentCustomer = getCustomerFromWashCard(currentWashCard);
                    isWashCardInserted = true;
                } else {
                    System.out.println("The owner has abandoned this particular wash card! Please return card to SuperShine address posted on the back of the card. Thank you.");
                }

            } else {
                System.out.println("No valid wash card given, please provide an actual wash card, thank you.");
                mainLoop();
            }
        }
    }

    private void rechargeBalanceMenu(){
        double firstOption = 250.00;
        double secondOption = 500.00;
        double thirdOption = 1000.00;

        System.out.println("Please choose amount to recharge your wash card:");
        System.out.println(makeMenuItems("" + firstOption + " DKK.", "" + secondOption + " DKK.", "" + thirdOption + " DKK.", "Custom Amount"));
        String input = menuItemInput.next();

        if(getAnswerAsNumber(input)>0 && getAnswerAsNumber(input)<5){
            switch(getAnswerAsNumber(input)){
                case 1:
                    currentCustomer.setBalance(currentCustomer.getBalance() + firstOption);
                    break;
                case 2:
                    currentCustomer.setBalance(currentCustomer.getBalance() + secondOption);
                    break;
                case 3:
                    currentCustomer.setBalance(currentCustomer.getBalance() + thirdOption);
                    break;
                case 4:
                    try{
                        rechargeBalance(specifyCustomAmount());
                    } catch (NumberFormatException | NullPointerException e){
                        System.out.println("No valid amount given.");
                        System.out.println("Please provide a valid amount.");
                        rechargeBalanceMenu();
                    }
            }
        } else if(input.matches("[,.]")){
            switch(input){
                case "250.0":
                    rechargeBalance(firstOption);
                    break;
                case "500.0":
                    rechargeBalance(secondOption);
                    break;
                case "1000.0":
                    rechargeBalance(thirdOption);
                    break;
            }
        }
        else {
            try{
                rechargeBalance(specifyCustomAmount());
            } catch (NumberFormatException | NullPointerException e){
                System.out.println("No valid amount given.");
                System.out.println("Please provide a valid amount.");
                rechargeBalanceMenu();
            }
        }
    }

    private void rechargeBalance(double amount) {
        currentCustomer.setBalance(currentCustomer.getBalance() + amount);
    }


    private double specifyCustomAmount() throws NumberFormatException, NullPointerException{
        System.out.println("Please specify custom amount:");
        String input = menuItemInput.next();
        if(input.matches("[.,]")){
            return Double.parseDouble(input);
        }
        else{
            return Integer.parseInt(input);
        }
    }

    private void buyCarWashMenu(){
        Wash currentWash;
        menuItemInput = new Scanner(System.in);

        System.out.println("Which type of Car Wash would you like?");
        System.out.println(makeMenuItems(washTypesAsStrings().toArray(new String[WashType.values().length])));

        String answer = menuItemInput.nextLine().trim().toUpperCase();

        currentWash = buyCarWash(answer); //makes a new wash with applied discounts.
        if(currentWash != null && canBuyCarWash(currentWash)){
            System.out.println("Thank you for choosing superShine.");
            System.out.println("Wash selected: " + capitalizeString(currentWash.getType().toString()));
        } else if (currentWash != null && !canBuyCarWash(currentWash)){
            System.out.println("The wash you are trying to buy costs: " + currentWash.getPrice() + " DKK.");
            System.out.println("Your wash card only has " + currentCustomer.getBalance() + "DKK.");
            System.out.println("Please recharge your wash card with at least " + (currentWash.getPrice() - currentCustomer.getBalance()) + " DKK. to continue.");
            mainLoop();
        } else {
            System.out.println("ERROR: Wash machine is broken. The wash you have selected appears to have gone missing.");
        }

    }

    private boolean canBuyCarWash(Wash wash){
        if(currentCustomer.getBalance()>=wash.getPrice()){
            return true;
        }
        return false;
    }

    private Wash buyCarWash(String answer) {
        int menuItemAsNumber = getAnswerAsNumber(answer);
        if (menuItemAsNumber > 0) {
            switch (menuItemAsNumber) {
                case 1 -> {
                    return new Wash(WashType.ECONOMY, this); }
                case 2 -> {
                    return new Wash(WashType.STANDARD, this); }
                case 3 -> {
                    return new Wash(WashType.DELUXE, this); }
                default -> {
                    System.out.println("Please select an item from the menu:");
                    mainLoop();
                }
            }
        } else {
            //answer is a String
            if(validateEnumFromString(answer)){
                WashType.valueOf(answer);
                switch (WashType.valueOf(answer)) {
                    case ECONOMY -> {
                        return new Wash(WashType.ECONOMY, this);
                    }
                    case STANDARD -> {
                        return new Wash(WashType.STANDARD, this);
                    }
                    case DELUXE -> {
                        return new Wash(WashType.DELUXE, this);
                    }
                }
            } else {
                System.out.println("Please select a Wash Type from the menu:");
                buyCarWashMenu();
            }
        }
        return null;
    }

    private boolean validateEnumFromString(String string){
        for(WashType type : WashType.values()){
            if(string.equals(type.toString())){
                return true;
            }
        }
        return false;
    }

    //C-style check if answer is number. Error results in -1.
    private int getAnswerAsNumber(String answer) {
        try {
            return Integer.parseInt(answer);
        } catch (NumberFormatException e) {
            //System.err.println("answer is not a number");                 /* USED FOR DEBUG */
        }
        return -1;
    }

    private ArrayList<String> washTypesAsStrings(){
        ArrayList<String> result = new ArrayList<>();
        for(WashType type : WashType.values()){
            result.add(capitalizeString(type.name().toLowerCase()));
        }
        return result;
    }

    private String makeMenuItems(String... item) {
        StringBuilder sb = new StringBuilder();
        int i = 1;
        for (String s : item) {
            sb.append(i)
                    .append(". ")
                    .append(s)
                    .append("\n");
            i++;
        }
        return sb.toString();
    }

    private String capitalizeString(String string){
        return string.substring(0,1).toUpperCase() + string.substring(1);
    }

    private WashCard insertWashCard(){
        String id = null;
        try {
            id = io.readFile(0, true);
        } catch (IOException e) {
            e.printStackTrace();
        }

        if(id != null){
            return new WashCard(Integer.parseInt(id));
        }
        return null;
    }

    private Customer getCustomerFromWashCard(WashCard washCard){
        return database.getCustomerFromID(washCard.getOwnerID());
    }

    public Customer getCurrentCustomer(){
        return currentCustomer;
    }

}