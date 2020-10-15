import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        FileManipulator fileManipulator = new FileManipulator();

        if (args.length > 0) {
            if (args[0].equals("--createmode")) {
                try {
                    fileManipulator.writeFile("test_card_1.washcard", "1", true);
                    fileManipulator.writeFile("test_card_2.washcard", "2", true);
                    fileManipulator.writeFile("test_card_3.washcard", "3", true);
                    fileManipulator.writeFile("test_card_4.washcard", "4", true);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                System.out.println("Wash Cards created. Closing program.");
            } else {
                System.out.println("No valid arguments found. Closing program.");
            }
        } else {
            Statistics statistics = new Statistics();
            Database database = new Database();
            database.makeTestData();
            new Machine(database, statistics, fileManipulator);
        }
    }

    private static int makeTeamName() {
        String names = "Jacob Gade Harder" + "Jonas Emil Gehrke" + "Andreas Juul Michagin" + "David Frederik Richards Petersen";
        char[] chars = names.toCharArray();
        int temp = 0;
        for (int i = 0; i < chars.length; i++) {
            char currentChar = chars[i];
            temp += currentChar;
        }

        return temp;
    }
}
