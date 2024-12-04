package com.example.pizzeria;

import java.util.ArrayList;
import java.util.List;

/**
 * Singleton class to manage orders across the application.
 * Uses the Order class for managing current and completed orders.
 */
public class OrderSingleton {

    private static OrderSingleton instance; // Single instance
    private Order currentOrder; // Current order
    private final List<Order> completedOrders; // List of completed orders
    private String errorMessage; // Customizable error message

    // Private constructor to prevent instantiation
    private OrderSingleton() {
        currentOrder = new Order();
        completedOrders = new ArrayList<>();
        errorMessage = "No items in the current order to complete."; // Default error message
    }

    /**
     * Get the single instance of the OrderSingleton.
     *
     * @return the singleton instance
     */
    public static synchronized OrderSingleton getInstance() {
        if (instance == null) {
            instance = new OrderSingleton();
        }
        return instance;
    }

    /**
     * Set a custom error message for the singleton.
     *
     * @param errorMessage the custom error message
     */
    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    /**
     * Add a pizza to the current order.
     *
     * @param pizza the Pizza object to add
     */
    public void addPizzaToOrder(Pizza pizza) {
        currentOrder.orderAdd(pizza);
    }

    /**
     * Get the current order.
     *
     * @return the current Order object
     */
    public Order getCurrentOrder() {
        return currentOrder;
    }

    /**
     * Complete the current order and start a new one.
     */
    public void completeCurrentOrder() {
        if (!currentOrder.getPizzas().isEmpty()) {
            completedOrders.add(currentOrder);
            currentOrder = new Order();
        } else {
            throw new RuntimeException(errorMessage);
        }
    }

    /**
     * Get the list of completed orders.
     *
     * @return the list of completed orders
     */
    public List<Order> getCompletedOrders() {
        return completedOrders;
    }

    /**
     * Remove a completed order by index.
     *
     * @param index the index of the order to remove
     */
    public void removeCompletedOrder(int index) {
        if (index >= 0 && index < completedOrders.size()) {
            completedOrders.remove(index);
        }
    }
}