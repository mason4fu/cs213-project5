/*
package com.example.pizzeria;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Objects;

*/
/**
 * Controller for the "Build Your Own Pizza" screen.
 * Manages user interactions, updates the UI, and handles logic for creating a custom pizza.
 *//*

public class BuildYourOwnController {
    private PizzeriaController mainController; // Reference to the main controller
    private OrdersController ordersController; // Reference to the orders controller
    private CheckoutController checkoutController; // Reference to the checkout controller
    private Stage primaryStage; // Reference to the primary stage
    private Scene primaryScene; // Reference to the primary scene
    private Pizza pizza; // Represents the current pizza being customized

    // FXML-injected UI elements for size selection using ComboBox
    @FXML
    private ComboBox<Size> sizeComboBox; // Dropdown for selecting pizza size

    // FXML-injected UI elements for crust selection
    @FXML
    private ToggleGroup crustGroup;
    @FXML
    private RadioButton chicagoStyleRadioButton;
    @FXML
    private RadioButton newYorkStyleRadioButton;

    // Other FXML-injected UI elements
    @FXML
    private Label subtotalLabel; // Displays the subtotal price
    @FXML
    private Button addToOrderButton; // Button to add the pizza to the order
    @FXML
    private Button returnToHomeButton; // Button to return to the main screen
    @FXML
    private ListView<Topping> availableToppingsListView; // List of available toppings
    @FXML
    private ListView<Topping> selectedToppingsListView; // List of selected toppings
    @FXML
    private Button addToppingButton; // Button to add a topping
    @FXML
    private Button removeToppingButton; // Button to remove a topping
    @FXML
    private ImageView crustImageView; // ImageView to display crust images

    private ObservableList<Topping> availableToppings; // List of available toppings
    private ObservableList<Topping> selectedToppings; // List of selected toppings

    */
/**
     * Sets the main controller and other dependencies.
     *//*

    public void setMainController(PizzeriaController mainController, OrdersController ordersController, CheckoutController checkoutController, Stage primaryStage, Scene primaryScene) {
        this.mainController = mainController;
        this.ordersController = ordersController;
        this.checkoutController = checkoutController;
        this.primaryStage = primaryStage;
        this.primaryScene = primaryScene;
    }

    */
/**
     * Initializes the controller and sets up default values and bindings.
     *//*

    @FXML
    public void initialize() {
        // Create a default Chicago-style Build Your Own pizza
        PizzaFactory factory = new ChicagoPizza();
        this.pizza = factory.createBuildYourOwn();

        // Populate available toppings and set up ListView bindings
        availableToppings = FXCollections.observableArrayList(Topping.values());
        selectedToppings = FXCollections.observableArrayList();
        availableToppingsListView.setItems(availableToppings);
        selectedToppingsListView.setItems(selectedToppings);

        // Initialize the ComboBox with size options
        sizeComboBox.setItems(FXCollections.observableArrayList(Size.values()));
        sizeComboBox.setOnAction(event -> updateSubtotal()); // Update subtotal when size changes

        // Set up toggle group for crust selection
        crustGroup = new ToggleGroup();
        chicagoStyleRadioButton.setToggleGroup(crustGroup);
        newYorkStyleRadioButton.setToggleGroup(crustGroup);

        crustGroup.selectedToggleProperty().addListener((observable, oldValue, newValue) -> updateCrustAndFactory());

        updateSubtotal(); // Initialize the subtotal display
    }

    */
/**
     * Updates the crust and pizza factory based on the selected crust type.
     *//*

    private void updateCrustAndFactory() {
        PizzaFactory factory;
        if (chicagoStyleRadioButton.isSelected()) {
            factory = new ChicagoPizza();
            crustImageView.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/chicago.png"))));
        } else if (newYorkStyleRadioButton.isSelected()) {
            factory = new NYPizza();
            crustImageView.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/ny.png"))));
        } else {
            return; // No crust selected
        }

        // Create a new Build Your Own pizza and update the crust image view
        this.pizza = factory.createBuildYourOwn();
        crustImageView.setFitHeight(120);
        crustImageView.setFitWidth(120);
        crustImageView.setPreserveRatio(false);
    }

    */
/**
     * Updates the subtotal based on the selected size and toppings.
     *//*

    private void updateSubtotal() {
        Size selectedSize = sizeComboBox.getValue(); // Get the selected size from the ComboBox

        if (selectedSize == null) {
            subtotalLabel.setText("Please select a size.");
            return;
        }

        pizza.setSize(selectedSize);
        pizza.setToppings(new ArrayList<>(selectedToppings));
        double subtotal = pizza.price();
        subtotalLabel.setText(subtotal == -2
                ? "Error: Maximum of 7 toppings allowed."
                : String.format("Subtotal: $%.2f", subtotal));
    }

    */
/**
     * Adds a selected topping to the pizza.
     *//*

    @FXML
    public void onAddTopping() {
        Topping selectedTopping = availableToppingsListView.getSelectionModel().getSelectedItem();
        if (selectedTopping != null && selectedToppings.size() < 7) {
            selectedToppings.add(selectedTopping);
            updateSubtotal();
        } else if (selectedToppings.size() >= 7) {
            showAlert("ERROR", "Topping Limit Exceeded", "Please select up to 7 toppings.");
        }
    }

    */
/**
     * Removes a selected topping from the pizza.
     *//*

    @FXML
    public void onRemoveTopping() {
        Topping selectedTopping = selectedToppingsListView.getSelectionModel().getSelectedItem();
        if (selectedTopping != null) {
            selectedToppings.remove(selectedTopping);
            updateSubtotal();
        }
    }

    */
/**
     * Adds the custom pizza to the order.
     *//*

    @FXML
    public void onAddToOrder() {
        if (pizza.getSize() == null) {
            showAlert("ERROR", "Size Selection Required", "Please select a pizza size before adding to order.");
            return;
        }

        Pizza pizzaToAdd = pizza;
        pizzaToAdd.setToppings(new ArrayList<>(selectedToppings));

        ordersController.addPizzaToOrder(pizzaToAdd);
        ordersController.updateOrderDisplay();
        checkoutController.updateOrderDisplay();

        showInfo("INFORMATION", "Pizza Added", "Your custom pizza has been added to the order.");

        resetSelections();
    }

    */
/**
     * Resets selections to default state.
     *//*

    private void resetSelections() {
        sizeComboBox.getSelectionModel().clearSelection(); // Clear ComboBox selection
        crustGroup.selectToggle(null);
        selectedToppings.clear();
        crustImageView.setImage(null);
        updateSubtotal();
    }

    */
/**
     * Returns to the main view.
     *//*

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
     * Displays an error alert.
     *//*

    private void showAlert(String title, String header, String content) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }

    */
/**
     * Displays an informational alert.
     *//*

    private void showInfo(String title, String header, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }
}
*/
