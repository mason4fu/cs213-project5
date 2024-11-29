package com.example.pizzeria;

/**
 * Represents the possible types of crust for the pizza
 */
public enum Crust {
    /** Pizza has a deep dish type crust*/
    DEEPDISH,

    /** Pizza has a pan type crust*/
    PAN,

    /** Pizza has a stuffed crust*/
    STUFFED,

    /** Pizza has a Brooklyn styled crust*/
    BROOKLYN,

    /** Pizza has a thin styled crust*/
    THIN,

    /** Pizza has a hand tossed styled crust*/
    HANDTOSSED;

    /**
     * Returns a string representation of the crust in uppercase format.
     *
     * @return a string representing the crust
     */
    @Override
    public String toString() {
        return name();
    }
}
