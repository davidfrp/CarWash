import java.util.ArrayList;
import java.util.Scanner;

public class Machine {

    private final Database database;
    private final Statistics statistics;

    private final String[] menuItems = {"Buy Car Wash", "Recharge Balance of Wash Card"};

    private Scanner menuItemInput = new Scanner(System.in);

    private boolean isWashCardInserted = false;
    private boolean hasSelectedItem = false;
    private String[] washTypes = {"Economy", "Standard", "De Luxe"};

    private Wash orderedWash;


    public Machine(Database database, Statistics statistics) {
        this.database = database;
        this.statistics = statistics;
        mainLoop();

    }

    private void mainLoop() {
        System.out.println("Welcome to SuperShine");
        do {
            mainMenu();
        } while (!hasSelectedItem);
    }

    private void mainMenu() {

        if (isWashCardInserted) {
            System.out.println("What would you like to do today?");
            System.out.println(makeMenuItems(menuItems));

            String answer = menuItemInput.nextLine().trim();
            int menuItemAsNumber = checkIfAnswerIsNumber(answer);

            if (menuItemAsNumber > 0) {
                switch (menuItemAsNumber) {
                    case 1 -> {
                        buyCarWashMenu();
                    }
                    case 2 -> {
                        rechargeBalance();
                    }
                    default -> {
                        System.out.println("Please select an item from the menu:");
                        mainMenu();
                    }
                }
            } else {
                //answer is a String.
                if (answer.trim().toLowerCase().matches("(\\bbuy\\b)?(\\bcar\\b)?")) {
                    buyCarWashMenu();
                } else if (answer.trim().toLowerCase().matches("(\\brecharge\\b)?(\\bbalance\\b)?(\\bof\\b)?(\\bcard\\b)?")) {
                    rechargeBalance();

                } else {
                    System.out.println("I did not quite understand that.\nPlease select an item from the menu:");
                    mainMenu();
                }
            }
        } else {
            System.out.println("Please insert your SuperShine Wash Card.");
            isWashCardInserted = true;

        }
    }

    private void rechargeBalance() {
        hasSelectedItem = true;
        //TODO: recharge balance.
    }

    private void buyCarWashMenu(){
        System.out.println("Which type of Car Wash would you like?");
        System.out.println(makeMenuItems(washTypesAsStrings().toArray(new String[WashType.values().length])));
        buyCarWash();
    }

    private Wash buyCarWash() {
        menuItemInput = new Scanner(System.in);

        makeMenuItems(washTypes);

        String answer = menuItemInput.nextLine().trim().toUpperCase();
        int menuItemAsNumber = checkIfAnswerIsNumber(answer);

        if (menuItemAsNumber > 0) {
            switch (menuItemAsNumber) {
                case 1 -> {
                    hasSelectedItem = true;
                    return new Wash(WashType.ECONOMY); }
                case 2 -> {
                    hasSelectedItem = true;
                    return new Wash(WashType.STANDARD); }
                case 3 -> {
                    hasSelectedItem = true;
                    return new Wash(WashType.DELUXE); }
                default -> {
                    System.out.println("Please select an item from the menu:");
                    mainMenu();
                }
            }
        } else {
            //answer is a String
            if(validateEnumFromString(answer)){
                WashType.valueOf(answer);
                switch (WashType.valueOf(answer)) {
                    case ECONOMY -> {
                        hasSelectedItem = true;
                        return new Wash(WashType.ECONOMY);
                    }
                    case STANDARD -> {
                        hasSelectedItem = true;
                        return new Wash(WashType.STANDARD);
                    }
                    case DELUXE -> {
                        hasSelectedItem = true;
                        return new Wash(WashType.DELUXE);
                    }
                }
            } else {
                System.out.println("Please select a Wash Type from the menu:");
                buyCarWash();
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
    private int checkIfAnswerIsNumber(String answer) {
        try {
            return Integer.parseInt(answer);
        } catch (NumberFormatException e) {
            System.err.println("answer is not a number");
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

}