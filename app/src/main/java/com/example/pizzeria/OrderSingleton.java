package com.example.pizzeria;

public class OrderSingleton {
    private static OrderSingleton orderSingleton; //single instance
    private Order orders; //global data to share
    private OrderSingleton() { // private constructor
        orders = new Order();
    }
    public static synchronized OrderSingleton getInstance() {
        if (orderSingleton == null) {
            orderSingleton = new OrderSingleton(); //lazy approach
        }
        return orderSingleton;
    }
    public void setOrders(Order order) { //setter
        orders = order;
    }
    public Order getOrders() { //getter
        return orders;
    }
    public void newOrder() {
        orders = new Order();
    }
}
