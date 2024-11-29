package com.example.pizzeria;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Controller for the Checkout screen.
 */
public class CheckoutController {
    private PizzeriaController mainController;  // Reference to the main controller
    private OrdersController ordersController; // Reference to OrdersController for accessing the current order
    private Stage primaryStage;
    private Scene primaryScene;

    private static final double TAX_RATE = 0.06625;

    @FXML
    private ListView<String> pizzasListView;
    @FXML
    private Label subtotalLabel;
    @FXML
    private Label taxLabel;
    @FXML
    private Label totalLabel;
    @FXML
    private Button removePizzaButton;
    @FXML
    private Button checkoutButton;

    /**
     * Set main controller.
     *
     * @param mainController
     * @param ordersController
     * @param primaryStage
     * @param primaryScene
     */
    public void setMainController(PizzeriaController mainController, OrdersController ordersController, Stage primaryStage, Scene primaryScene) {
        this.mainController = mainController;
        this.ordersController = ordersController;
        this.primaryStage = primaryStage;
        this.primaryScene = primaryScene;
        updateOrderDisplay(); // Initialize display upon setting controllers and stage
    }

    /**
     * Initialize.
     */
    @FXML
    public void initialize() {
        pizzasListView.setItems(FXCollections.observableArrayList());
    }

    /**
     * Update order display.
     */
    public void updateOrderDisplay() {
        if (ordersController == null) {
            showAlert("ERROR", "OrdersController not initialized", "Unable to update order display.");
            return;
        }

        ObservableList<String> pizzaDescriptions = FXCollections.observableArrayList();
        double subtotal = 0;

        for (Pizza pizza : ordersController.getCurrentOrder()) {
            double pizzaPrice = pizza.price();
            pizzaDescriptions.add(pizza + " - $" + String.format("%.2f", pizzaPrice));
            subtotal += pizzaPrice;
        }

        pizzasListView.setItems(pizzaDescriptions);
        subtotalLabel.setText(String.format("Subtotal: $%.2f", subtotal));

        double tax = subtotal * TAX_RATE;
        taxLabel.setText(String.format("Tax: $%.2f", tax));

        double total = subtotal + tax;
        totalLabel.setText(String.format("Total: $%.2f", total));
    }

    /**
     * Remove selected pizza.
     */
    @FXML
    public void onRemovePizza() {
        int selectedIndex = pizzasListView.getSelectionModel().getSelectedIndex();

        // Check if a pizza is selected and ordersController is not null
        if (selectedIndex >= 0 && ordersController != null) {
            // Remove the selected pizza from the order
            ordersController.getCurrentOrder().remove(selectedIndex);

            // Update the display to reflect the change
            updateOrderDisplay();

        } else {
            showAlert("ERROR", "No pizza selected", "Please select a pizza to remove.");
        }
    }

    /**
     * Manage checkout button click.
     */
    @FXML
    public void onCheckout() {
        if (ordersController == null) {
            showAlert("ERROR", "OrdersController not initialized", "Unable to complete checkout.");
            return;
        }

        Alert confirmationAlert = new Alert(Alert.AlertType.CONFIRMATION);
        confirmationAlert.setTitle("Confirm Checkout");
        confirmationAlert.setHeaderText("Are you sure you want to proceed with checkout?");
        confirmationAlert.setContentText("Click OK to proceed, or Cancel to go back.");

        confirmationAlert.showAndWait().ifPresent(response -> {
            if (response == ButtonType.OK) {
                ordersController.completeOrder();
                updateOrderDisplay();
                showInfo("INFORMATION", "Order Completed", "Thank you! Your order has been placed.");
            }
        });
    }

    /**
     * Returns to the main view.
     */
    @FXML
    public void displayMain() {
        if (mainController != null) {
            mainController.displayMain();
        } else {
            showAlert("ERROR", "Main Controller Missing", "Cannot return to main view.");
        }
    }

    /**
     * Show alert.
     *
     * @param title
     * @param header
     * @param content
     */
    private void showAlert(String title, String header, String content) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }

    /**
     * Show information.
     *
     * @param title
     * @param header
     * @param content
     */
    private void showInfo(String title, String header, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }
}