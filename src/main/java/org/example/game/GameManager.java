package org.example.game;

import lombok.Getter;
import org.example.game.utils.Generator;
import org.example.game.utils.UserInput;

import static org.example.game.utils.GameConstants.*;

public class GameManager {
    @Getter
    private static final GameManager instance = new GameManager();

    private int number;
    private int attemptsLeft;
    private boolean isNumberGuessed;

    private GameManager() {}

    public void launchGame() {
        System.out.println("Welcome to the Number Guessing Game!");

        do {
            updateSettings();
            displayRules();

            interactWithUser();
            displayResult();

        } while (!UserInput.exitGame());

        System.out.println("Thanks for playing. Hope to see you again!");
    }

    private void updateSettings() {
        number = Generator.generateRandomInteger(LOWER_BOUND, UPPER_BOUND);
        attemptsLeft = ATTEMPTS_AMOUNT;
        isNumberGuessed = false;
    }

    private void displayRules() {
        System.out.printf("\nTry to guess the number between %d and %d.", LOWER_BOUND, UPPER_BOUND);
        System.out.printf(" You have %d attempts. Good luck!", ATTEMPTS_AMOUNT);
        System.out.print("\nEnter the number: ");
    }

    private void displayResult() {
        if (!isNumberGuessed)
            System.out.println("\nGame Over! The number was " + number + ".");
        System.out.print("\nIf you want to leave, press 0. Otherwise press any button: ");
    }

    private void interactWithUser() {
        while (!isNumberGuessed && keepPlaying()) {
            int userNumber = UserInput.readNumber(LOWER_BOUND, UPPER_BOUND);
            compareUserNumber(userNumber);
        }
    }

    private boolean keepPlaying() {
        return attemptsLeft > 0;
    }

    private void compareUserNumber(int userNumber) {
        if (userNumber > number) {
            promptUser("Your number is too high.");
        }
        else if (userNumber < number) {
            promptUser("Your number is too low.");
        }
        else {
            isNumberGuessed = true;
            System.out.println("\nCongratulations, you guessed the number!");
        }
    }

    private void promptUser(String prompt) {
        System.out.println(prompt + " Attempts left: " + --attemptsLeft);
        if (keepPlaying()) System.out.print("Try again: ");
    }
}
