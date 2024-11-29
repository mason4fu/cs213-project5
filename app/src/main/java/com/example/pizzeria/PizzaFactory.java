package com.example.pizzeria;

/**
 * Java interface defining the methods for creating different types of pizzas
 *
 * @author YU FU, JOHNATHAN CHAN
 */
public interface PizzaFactory {

    /**
     * Method used by classes that implement it that will return a deluxe pizza
     *
     * @return a deluxe pizza of the style of whatever class uses it
     */
    Pizza createDeluxe();

    /**
     * Method used by classes that implement it that will return a meatzza pizza
     *
     * @return a meatzza pizza of the style of whatever class uses it
     */
    Pizza createMeatzza();

    /**
     * Method used by classes that implement it that will return a BBQ chicken pizza
     *
     * @return a BBQ chicken pizza of the style of whatever class uses it
     */
    Pizza createBBQChicken();

    /**
     * Method used by classes that implement it that will return a custom pizza
     *
     * @return a custom pizza of the style of whatever class uses it
     */
    Pizza createBuildYourOwn();
}