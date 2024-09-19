/*
 * Course: CSC1020
 * Lab 2 - Exceptions
 * Die class
 * Name: Michael Shalewski
 * Last Updated: 09/18/2024
 */
package shalewskim;

import java.util.Random;

/**
 * Die class
 */
public class Die {
    /**
     * MINIMUM NUMBER OF SIDES
     */
    public static final int MIN_SIDES = 2;

    /**
     * MAXIMUM NUMBER OF SIDES
     */
    public static final int MAX_SIDES = 100;

    private final int numSides;
    private final Random random;
    private int currentValue;

    /**
     * Die constructor
     * @param numSides number of sides from 2 to 100
     * @throws IllegalArgumentException when numSides is out of bounds
     */
    public Die(int numSides) throws IllegalArgumentException {
        if (numSides < MIN_SIDES || numSides > MAX_SIDES) {
            throw new IllegalArgumentException("Illegal number of sides: "+numSides);
        }
        this.numSides = numSides;
        this.random = new Random();
    }

    /**
     * roll method to set Die currentValue
     */
    public void roll() {
        this.currentValue = random.nextInt(numSides)+1;
    }

    /**
     * getCurrentValue
     * @return int currentValue
     * @throws DieNotRolledException when Die has not had roll method called
     */
    public int getCurrentValue() throws DieNotRolledException {
        if (currentValue == 0) {
            throw new DieNotRolledException();
        }
        int value = this.currentValue;
        this.currentValue = 0;
        return value;
    }

}