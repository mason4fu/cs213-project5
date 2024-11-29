package com.example.pizzeria;

/**
 * Represents a deluxe pizza, which is a type of pizza with predefined toppings and crust
 *
 * @author YU FU, JOHNATHAN CHAN
 */
public class Deluxe extends Pizza{

    /**
     * Parameterless constructor of a deluxe pizza
     */
    public Deluxe() {
        super();
        toppingAdder();
    }

    /**
     * Parameterized constructor of a deluxe pizza with a specified crust and size
     *
     * @param crust crust the deluxe pizza should have
     * @param size crust the deluxe pizza should have
     */
    public Deluxe(Crust crust, Size size) {
        super(crust, size);
        toppingAdder();
    }

    /**
     * Adds all the toppings a deluxe pizza should have
     */
    private void toppingAdder() {
        this.addTopping(Topping.SAUSAGE);
        this.addTopping(Topping.PEPPERONI);
        this.addTopping(Topping.GREENPEPPER);
        this.addTopping(Topping.ONION);
        this.addTopping(Topping.MUSHROOM);
    }

    /**
     * Returns the price of deluxe pizza based on size
     *
     * @return 20.99 if large, 18.99 if medium, 16.99 if small, otherwise -1 to represent no size
     */
    @Override
    public double price() {
        switch (this.getSize()){
            case LARGE -> {
                return 20.99;
            }
            case MEDIUM -> {
                return 18.99;
            }
            case SMALL -> {
                return 16.99;
            }
            default -> {
                return -1;
            }
        }
    }
}
