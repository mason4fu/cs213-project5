package com.example.pizzeria;

/**
 * Represents the possible toppings for the pizza
 *
 * @author YU FU, JOHNATHAN CHAN
 */
public enum Topping {
    /** The pizza has sausage as a topping */
    SAUSAGE,

    /** The pizza has pepperoni as a topping */
    PEPPERONI,

    /** The pizza has green pepper as a topping */
    GREENPEPPER,

    /** The pizza has onion as a topping */
    ONION,

    /** The pizza has mushroom as a topping */
    MUSHROOM,

    /** The pizza has BBQ chicken as a topping */
    BBQCHICKEN,

    /** The pizza has provolone as a topping */
    PROVOLONE,

    /** The pizza has cheddar as a topping */
    CHEDDAR,

    /** The pizza has beef as a topping */
    BEEF,

    /** The pizza has ham as a topping */
    HAM,

    /** The pizza has olives as a topping */
    OLIVES,

    /** The pizza has jalapenos as a topping */
    JALAPENOS,

    /** The pizza has pineapple as a topping */
    PINEAPPLE;

    /**
     * Returns a string representation of the topping in uppercase format.
     *
     * @return a string representing the topping
     */
    @Override
    public String toString() {
        return name();
    }
}
