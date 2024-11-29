package com.example.pizzeria;

/**
 * Represents a BBQChicken pizza, which is a type of pizza with predefined toppings and crust
 *
 * @author YU FU, JOHNATHAN CHAN
 */
public class BBQChicken extends Pizza{
    /**
     * Parameterless constructor for BBQ Chicken objects
     */
    public BBQChicken() {
        super();
        toppingAdder();
    }

    /**
     * Adds up all the toppings used by BBQ Chicken pizzas
     */
    private void toppingAdder() {
        this.addTopping(Topping.BBQCHICKEN);
        this.addTopping(Topping.GREENPEPPER);
        this.addTopping(Topping.PROVOLONE);
        this.addTopping(Topping.CHEDDAR);
    }

    /**
     * Returns the price of BBQ chicken pizza based on size
     *
     * @return 19.99 if large, 16.99 if medium, 14.99 if small, otherwise -1 to represent no size
     */
    @Override
    public double price() {
        switch (this.getSize()){
            case LARGE -> {
                return 19.99;
            }
            case MEDIUM -> {
                return 16.99;
            }
            case SMALL -> {
                return 14.99;
            }
            default -> {
                return -1;
            }
        }
    }
}
