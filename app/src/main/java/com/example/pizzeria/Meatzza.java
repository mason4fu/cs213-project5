package com.example.pizzeria;

/**
 * Represents a meatzza type pizza, which is a type of pizza with predefined toppings and crust
 *
 * @author YU FU, JOHNATHAN CHAN
 */
public class Meatzza extends Pizza{

    /**
     * Parameterless constructor to construct a meatzza pizza
     */
    public Meatzza() {
        super();
        toppingAdder();
    }

    /**
     * Parameterized constructor to construct a meatzza pizza with the specified crust and size
     *
     * @param crust crust the meatzza will have
     * @param size size of the meatzza
     */
    public Meatzza(Crust crust, Size size) {
        super(crust, size);
        toppingAdder();
    }

    /**
     * Adds all the toppings a meatzza should have
     */
    private void toppingAdder() {
        this.addTopping(Topping.SAUSAGE);
        this.addTopping(Topping.PEPPERONI);
        this.addTopping(Topping.BEEF);
        this.addTopping(Topping.HAM);
    }

    /**
     * Returns the price of meatzza based on size
     *
     * @return 21.99 if large, 19.99 if medium, 17.99 if small, otherwise -1 to represent no size
     */
    @Override
    public double price() {
        switch (this.getSize()){
            case LARGE -> {
                return 21.99;
            }
            case MEDIUM -> {
                return 19.99;
            }
            case SMALL -> {
                return 17.99;
            }
            default -> {
                return -1;
            }
        }
    }
}
