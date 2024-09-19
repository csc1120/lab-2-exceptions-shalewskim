/*
 * Course: CSC1020
 * Lab 2 - Exceptions
 * Main Driver class
 * Name: Michael Shalewski
 * Last Updated: 09/18/2024
 */
package shalewskim;

import java.util.Arrays;
import java.util.Scanner;

/**
 * Lab 2 Die Driver
 */
public class Driver {
    private static final int MIN_DICE = 2;
    private static final int MAX_DICE = 10;
    private static final int PERCENTAGE = 10;

    private static Scanner in = new Scanner(System.in);

    public static void main(String[] args) {
        try {
            int[] dieConfig = getInput();
            int numberOfDice = dieConfig[0];
            int sides = dieConfig[1];
            int rollsCount = dieConfig[2];

            Die[] dice = createDice(numberOfDice, sides);
            int[] rolls = rollDice(dice, sides, rollsCount);

            report(numberOfDice, rolls, findMax(rolls));
        } catch (DieNotRolledException e) {
            System.err.println(e.getMessage());
        } catch (NumberFormatException e) {
            System.err.println("Invalid input: All values must be whole numbers");
        } catch (ArrayIndexOutOfBoundsException | IllegalArgumentException e) {
            System.err.println(e.getMessage());
        }
    }

    private static int[] getInput() throws NumberFormatException, ArrayIndexOutOfBoundsException {
        System.out.println("Please enter the number of dice to roll, "
                + "how many sides the dice have,");
        System.out.println("and how many rolls to complete, separating the values by a space.");
        System.out.println("Example: \"2 6 1000\"");
        System.out.println();
        System.out.print("Enter configuration: ");
        String input = in.nextLine();
        int[] dieConfig = Arrays.stream(input.split(" ")).mapToInt(Integer::parseInt).toArray();
        if (dieConfig.length < 3) {
            throw new ArrayIndexOutOfBoundsException("Invalid input: Expected 3 values but "
                    + "only received " + dieConfig.length);
        }
        return dieConfig;
    }

    private static Die[] createDice(int numDice, int numSides) throws IllegalArgumentException {
        if (numDice < MIN_DICE || numDice > MAX_DICE) {
            throw new IllegalArgumentException("Bad die creation: Illegal number of dice: "
                    +numDice);
        }

        Die[] die = new Die[numDice];
        for (int i = 0; i < numDice; i++) {
            die[i] = new Die(numSides);
        }
        return die;
    }

    private static int[] rollDice(Die[] dice, int numSides, int numRolls)
            throws DieNotRolledException {
        int[] rollFrequency = new int[(numSides * dice.length) - (dice.length - 1)];

        for (int i = 0; i < numRolls; i++) {
            int total = 0;
            for (Die die : dice) {
                die.roll();
                total += die.getCurrentValue();
            }
            rollFrequency[total - dice.length] += 1;
        }

        return rollFrequency;
    }

    private static int findMax(int[] rolls) {
        int max = rolls[0];
        for (int i = 1; i < rolls.length-1; i++) {
            if (rolls[i] > max) {
                max = rolls[i];
            }
        }
        return max;
    }

    private static void report(int numDice, int[] rolls, int max) {
        for (int i = 0; i < rolls.length; i++) {
            int count = rolls[i] / (max / PERCENTAGE);
            String stars = "*".repeat(count);
            System.out.printf("%-2d:%-6d   %s\n", i + numDice, rolls[i], stars);
        }
    }
}