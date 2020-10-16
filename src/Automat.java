import java.io.IOException;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Automat {
    private CreditCard creditCard = null;
    private final FileManipulator io;
    private final Database database;
    private final Statistics statistics;

    private final String[] menuItems = {"Buy Car Wash", "Recharge Balance of Wash Card", "Remove Wash Card."};

    private Scanner menuItemInput = new Scanner(System.in);

    private boolean isWashCardInserted = false;
    private String[] washTypes = {"Economy", "Standard", "De Luxe"};
    private WashCard currentWashCard;
    private Customer currentCustomer;


    public Automat(Database database, Statistics statistics, FileManipulator io) {
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
            } catch (NoSuchElementException e) {
                System.err.println("No valid option chosen.");
            }
        }
    }

    private void mainMenu() throws NoSuchElementException {

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
                    case 3 -> {
                        removeWashCard();
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
            System.out.println("Please insert your SuperShine Wash Card.\n");
            currentWashCard = insertWashCard();

            if (currentWashCard != null) {
                if (getCustomerFromWashCard(currentWashCard) != null) {
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

    /**
     * Simulates removing a wash card.
     *
     */
    private void removeWashCard() {
        System.out.println("Wash Card can now safely be removed.\n");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        currentWashCard = null;
        currentCustomer = null;
        isWashCardInserted = false;
        mainLoop();
    }

    /**
     * This method takes the customer through the workflow of recharging their wash card. Some of the amounts are hard-coded due to switch-cases' preference for static content.
     * It calls the appropriate methods to print receipts, get credit cards and recharge balance.
     */
    private void rechargeBalanceMenu() {
        double firstOption = 250.00;
        double secondOption = 500.00;
        double thirdOption = 1000.00;

        System.out.println("Please choose amount to recharge your wash card:");
        System.out.println(makeMenuItems("" + firstOption + " DKK.", "" + secondOption + " DKK.", "" + thirdOption + " DKK.", "Custom Amount"));
        String input = menuItemInput.next();

        if (getAnswerAsNumber(input) > 0 && getAnswerAsNumber(input) < 5) {
            switch (getAnswerAsNumber(input)) {
                case 1:
                    System.out.println("You have chosen " + firstOption + "DKK.");
                    if (creditCard == null) {
                        System.out.println("Please insert your credit card.");
                        insertCreditCard();
                    }
                    if (creditCard != null) {
                        rechargeBalance(firstOption);
                        System.out.println("Printing receipt.");
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        io.writeFileAtSpecificLocation(new Receipt(firstOption, creditCard, false).toString());
                        creditCard = null;
                    }
                    break;
                case 2:

                    System.out.println("You have chosen " + secondOption + "DKK.");
                    if (creditCard == null) {
                        System.out.println("Please insert your credit card.");
                        insertCreditCard();
                    }
                    if (creditCard != null) {
                        rechargeBalance(secondOption);
                        System.out.println("Printing receipt.");
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        io.writeFileAtSpecificLocation(new Receipt(secondOption, creditCard, false).toString());
                        creditCard = null;
                    }
                    break;
                case 3:
                    System.out.println("You have chosen " + thirdOption + "DKK.");
                    if (creditCard == null) {
                        System.out.println("Please insert your credit card.");
                        insertCreditCard();
                    }
                    if (creditCard != null) {
                        rechargeBalance(thirdOption);
                        System.out.println("Printing receipt.");
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        io.writeFileAtSpecificLocation(new Receipt(thirdOption, creditCard, false).toString());
                        creditCard = null;
                    }
                    break;
                case 4:
                    customAmount();
                    break;
            }
        } else if (input.matches("[,.]")) {
            switch (input) {
                case "250.0":
                    rechargeBalance(firstOption);
                    if (creditCard == null) {
                        System.out.println("Please insert credit card.");
                        insertCreditCard();
                    }
                    if (creditCard != null) {
                        System.out.println("Printing receipt.");
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        io.writeFileAtSpecificLocation(new Receipt(firstOption, creditCard, false).toString());
                    }
                    break;
                case "500.0":
                    rechargeBalance(secondOption);
                    if (creditCard == null) {
                        System.out.println("Please insert credit card.");
                        insertCreditCard();
                    }
                    if (creditCard != null) {
                        System.out.println("Printing receipt.");
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        io.writeFileAtSpecificLocation(new Receipt(secondOption, creditCard, false).toString());
                    }
                    break;
                case "1000.0":
                    rechargeBalance(thirdOption);
                    if (creditCard == null) {
                        System.out.println("Please insert credit card.");
                        insertCreditCard();
                    }
                    if (creditCard != null) {
                        System.out.println("Printing receipt.");
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        io.writeFileAtSpecificLocation(new Receipt(thirdOption, creditCard, false).toString());
                    }
                    break;
            }
        } else {
            customAmount();
        }
    }

    /**
     * A setter for the credit card currently being used.
     * @see CreditCard
     */
    private void insertCreditCard() {
        creditCard = io.loadCreditCard();
    }

    /**
     * rechargers the customers balance with a new amount. The amount is added, not replaced.
     * @param amount the amount to be recharge onto the wash card as a double.
     */
    private void rechargeBalance(double amount) {
        currentCustomer.setBalance(currentCustomer.getBalance() + amount);
    }

    /**
     * In case the customer wishes to specify a custom amount. This method verifies that it is a number using a regex to split on separators.
     * @return the custom amount as a double.
     * @throws NumberFormatException in case the amount cannot be passed to a double
     * @throws NullPointerException in case the string that is being parsed from {@link System#in} is null (empty string)
     */
    private double specifyCustomAmount() throws NumberFormatException, NullPointerException {
        System.out.println("Please specify custom amount:");
        String input = menuItemInput.next();
        if (input.matches("[.,]")) {
            return Double.parseDouble(input);
        } else {
            return Integer.parseInt(input);
        }
    }

    /**
     * A methode to create a menu for buying a car wash.
     */
    private void buyCarWashMenu() {
        Wash currentWash;
        menuItemInput = new Scanner(System.in);

        System.out.println("Which type of Car Wash would you like?");
        System.out.println(makeMenuItems(washTypesAsStrings().toArray(new String[WashType.values().length])));

        String answer = menuItemInput.nextLine().trim().toUpperCase();

        currentWash = buyCarWash(answer); //makes a new wash with applied discounts.
        if (currentWash != null && canBuyCarWash(currentWash)) {
            washCar(currentWash);
            currentCustomer.chargeMoney(currentWash.getPrice());
        } else if (currentWash != null && !canBuyCarWash(currentWash)) {
            System.out.println("The wash you are trying to buy costs: " + currentWash.getPrice() + " DKK.");
            System.out.println("Your wash card only has " + currentCustomer.getBalance() + "DKK.");
            System.out.println("Please recharge your wash card with at least " + (currentWash.getPrice() - currentCustomer.getBalance()) + " DKK. to continue.");
            mainLoop();
        } else {
            System.out.println("ERROR: Wash machine is broken. The wash you have selected appears to have gone missing.");
        }
    }

    /**
     * Verifies that the customer have enough money on their wash card to buy the desired wash.
     * @param wash the wash the customer desires
     * @return
     */
    private boolean canBuyCarWash(Wash wash) {
        if (currentCustomer.getBalance() >= wash.getPrice()) {
            return true;
        }
        return false;
    }

    /**
     * This method takes some input as a string and first checks whether it can be converted to a number, i.e. if the user got here by using numbers or words.
     * In either case, this method returns a wash based on the customer's choice.
     * @param answer a string representing the choice made by the customer.
     * @return a wash-object based on the selection made by the customer.
     * @see Wash
     */
    private Wash buyCarWash(String answer) {
        int menuItemAsNumber = getAnswerAsNumber(answer);
        if (menuItemAsNumber > 0) {
            switch (menuItemAsNumber) {
                case 1 -> {
                    return new Wash(WashType.ECONOMY, this);
                }
                case 2 -> {
                    return new Wash(WashType.STANDARD, this);
                }
                case 3 -> {
                    return new Wash(WashType.DELUXE, this);
                }
                default -> {
                    System.out.println("Please select an item from the menu:");
                    mainLoop();
                }
            }
        } else {
            //answer is a String
            if (validateEnumFromString(answer)) {
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

    /**
     * This helper method verifies if a string is a value of a {@link WashType} enum.
     * @param string the string to check up against the types.
     * @return true if the string is equal to a type, else false.
     */
    private boolean validateEnumFromString(String string) {
        for (WashType type : WashType.values()) {
            if (string.equals(type.toString())) {
                return true;
            }
        }
        return false;
    }

    /**
     * This helper method verifies if a string can be converted to an integer.
     * @param answer the string that is to be converted.
     * @return If the string can be converted to an int, that int is returned, otherwise it returns -1.
     */
    //C-style check if answer is number. Error results in -1.
    private int getAnswerAsNumber(String answer) {
        try {
            return Integer.parseInt(answer);
        } catch (NumberFormatException e) {
            //System.err.println("answer is not a number");                 /* USED FOR DEBUG */
        }
        return -1;
    }

    /**
     * This method lists all the different wash types which are enums in an {@link ArrayList} with strings.
     * The types are formatted in such a way that the list item's first letter is capitalized.
     * @return an {@link ArrayList} with strings.
     * @see ArrayList
     */
    private ArrayList<String> washTypesAsStrings() {
        ArrayList<String> result = new ArrayList<>();
        for (WashType type : WashType.values()) {
            result.add(capitalizeString(type.name().toLowerCase()));
        }
        return result;
    }

    /**
     * Generic helper method that creates a list-like menu with numbers for each item in the menu.
     * @param item a list of Strings containing the menu-items.
     * @return The entire menu as a single string, using a {@link StringBuilder}.
     * @see StringBuilder
     */
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

    /**
     * Simple helper method that splits a string in two and capitalizes the first letter.
     * @param string The string that needs to be capitalized.
     * @return The combined string with the first letter capitalized.
     */
    private String capitalizeString(String string) {
        return string.substring(0, 1).toUpperCase() + string.substring(1);
    }

    /**
     * This method tries to get a {@link WashCard} from a {@link FileManipulator}.
     * @return the {@link WashCard}
     * @see FileManipulator#readFile(int, boolean)
     */
    private WashCard insertWashCard() {
        String id = null;
        try {
            id = io.readFile(0, true);
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (id != null) {
            return new WashCard(Integer.parseInt(id));
        }
        return null;
    }

    /**
     * Retrieves the owner of a wash card as a customer-object.
     * @param washCard
     * @return A customer-object representing the owner of the wash card.
     */
    private Customer getCustomerFromWashCard(WashCard washCard) {
        return database.getCustomerFromID(washCard.getOwnerId());
    }

    /**
     * Gets the current customer.
     * @return the current customer-object.
     */
    public Customer getCurrentCustomer() {
        return currentCustomer;
    }

    /**
     * A text-based progressbar using recursion.
     * In this itereation of the method, i *MUST* be 0 and symbol *MUST* be '#' to work.
     * (This method is in its early stages.)
     * @param i the progress bar goes from this number up to 20 in length. This must be 0(!).
     * @param symbol What symbol to add to the progress bar. For the progress bar to not expand exponentially, the actual symbol is hard-coded(!) and must be '#'.
     */
    //i must be 0 and symbol must be '#'.....
    private void progressBar(int i, String symbol) {
        if (i <= 20) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.print("[" + symbol + "] " + i * 5 + "%\r");
            progressBar(i + 1, "#" + symbol);
        }
    }

    /**
     * customAmount is a helper method to the use case where a customer selects a custom amount to recharge their wash card.
     * It prints out the necessary text to the console as well as gives the customer a receipt and recharges the customer's balance.
     */
    private void customAmount() {
        System.out.println("You have chosen a custom amount.");
        try {
            double specifiedAmount = specifyCustomAmount();
            System.out.println("You are about to recharge your wash card with " + specifiedAmount + " DKK.");
            if (creditCard == null) {
                System.out.println("Please insert your credit card.");
                insertCreditCard();
            }
            if (creditCard != null) {
                System.out.println("Printing out receipt.");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                io.writeFileAtSpecificLocation(new Receipt(specifiedAmount, creditCard, false).toString());
            }
            rechargeBalance(specifiedAmount);
            System.out.println(); //new line.
        } catch (NumberFormatException | NullPointerException e) {
            System.out.println("No valid amount given.");
            System.out.println("Please provide a valid amount.");
            rechargeBalanceMenu();
        }
    }

    /**
     * washCar is a helper method to simulate a car wash in the console.
     * Besides printing to the console it also has the responsibility of giving a receipt to the customer.
     * @param currentWash
     */
    private void washCar(Wash currentWash) {
        System.out.println("Thank you for choosing superShine.");
        System.out.println("Wash selected: " + capitalizeString(currentWash.getType().toString()));
        System.out.println("Printing receipt...");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        io.writeFileAtSpecificLocation(new Receipt(currentWash, true).toString());
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.print("Car wash starting in 3\r");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.print("Car wash starting in 2\r");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.print("Car wash starting in 1\r");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.print("Car wash starting now\r");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        progressBar(0, "#");
        System.out.println();
        System.out.println("Car wash done.");
        System.out.println("\n"); //newLine
    }
}