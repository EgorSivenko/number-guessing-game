package org.example.game.utils;

import lombok.experimental.UtilityClass;

import java.util.Scanner;

@UtilityClass
public class UserInput {
    private static final Scanner scanner = new Scanner(System.in);

    public static int readNumber(int lowerBound, int upperBound) {
        while (true) {
            try {
                String input = scanner.nextLine();
                int number = Integer.parseInt(input);
                if (number >= lowerBound && number <= upperBound)
                    return number;
                else
                    System.out.printf("Enter the number between %d and %d: ", lowerBound, upperBound);
            } catch (NumberFormatException ex) {
                System.out.print("Please, enter an integer number: ");
            }
        }
    }

    public static boolean exitGame() {
        try {
            String input = scanner.nextLine();
            return Integer.parseInt(input) == 0;
        } catch (NumberFormatException ex) {
            return false;
        }
    }
}
