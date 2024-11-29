package com.example.pizzeria;
/**
 * Represents the different possible sizes for pizzas
 *
 * @author YU FU, JOHNATHAN CHAN
 */
public enum Size {
    /** The pizza is small sized */
    SMALL,

    /** The pizza is medium sized */
    MEDIUM,

    /** The pizza is large sized */
    LARGE;

    /**
     * Returns a string representation of the size in uppercase format.
     *
     * @return a string representing the size
     */
    @Override
    public String toString() {
        return name();
    }
}
