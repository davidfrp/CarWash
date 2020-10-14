import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Statistics statistics = new Statistics();
        Database database = new Database();
        database.makeTestData();
        new Machine(database, statistics);
    }

    private static int makeTeamName(){
        String names = "Jacob Gade Harder" + "Jonas Emil Gehrke" + "Andreas Juul Michagin" + "David Frederik Richards Petersen";
        char[] chars = names.toCharArray();
        int temp = 0;
        for(int i = 0; i< chars.length;i++){
            char currentChar = chars[i];
            temp += currentChar;
        }

        return temp;
    }
}
