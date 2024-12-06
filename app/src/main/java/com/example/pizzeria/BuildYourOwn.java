package com.example.pizzeria;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a custom pizza with a specified crust, size, and toppings.
 * Allows dynamic price calculation based on size and number of toppings.
 *
 * @author YU FU, JOHNATHAN CHAN
 */
public class BuildYourOwn extends Pizza {
    private static final double TOPPING_COST = 1.69;
    private static final double LARGE_BASE_PRICE = 12.99;
    private static final double MEDIUM_BASE_PRICE = 10.99;
    private static final double SMALL_BASE_PRICE = 8.99;

    /**
     * Calculates the price of the BuildYourOwn pizza based on size and toppings.
     *
     * @return the calculated price or error code for invalid configurations
     */
    @Override
    public double price() {
        if (this.getSize() == null) {
            return -1;  // Error: No valid size selected
        }
        double basePrice;

        switch (this.getSize()) {
            case LARGE -> basePrice = LARGE_BASE_PRICE;
            case MEDIUM -> basePrice = MEDIUM_BASE_PRICE;
            case SMALL -> basePrice = SMALL_BASE_PRICE;
            default -> {
                return -1;  // Error: No valid size selected
            }
        }

        if (this.getToppings().size() > 7) {
            return -2;  // Error: More than 7 toppings
        }

        return basePrice + TOPPING_COST * this.getToppings().size();
    }
}