package com.example.pizzeria;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Activity for the Checkout screen.
 * Manages the display of pizzas in the current order and calculates totals with tax.
 *
 * @author YU FU, JOHNATHAN CHAN
 */
public class CheckoutActivity extends AppCompatActivity {

    private static final double TAX_RATE = 0.06625;

    // UI elements
    private ListView pizzasListView;
    private TextView subtotalLabel, taxLabel, totalLabel;
    private Button removePizzaButton, checkoutButton, returnToHomeButton;

    private ArrayAdapter<String> pizzasAdapter;
    private List<String> pizzaDescriptions; // List to hold string representations of pizzas

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);

        // Initialize UI elements
        pizzasListView = findViewById(R.id.pizzasListView);
        subtotalLabel = findViewById(R.id.subtotalLabel);
        taxLabel = findViewById(R.id.taxLabel);
        totalLabel = findViewById(R.id.totalLabel);
        removePizzaButton = findViewById(R.id.removePizzaButton);
        checkoutButton = findViewById(R.id.checkoutButton);
        returnToHomeButton = findViewById(R.id.returnToHomeButton);

        // Initialize pizza descriptions from the Singleton
        pizzaDescriptions = new ArrayList<>();
        updatePizzaDescriptions();

        // Set up adapter for ListView
        pizzasAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_activated_1, pizzaDescriptions);
        pizzasListView.setAdapter(pizzasAdapter);

        // Enable single selection mode
        pizzasListView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);

        // Update totals
        updateOrderDisplay();

        // Set button actions
        removePizzaButton.setOnClickListener(v -> onRemovePizza());
        checkoutButton.setOnClickListener(v -> onCheckout());
        returnToHomeButton.setOnClickListener(v -> finish());
    }

    /**
     * Updates the list of pizza descriptions.
     */
    private void updatePizzaDescriptions() {
        pizzaDescriptions.clear();
        for (Pizza pizza : OrderSingleton.getInstance().getCurrentOrder().getPizzas()) {
            pizzaDescriptions.add(pizza.toString());
        }
    }

    /**
     * Updates the order display by recalculating totals and updating the ListView.
     */
    private void updateOrderDisplay() {
        updatePizzaDescriptions();
        pizzasAdapter.notifyDataSetChanged();

        double subtotal = OrderSingleton.getInstance().getCurrentOrder().getPizzas()
                .stream()
                .mapToDouble(Pizza::price)
                .sum();

        double tax = subtotal * TAX_RATE;
        double total = subtotal + tax;

        subtotalLabel.setText(getString(R.string.subtotal_label, subtotal));
        taxLabel.setText(getString(R.string.tax_label, tax));
        totalLabel.setText(getString(R.string.total_label, total));
    }

    /**
     * Removes the selected pizza from the order.
     */
    private void onRemovePizza() {
        int selectedIndex = pizzasListView.getCheckedItemPosition();
        if (selectedIndex >= 0) {
            OrderSingleton.getInstance().getCurrentOrder().getPizzas().remove(selectedIndex);
            pizzasListView.clearChoices(); // Clear selection
            updateOrderDisplay();
            Toast.makeText(this, getString(R.string.toast_pizza_removed), Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, getString(R.string.toast_no_pizza_selected), Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * Completes the checkout process and clears the current order.
     */
    private void onCheckout() {
        try {
            OrderSingleton.getInstance().completeCurrentOrder();
            pizzasListView.clearChoices(); // Clear selection
            updateOrderDisplay();
            Toast.makeText(this, getString(R.string.toast_order_completed), Toast.LENGTH_SHORT).show();
        } catch (RuntimeException e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
}