package com.example.pizzeria;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.util.Objects;

public class MakePizzaController {
    private PizzeriaController mainController;
    private OrdersController ordersController;
    private CheckoutController checkoutController;
    private Stage stage;
    private Scene primaryScene;
    private Stage primaryStage;
    private ObservableList<String> colorList, fruitList, peopleList;
    @FXML
    private Button OrderButton;
    @FXML
    private ToggleGroup TypeGroup;
    @FXML
    private ToggleGroup SizeGroup;
    @FXML
    private ToggleGroup NYOrChicagoGroup;
    @FXML
    private TextArea priceField;
    @FXML
    private TableView<Topping> ToppingsTable;
    @FXML
    private TableColumn<Topping, String> ToppingCol;
    @FXML
    private ImageView pizzaImages;

    /**
     * Get the reference to the MainController object.
     * We can call any public method defined in the controller through the reference.
     */
    public void setMainController(PizzeriaController mainController, OrdersController ordersController, CheckoutController checkoutController, Stage primaryStage, Scene primaryScene) {
        this.mainController = mainController;
        this.ordersController = ordersController;
        this.checkoutController = checkoutController;
        this.primaryStage = primaryStage;
        this.primaryScene = primaryScene;
    }

    @FXML
    public void TypeAction() {
        updateButtonState();
        updateToppings();
    }

    @FXML
    public void StyleAction() {
        updateButtonState();
    }

    @FXML
    public void SizeAction() {
        updateButtonState();
    }

    @FXML
    public void displayMain() {
        if (mainController != null) {
            mainController.displayMain();  // Call back to PizzeriaController’s displayMain
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

    /**
     * Enables the schedule button when required fields are filled
     */
    private void updateButtonState() {
        boolean fieldsNotFilled = TypeGroup.getSelectedToggle() == null ||
                NYOrChicagoGroup.getSelectedToggle() == null ||
                SizeGroup.getSelectedToggle() == null;
        OrderButton.setDisable(fieldsNotFilled);
        updateImages();
        if(fieldsNotFilled){
            priceField.setText(null);
            return;
        }
        priceCheck();
    }

    @FXML
    public void onAddToOrder() {
        String type = ((RadioButton) TypeGroup.getSelectedToggle()).getText().trim();
        String style = ((RadioButton) NYOrChicagoGroup.getSelectedToggle()).getText().trim();
        String size = ((RadioButton) SizeGroup.getSelectedToggle()).getText().trim();

        PizzaFactory pizzaFactory;

        if (style.equalsIgnoreCase("Chicago")) {
            pizzaFactory = new ChicagoPizza();
        } else if (style.equalsIgnoreCase("New York")) {
            pizzaFactory = new NYPizza();
        } else {
            showAlert("ERROR", "Invalid Style of Pizza", "This should not appear.");
            return;
        }

        Pizza pizzaToAdd;

        switch (type.toLowerCase().trim()) {
            case "bbq chicken" -> {
                pizzaToAdd = pizzaFactory.createBBQChicken();
            }
            case "meatzza" -> {
                pizzaToAdd = pizzaFactory.createMeatzza();
            }
            case "deluxe" -> {
                pizzaToAdd = pizzaFactory.createDeluxe();
            }
            default -> {
                showAlert("ERROR", "Invalid Type of Pizza", "This should not appear.");
                return;
            }
        }
        pizzaToAdd.setSize(stringToSize(size));

        ordersController.addPizzaToOrder(pizzaToAdd);
        ordersController.updateOrderDisplay();
        checkoutController.updateOrderDisplay();

        showInfo("INFORMATION","Pizza Added",
                String.format("Your %s, %s-style %s pizza has been added to the order.",
                size, style, type));

        TypeGroup.selectToggle(null);
        NYOrChicagoGroup.selectToggle(null);
        SizeGroup.selectToggle(null);
        updateButtonState();
        updateToppings();
    }

    /**
     * Update toppings.
     */
    private void updateToppings() {
        if(TypeGroup.getSelectedToggle() == null){
            ToppingsTable.getItems().clear();
            ToppingsTable.setDisable(true);
            return;
        }
        ToppingsTable.setDisable(false);

        String type = ((RadioButton) TypeGroup.getSelectedToggle()).getText().toLowerCase().trim();

        // Shouldn't matter what style since same toppings for both
        PizzaFactory pizzaFactory = new NYPizza();

        Pizza pizzaToAdd;

        switch (type.toLowerCase().trim()) {
            case "bbq chicken" -> {
                pizzaToAdd = pizzaFactory.createBBQChicken();
            }
            case "meatzza" -> {
                pizzaToAdd = pizzaFactory.createMeatzza();
            }
            case "deluxe" -> {
                pizzaToAdd = pizzaFactory.createDeluxe();
            }
            default -> {
                showAlert("ERROR", "Invalid Type of Pizza", "This should not appear.");
                return;
            }
        }

        ToppingsTable.setItems(FXCollections.observableArrayList(pizzaToAdd.getToppings()));
    }

    /**
     * Update images displayed.
     */
    private void updateImages() {
        if (TypeGroup.getSelectedToggle() != null && NYOrChicagoGroup.getSelectedToggle() != null){
            fullImageSetter();
        } else if (TypeGroup.getSelectedToggle() != null) {
            typeImageSetter();
        } else if (NYOrChicagoGroup.getSelectedToggle() != null) {
            styleImageSetter();
        } else {
            pizzaImages.setImage(null);
        }
    }

    /**
     * Set images based on style.
     */
    private void styleImageSetter(){
        String style = ((RadioButton) NYOrChicagoGroup.getSelectedToggle()).getText().toLowerCase().trim();
        if(style.equalsIgnoreCase("New York")){
            pizzaImages.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/ny.png"))));
        } else if (style.equalsIgnoreCase("Chicago")) {
            pizzaImages.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/chicago.png"))));
        } else {
            showAlert("ERROR", "Invalid Style of Pizza", "This should not appear.");
            pizzaImages.setImage(null);
        }
    }

    /**
     * Set images based on type.
     */
    private void typeImageSetter(){
        String type = ((RadioButton) TypeGroup.getSelectedToggle()).getText().toLowerCase().trim();
        if(type.equalsIgnoreCase("BBQ Chicken")) {
            pizzaImages.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/NYBBQChicken.jpg"))));
        } else if(type.equalsIgnoreCase("Deluxe")) {
            pizzaImages.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/NYDeluxe.jpg"))));
        } else if (type.equalsIgnoreCase("Meatzza")) {
            pizzaImages.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/NYMeatzza.jpg"))));
        } else {
            showAlert("ERROR", "Invalid Type of Pizza", "Image display failure, this should not appear.");
            pizzaImages.setImage(null);
        }
    }

    /**
     * Full image setter.
     */
    private void fullImageSetter(){
        String style = ((RadioButton) NYOrChicagoGroup.getSelectedToggle()).getText().toLowerCase().trim();
        if(style.equalsIgnoreCase("New York")){
            typeImageSetter();
        } else if (style.equalsIgnoreCase("Chicago")) {
            String type = ((RadioButton) TypeGroup.getSelectedToggle()).getText().toLowerCase().trim();
            if(type.equalsIgnoreCase("BBQ Chicken")) {
                pizzaImages.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/ChicagoBBQChicken.jpg"))));
            } else if(type.equalsIgnoreCase("Deluxe")) {
                pizzaImages.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/ChicagoDeluxe.jpg"))));
            } else if (type.equalsIgnoreCase("Meatzza")) {
                pizzaImages.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/ChicagoMeatzza.jpg"))));
            } else {
                showAlert("ERROR", "Invalid Type of Pizza", "Image display failure, this should not appear.");
                pizzaImages.setImage(null);
            }
        } else {
            showAlert("ERROR", "Invalid Style of Pizza", "This should not appear.");
            pizzaImages.setImage(null);
        }
    }

    @FXML
    public void initialize() {
        ToppingCol.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().toString()));
    }

