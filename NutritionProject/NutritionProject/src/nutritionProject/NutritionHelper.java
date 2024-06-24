package nutritionProject;

import java.util.Scanner;

public class NutritionHelper {

    public static int getUserInputAsInt(Scanner scanner) {
        while (true) {
            if (scanner.hasNextInt()) {
                int number = scanner.nextInt();
                scanner.nextLine();
                return number;
            } else {
                System.out.println("Please Enter a numeric value");
                scanner.nextLine();
            }
        }
    }

    public static String getUserInputAsStringAndOnlyLetters(Scanner scanner) {
        while (true) {
            String input = scanner.nextLine().trim();
            if (input.matches("[a-zA-Z\\s]+")) {
                return input;
            } else {
                System.out.println("Please enter a value with English characters and not numeric.");
            }
        }
    }
}


