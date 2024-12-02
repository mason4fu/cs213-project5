/*
package com.example.pizzeria;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

*/
/**
 * Controller for the main screen.
 *//*

public class PizzeriaController {
    private Stage primaryStage;
    private Scene primaryScene;

    private OrdersController ordersController;
    private Scene ordersScene;

    private CheckoutController checkoutController;
    private Scene checkoutScene;

    private BuildYourOwnController buildYourOwnController;
    private Scene buildYourOwnScene;

    private MakePizzaController makePizzaController;
    private Scene makePizzaScene;


    @FXML
    private Button orderPizzaButton;
    @FXML
    private Button buildYourOwnButton;
    @FXML
    private Button checkoutButton;
    @FXML
    private Button viewOrdersButton;

    */
/**
     *Set the primary stage.
     *
     * @param stage
     * @param scene
     *//*

    public void setPrimaryStage(Stage stage, Scene scene) {
        this.primaryStage = stage;
        this.primaryScene = scene;
    }

    */
/**
     * Initialize.
     *//*

    @FXML
    public void initialize() {
        preloadControllers();

        orderPizzaButton.setGraphic(createImageView("/images/pizza.png"));
        buildYourOwnButton.setGraphic(createImageView("/images/byo.png"));
        checkoutButton.setGraphic(createImageView("/images/checkout.png"));
        viewOrdersButton.setGraphic(createImageView("/images/orders.png"));
    }

    */
/**
     *Create image view.
     *
     * @param imagePath
     * @return
     *//*

    private ImageView createImageView(String imagePath) {
        ImageView imageView = new ImageView(new Image(Objects.requireNonNull(getClass().getResourceAsStream(imagePath))));
        imageView.setFitHeight(130);
        imageView.setFitWidth(130);
        imageView.setPreserveRatio(false);
        return imageView;
    }

    */
/**
     * Preload controllers.
     *//*

    private void preloadControllers() {
        ordersScene = loadView("Orders-view.fxml", controller -> {
            ordersController = (OrdersController) controller;
            ordersController.setMainController(this, primaryStage, primaryScene);
        });

        checkoutScene = loadView("Checkout-view.fxml", controller -> {
            checkoutController = (CheckoutController) controller;
            checkoutController.setMainController(this, ordersController, primaryStage, primaryScene);
        });

        buildYourOwnScene = loadView("BuildYourOwn-view.fxml", controller -> {
            buildYourOwnController = (BuildYourOwnController) controller;
            buildYourOwnController.setMainController(this, ordersController, checkoutController, primaryStage, primaryScene);
        });

        makePizzaScene = loadView("MakePizza-view.fxml", controller -> {
            makePizzaController = (MakePizzaController) controller;
            makePizzaController.setMainController(this, ordersController, checkoutController, primaryStage, primaryScene);
        });
    }

    */
/**
     *Load view.
     *
     * @param fxmlFile
     * @param initializer
     * @return
     *//*

    private Scene loadView(String fxmlFile, ControllerInitializer initializer) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
            BorderPane root = loader.load();
            Scene scene = new Scene(root, 500, 600);
            Object controller = loader.getController();
            initializer.initialize(controller);
            return scene;
        } catch (IOException e) {
            e.printStackTrace();
            showAlert("ERROR", "Loading Error", "Couldn't load " + fxmlFile);
            return null;
        }
    }

    @FXML
    public void DisplayOrders() {
        setSceneAndTitle(ordersScene, "Orders");
    }

    @FXML
    public void DisplayCheckout() {
        setSceneAndTitle(checkoutScene, "Checkout");
    }

    @FXML
    public void DisplayBuildYourOwn() {
        setSceneAndTitle(buildYourOwnScene, "Build Your Own");
    }

    @FXML
    public void DisplayMakePizza() {
        setSceneAndTitle(makePizzaScene, "Make a Pizza");
    }

    public void displayMain() {
        setSceneAndTitle(primaryScene, "PizzeriaHub");
    }

    */
/**
     * Set scene and title.
     *
     * @param scene
     * @param title
     *//*

    private void setSceneAndTitle(Scene scene, String title) {
        if (primaryStage == null) {
            showAlert("ERROR", "Primary Stage Not Initialized", "Please ensure primary stage is set.");
            return;
        }
        primaryStage.setScene(scene);
        primaryStage.setTitle(title);
        primaryStage.show();
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
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }

    @FunctionalInterface
    private interface ControllerInitializer {
        void initialize(Object controller);
    }
}*/