    /**
     * Check price.
     */
    private void priceCheck() {
        String type = ((RadioButton) TypeGroup.getSelectedToggle()).getText().trim();
        String style = ((RadioButton) NYOrChicagoGroup.getSelectedToggle()).getText().trim();
        String size = ((RadioButton) SizeGroup.getSelectedToggle()).getText().trim();

        PizzaFactory pizzaFactory;

        if (style.equalsIgnoreCase("Chicago")) {
            pizzaFactory = new ChicagoPizza();
        } else if (style.equalsIgnoreCase("New York")) {
            pizzaFactory = new NYPizza();
        } else {
            showAlert("ERROR", "Invalid Style of Pizza", "This should not appear.");
            return;
        }

        Pizza pizzaToAdd;

        switch (type.toLowerCase()) {
            case "bbq chicken" -> {
                pizzaToAdd = pizzaFactory.createBBQChicken();
            }
            case "meatzza" -> {
                pizzaToAdd = pizzaFactory.createMeatzza();
            }
            case "deluxe" -> {
                pizzaToAdd = pizzaFactory.createDeluxe();
            }
            default -> {
                showAlert("ERROR", "Invalid Type of Pizza", "This should not appear.");
                return;
            }
        }
        pizzaToAdd.setSize(stringToSize(size));

        priceField.setText(String.format("The %s, %s-style %s pizza is $%.2f.",
                size, style, type, pizzaToAdd.price()));
    }

    /**
     * Convert string to size.
     *
     * @param size
     * @return
     */
    private Size stringToSize(String size) {
        switch (size.toLowerCase()) {
            case "large" -> {
                return Size.LARGE;
            }
            case "medium" -> {
                return Size.MEDIUM;
            }
            default -> {
                return Size.SMALL;
            }
        }
    }

}
