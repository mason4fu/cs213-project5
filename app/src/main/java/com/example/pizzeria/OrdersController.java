/*
package com.example.pizzeria;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.scene.Scene;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

*/
/**
 * Controller for managing orders in the Pizzeria application.
 * Displays and manages a list of pizzas in the current and completed orders.
 *//*

public class OrdersController {
    private PizzeriaController mainController;  // Reference to the main controller
    private Stage primaryStage;
    private Scene primaryScene;

    private Order currentOrder; // The current order being managed
    private List<Order> completedOrders = new ArrayList<>(); // List of completed orders

    @FXML
    private ListView<String> pizzasListView; // Displays pizzas in the current order
    @FXML
    private ListView<String> completedOrdersListView; // Displays completed orders
    @FXML
    private Label totalLabel; // Shows the total amount for the current order
    @FXML
    private Button refreshButton;
    @FXML
    private Button removeOrderButton;
    @FXML
    private Button printButton;

    */
/**
     * Set main controller.
     *
     * @param mainController
     * @param primaryStage
     * @param primaryScene
     *//*

    public void setMainController(PizzeriaController mainController, Stage primaryStage, Scene primaryScene) {
        this.mainController = mainController;
        this.primaryStage = primaryStage;
        this.primaryScene = primaryScene;

        // Initialize the current order if not already done
        if (this.currentOrder == null) {
            this.currentOrder = new Order();
        }
        updateOrderDisplay();
        updateCompletedOrdersDisplay();
    }

    @FXML
    public void initialize() {
        pizzasListView.setItems(FXCollections.observableArrayList());
        completedOrdersListView.setItems(FXCollections.observableArrayList());

        if (this.currentOrder == null) {
            this.currentOrder = new Order();
        }

        updateOrderDisplay();
        updateCompletedOrdersDisplay();
    }

    */
/**
     * Adds a pizza to the current order and updates the display.
     *
     * @param pizza the pizza to add
     *//*

    public void addPizzaToOrder(Pizza pizza) {
        if (currentOrder == null) {
            currentOrder = new Order();
        }
        currentOrder.orderAdd(pizza);
        updateOrderDisplay();
    }

    */
/**
     * Completes the current order, adds it to the list of completed orders,
     * and starts a new order.
     *//*

    public void completeOrder() {
        if (currentOrder != null) {
            completedOrders.add(currentOrder);
        }
        currentOrder = new Order(); // Start a new order
        updateOrderDisplay();
        updateCompletedOrdersDisplay();
    }

    */
/**
     * Updates the display of pizzas and total cost in the current order.
     *//*

    public void updateOrderDisplay() {
        ObservableList<String> pizzaDescriptions = FXCollections.observableArrayList();
        double total = 0;

        for (Pizza pizza : currentOrder.getPizzas()) {
            double price = pizza.price();
            pizzaDescriptions.add(pizza + " - $" + String.format("%.2f", price));
            total += price;
        }

        pizzasListView.setItems(pizzaDescriptions);
        totalLabel.setText(String.format("Total: $%.2f", total));
    }

    */
/**
     * Retrieves the pizzas in the current order.
     *
     * @return list of pizzas in the current order
     *//*

    public List<Pizza> getCurrentOrder() {
        return currentOrder.getPizzas();
    }

    */
/**
     * Updates the display of completed orders.
     *//*

    public void updateCompletedOrdersDisplay() {
        ObservableList<String> orderDescriptions = FXCollections.observableArrayList();
        for (Order order : completedOrders) {
            orderDescriptions.add(order.toString()); // Customize as needed for detailed order description
        }
        completedOrdersListView.setItems(orderDescriptions);
    }

    */
/**
     * Removes the selected order from the completed orders list.
     *//*

    @FXML
    public void onRemoveSelectedOrder() {
        int selectedIndex = completedOrdersListView.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            completedOrders.remove(selectedIndex);
            updateCompletedOrdersDisplay();
        } else {
            showAlert("ERROR", "No order selected", "Please select an order to remove.");
        }
    }

    */
/**
     * Prints all completed orders to a text file. Stored in root folder.
     *//*

    @FXML
    public void onPrintOrders() {
        if (completedOrders.isEmpty()) {
            showAlert("ERROR", "No Completed Orders", "There are no completed orders to print.");
            return;
        }

        try (FileWriter writer = new FileWriter("completed_orders.txt")) {
            for (Order order : completedOrders) {
                writer.write(order.toString() + System.lineSeparator());
            }
            showAlert("Success", "Orders Printed", "All orders have been printed to completed_orders.txt");
        } catch (IOException e) {
            showAlert("ERROR", "File Write Error", "Could not write to file: " + e.getMessage());
        }
    }

    @FXML
    public void onRefreshOrders() {
        updateOrderDisplay();
        updateCompletedOrdersDisplay();
    }

    @FXML
    public void displayMain() {
        if (mainController != null) {
            mainController.displayMain();
        } else {
            showAlert("ERROR", "Main Controller Missing", "Cannot return to main view.");
        }
    }

    */
/**
     * Show alert.
     *
     * @param title
     * @param header
     * @param content
     *//*

    private void showAlert(String title, String header, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }
}*/
