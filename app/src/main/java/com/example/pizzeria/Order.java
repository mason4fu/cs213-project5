package com.example.pizzeria;

import java.util.ArrayList;

/**
 * Represents the pizza orders
 */
public class Order {

    /** Constant that is meant to track the number of orders made so far */
    private static int SERIAL = 0;

    /** Instance variable representing the order number of this specific order */
    private int number; //order number

    /** ArrayLists that will store the pizzas */
    private ArrayList<Pizza> pizzas; //can use List<E> instead of ArrayList<E>

    /**
     * Parameterless constructor to create an order object
     */
    public Order() {
        SERIAL++;
        this.number = SERIAL;
        pizzas = new ArrayList<>();
    }

    /**
     * Parameterized constructor that creates an order object with the given order number and selected pizzas
     *
     * @param number order number of the order
     * @param pizzas pizzas ordered
     */
    public Order(int number, ArrayList<Pizza> pizzas) {
        this.number = number;
        this.pizzas = pizzas;
    }

    /**
     * Returns the pizzas on the order
     *
     * @return pizzas associated with the order object
     */
    public ArrayList<Pizza> getPizzas() {
        return pizzas;
    }

    /**
     * Adds a pizza to the order
     *
     * @param pizza the pizza to be added to the order
     */
    public void orderAdd(Pizza pizza) {
        pizzas.add(pizza);
    }

    /**
     * Removes a pizza from the order
     *
     * @param pizza to be removed
     */
    public void orderRemove(Pizza pizza) {
        pizzas.remove(pizza);
    }

    /**
     * Returns a string representation of the order
     *
     * @return string representation of the order object
     */
    @Override
    public String toString() {
        double total = 0;
        for (Pizza pizza : pizzas){
            total += pizza.price();
        }
        return String.format("%d, %s, %.2f",
                number,
                pizzas.toString(),
                total);
    };
}
