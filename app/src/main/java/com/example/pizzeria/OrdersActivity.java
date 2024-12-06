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
 * Activity for managing orders in the Pizzeria application.
 * Handles the current order logic and manages completed orders.
 *
 * @author YU FU, JOHNATHAN CHAN
 */
public class OrdersActivity extends AppCompatActivity {

    // UI elements
    private ListView completedOrdersListView;
    private TextView totalOrdersLabel;
    private Button refreshButton, removeOrderButton, returnToHomeButton;

    private ArrayAdapter<String> completedOrdersAdapter;
    private List<String> completedOrderDescriptions; // List to hold string representations of completed orders

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orders);

        // Initialize UI elements
        completedOrdersListView = findViewById(R.id.completedOrdersListView);
        totalOrdersLabel = findViewById(R.id.totalOrdersLabel);
        refreshButton = findViewById(R.id.refreshButton);
        removeOrderButton = findViewById(R.id.removeOrderButton);
        returnToHomeButton = findViewById(R.id.returnToHomeButton);

        // Initialize completed order descriptions
        completedOrderDescriptions = new ArrayList<>();
        updateOrderDescriptions();

        // Set up adapter for ListView
        completedOrdersAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_activated_1, completedOrderDescriptions);
        completedOrdersListView.setAdapter(completedOrdersAdapter);

        // Highlight selected item and clear highlight when clicking elsewhere
        completedOrdersListView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);

        // Set button actions
        refreshButton.setOnClickListener(v -> updateOrderDisplay());
        removeOrderButton.setOnClickListener(v -> removeSelectedOrder());
        returnToHomeButton.setOnClickListener(v -> finish());

        // Update total orders
        updateTotalOrders();
    }

    /**
     * Updates the total number of completed orders.
     */
    private void updateTotalOrders() {
        int totalOrders = OrderSingleton.getInstance().getCompletedOrders().size();
        totalOrdersLabel.setText(getString(R.string.total_orders_label, totalOrders));
    }

    /**
     * Updates the completed orders descriptions.
     */
    private void updateOrderDescriptions() {
        completedOrderDescriptions.clear();
        for (Order order : OrderSingleton.getInstance().getCompletedOrders()) {
            completedOrderDescriptions.add(order.toString());
        }
    }

    /**
     * Updates the completed orders display.
     */
    private void updateOrderDisplay() {
        updateOrderDescriptions();
        completedOrdersAdapter.notifyDataSetChanged();
        updateTotalOrders();
    }

    /**
     * Removes the selected completed order.
     */
    private void removeSelectedOrder() {
        int selectedIndex = completedOrdersListView.getCheckedItemPosition();
        if (selectedIndex >= 0) {
            OrderSingleton.getInstance().removeCompletedOrder(selectedIndex);
            updateOrderDisplay();
            completedOrdersListView.clearChoices(); // Clear selection
            Toast.makeText(this, getString(R.string.toast_order_removed), Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, getString(R.string.toast_no_order_selected), Toast.LENGTH_SHORT).show();
        }
    }
}