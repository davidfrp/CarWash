import java.util.Scanner;

public class Machine {
    public static void main(String[] args) {
        Scanner inputScan = new Scanner(System.in);     //Create scanner object
        WashType washType = new WashType();

        washType.applyDiscount(1,20);
        System.out.println(washType.getEconomy());




        while(true){
            System.out.println("Please insert your WashCard");
            WashCard washcard = new WashCard(200,"Jonas");      //Simulating insertion of washcard
            if(washcard.getName().length() <= 0){                            //Checks if card has information
                System.out.println("Card is not valid, try again");
            }else{
                break;                                                      //if card is valid proceed
            }
        }


        while(true){                                    //System runs in loop so menu is always showing
            System.out.println("Welcome to Supershine");
            System.out.println("1 : Buy wash");
            System.out.println("2 : Recharge Balance");
            int menuChoice = inputScan.nextInt();

            switch (menuChoice){
                case 1:     //Buy wash
                    break;
                case 2:     //Recharge balance
                    break;
                default:
                    System.out.println("Something went wrong, try again");
                    continue;
            }
        }
    }
}
