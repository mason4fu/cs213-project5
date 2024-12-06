package com.example.pizzeria;

import java.util.ArrayList;

/**
 * Represents a pizza object, containing info on types of toppings, crust, and size.
 *
 * @author YU FU, JOHNATHAN CHAN
 */
public abstract class Pizza {

    /** ArrayList of toppings on the pizza */
    private ArrayList<Topping> toppings; //Topping is a Enum class

    /** Type of crust of the pizza */
    private Crust crust; //Crust is a Enum class

    /** Specified size of the pizza */
    private Size size; //Size is a Enum class

    /**
     * Parameterless constructor to create a pizza object
     */
    public Pizza() {
        this.toppings = new ArrayList<>();
        this.crust = null;
        this.size = null;
    }

    /**
     * Constructor that creates a pizza object with a given crust and size
     *
     * @param crust type of crust the pizza will have
     * @param size size of the pizza
     */
    public Pizza(Crust crust, Size size) {
        this.toppings = new ArrayList<>();
        this.crust = crust;
        this.size = size;
    }

    /**
     * Constructor that creates a pizza object with given toppings, crust, and size
     *
     * @param toppings toppings that will be on the pizza
     * @param crust type of crust the pizza will have
     * @param size size of the pizza
     */
    public Pizza(ArrayList<Topping> toppings, Crust crust, Size size) {
        this.toppings = toppings;
        this.crust = crust;
        this.size = size;
    }

    /**
     * Returns the pizza's toppings
     *
     * @return toppings of the pizza object
     */
    public ArrayList<Topping> getToppings() {
        return toppings;
    }

    /**
     * Sets the pizza's toppings to the given toppings parameter
     *
     * @param toppings toppings the pizza will have
     */
    public void setToppings(ArrayList<Topping> toppings) {
        this.toppings = toppings;
    }

    /**
     * Adds a topping to the pizza's already existing toppings
     *
     * @param topping the topping to be added
     */
    public void addTopping(Topping topping) {
        this.toppings.add(topping);
    }

    /**
     * Removes a topping from the pizza's already existing toppings
     *
     * @param topping the topping to be removed
     */
    public void removeTopping(Topping topping){
        this.toppings.remove(topping);
    }

    /**
     * Returns the type of crust the pizza has
     *
     * @return the crust of the pizza object
     */
    public Crust getCrust() {
        return crust;
    }

    /**
     * Sets the crust of the pizza object to the specified crust type
     *
     * @param crust the crust the pizza object will have
     */
    public void setCrust(Crust crust) {
        this.crust = crust;
    }

    /**
     * Returns the specified size of the pizza object
     *
     * @return the size of the pizza
     */
    public Size getSize() {
        return size;
    }

    /**
     * Sets the pizza's size to the specified size
     *
     * @param size the desired size of the pizza
     */
    public void setSize(Size size) {
        this.size = size;
    }

    /**
     * Abstract method that subclasses will need to implement that gives the price of the pizza
     *
     * @return price of the pizza
     */
    public abstract double price();

    /**
     * String representation of the pizza object
     *
     * @return a string representing the pizza
     */
    @Override
    public String toString() {
        return String.format("%s %s %s",
                toppings.toString(),
                crust.toString(),
                size.toString());
    }
}