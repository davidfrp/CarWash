import java.util.Scanner;

public class Machine {

    private final Database database;
    private final Statistics statistics;

    private final String[] menuItems = {"Buy Car Wash", "Recharge Balance of Wash Card"};

    private boolean isWashCardInserted = false;
    private boolean hasSelectedItem = false;


    public Machine(Database database, Statistics statistics) {
        this.database = database;
        this.statistics = statistics;
        mainLoop();

    }

    private void mainLoop() {
        do {
            mainMenu();
        } while (!hasSelectedItem);
    }

    private void mainMenu() {

        Scanner menuItemInput = new Scanner(System.in);

        System.out.println("Welcome to SuperShine");

        if (isWashCardInserted) {
            System.out.println("What would you like to do today?");
            System.out.println(makeMenuItems(menuItems));

            String answer = menuItemInput.nextLine().trim();
            int menuItemAsNumber = checkIfAnswerIsNumber(answer);

            if (menuItemAsNumber > 0) {
                switch (menuItemAsNumber) {
                    case 1 -> {
                        buyCarWash();
                        hasSelectedItem = true;
                    }
                    case 2 -> {
                        rechargeBalance();
                        hasSelectedItem = true;
                    }
                    default -> {
                        System.out.println("Please select an item from the menu:");
                        mainMenu();
                    }
                }
            } else {
                //answer is a String.
                if (answer.matches("(\\bbuy\\b)?(\\bcar\\b)?")) {
                    buyCarWash();
                    hasSelectedItem = true;
                } else if (answer.matches("(\\brecharge\\b)?(\\bbalance\\b)?(\\bof\\b)?(\\bcard\\b)?")) {
                    rechargeBalance();
                    hasSelectedItem = true;
                } else {
                    System.out.println("Please select an item from the menu:");
                    mainMenu();
                }
            }
        } else {
            System.out.println("Please insert your SuperShine Wash Card.");
        }
    }

    private void rechargeBalance() {
        //TODO: recharge balance.
    }

    private void buyCarWash() {
        //TODO: buy car wash.
    }

    //C-style check if answer is number. Error results in -1.
    private int checkIfAnswerIsNumber(String answer) {
        try {
            return Integer.parseInt(answer);
        } catch (NumberFormatException e) {
            System.err.println("answer is not a number");
        }
        return -1;
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

    private WashCard createWashCard(int ownerID) {
        return new WashCard(ownerID);
    }
}