/*
 * Course: CSC1020
 * Lab 2 - Exceptions
 * DieNotRolledException class
 * Name: Michael Shalewski
 * Last Updated: 09/18/2024
 */
package shalewskim;

/**
 *  RuntimeException thrown when Die getCurrentValue()
 *  is called before having been rolled
 */
public class DieNotRolledException extends RuntimeException {
    private final String message = "Die must be rolled prior to retrieving a value.";
    /**
     *  Overloaded Constructor for DieNotRolledException
     */
    public DieNotRolledException() { }

    @Override
    public String getMessage() {
        return this.message;
    }
}
