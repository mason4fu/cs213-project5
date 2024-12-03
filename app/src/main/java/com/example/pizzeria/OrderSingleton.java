package com.example.pizzeria;

import java.util.ArrayList;

public class OrderSingleton {
    private static OrderSingleton orderSingleton; //single instance
    private Order currentOrder; //global data to share

    public ArrayList<Order> getCompletedOrders() {
        return completedOrders;
    }

    private ArrayList<Order> completedOrders;
    private OrderSingleton() { // private constructor
        currentOrder = new Order();
    }
    public static synchronized OrderSingleton getInstance() {
        if (orderSingleton == null) {
            orderSingleton = new OrderSingleton(); //lazy approach
        }
        return orderSingleton;
    }
    public void setCurrentOrder(Order order) { //setter
        currentOrder = order;
    }
    public Order getCurrentOrder() { //getter
        return currentOrder;
    }

    public void newOrder() {
        if (!currentOrder.isEmpty()){
            completedOrders.add(currentOrder);
            currentOrder = new Order();
        } else {
            throw new RuntimeException();
        }
    }
}
