import java.util.Scanner;

public class Automat {
    public static void main(String[] args) {
        Scanner inputScan = new Scanner(System.in);
        while (true) {  //Machine should never turn off
            System.out.println("Insert WashCard");
            //TODO: How do we insert a washcard? Simulating a static one?

            outer: //Define menu loop
            while (true) {
                System.out.println("**Menu**\n" +
                        "1: Buy Wash\n" +
                        "2: Recharge Washcard");
                int menuChoice = inputScan.nextInt();   //scan for menu choice

                switch (menuChoice) {
                    case 1: //Buy wash
                        break;
                    case 2: //Recharge Washcard
                        break;
                    default:    //Error, wrong key
                        System.out.println("Something went wrong, please try again");
                        break;
                }
            }
        }
    }
}
