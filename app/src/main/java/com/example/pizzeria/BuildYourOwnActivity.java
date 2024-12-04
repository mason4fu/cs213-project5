package com.example.pizzeria;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * Activity for the "Build Your Own Pizza" screen.
 * Enforces user actions in sequence and handles warnings.
 */
public class BuildYourOwnActivity extends AppCompatActivity {

    private Spinner sizeSpinner;
    private Button chicagoStyleButton, nyStyleButton, returnToHomeButton;
    private ImageView crustImageView, toppingImageView;
    private RecyclerView toppingsRecyclerView;
    private ListView selectedToppingsListView;
    private TextView subtotalLabel;
    private Button addToOrderButton, removeToppingButton;

    private BuildYourOwn pizza;
    private ArrayAdapter<Topping> selectedToppingsAdapter;
    private List<Topping> selectedToppings;
    private ToppingsAdapter toppingsAdapter;

    private boolean isSizeSelected = false;
    private boolean isCrustSelected = false;

    private static final int MAX_TOPPINGS = 7;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buildyourown);

        // Initialize UI components
        sizeSpinner = findViewById(R.id.sizeSpinner);
        chicagoStyleButton = findViewById(R.id.chicagoStyleButton);
        nyStyleButton = findViewById(R.id.nyStyleButton);
        crustImageView = findViewById(R.id.crustImageView);
        toppingImageView = findViewById(R.id.toppingImageView);
        toppingsRecyclerView = findViewById(R.id.toppingsRecyclerView);
        selectedToppingsListView = findViewById(R.id.selectedToppingsListView);
        subtotalLabel = findViewById(R.id.subtotalLabel);
        addToOrderButton = findViewById(R.id.addToOrderButton);
        removeToppingButton = findViewById(R.id.removeToppingButton);
        returnToHomeButton = findViewById(R.id.returnToHomeButton);

        pizza = new BuildYourOwn();
        selectedToppings = new ArrayList<>();

        // Set up Spinner for size selection
        ArrayAdapter<String> sizeAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item);
        sizeAdapter.add(getString(R.string.select_size)); // Default
        for (Size size : Size.values()) {
            sizeAdapter.add(size.toString());
        }
        sizeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sizeSpinner.setAdapter(sizeAdapter);

        // Add size change listener to Spinner
        sizeSpinner.setOnItemSelectedListener(new android.widget.AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(android.widget.AdapterView<?> parent, android.view.View view, int position, long id) {
                if (position == 0) { // Default option selected
                    isSizeSelected = false;
                    pizza.setSize(null);
                    showWarning(getString(R.string.warning_select_size));
                } else {
                    isSizeSelected = true;
                    pizza.setSize(Size.valueOf(sizeSpinner.getSelectedItem().toString()));
                    updateSubtotal();
                }
            }

            @Override
            public void onNothingSelected(android.widget.AdapterView<?> parent) {
                isSizeSelected = false;
            }
        });

        // Set up RecyclerView for available toppings
        toppingsAdapter = new ToppingsAdapter(this, Topping.values(), this::addTopping);
        toppingsRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        toppingsRecyclerView.setAdapter(toppingsAdapter);

        // Set up ListView for selected toppings
        selectedToppingsAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_activated_1, selectedToppings);
        selectedToppingsListView.setAdapter(selectedToppingsAdapter);
        selectedToppingsListView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);

        // Set button actions
        chicagoStyleButton.setOnClickListener(v -> selectCrust(Crust.DEEPDISH, R.drawable.chicago));
        nyStyleButton.setOnClickListener(v -> selectCrust(Crust.THIN, R.drawable.ny));
        addToOrderButton.setOnClickListener(v -> addToOrder());
        removeToppingButton.setOnClickListener(v -> removeTopping());
        returnToHomeButton.setOnClickListener(v -> finish());

        updateSubtotal();
    }

    private void selectCrust(Crust crust, int imageResId) {
        if (!isSizeSelected) {
            showWarning(getString(R.string.warning_select_size_first));
            return;
        }
        pizza.setCrust(crust);
        crustImageView.setImageResource(imageResId);
        isCrustSelected = true;
        updateSubtotal();
    }

    private void addTopping(Topping topping) {
        if (!isSizeSelected) {
            showWarning(getString(R.string.warning_select_size_first));
            return;
        }
        if (!isCrustSelected) {
            showWarning(getString(R.string.warning_select_style_first));
            return;
        }
        if (selectedToppings.size() >= MAX_TOPPINGS) {
            showWarning(getString(R.string.warning_max_toppings));
        } else {
            selectedToppings.add(topping);
            pizza.setToppings(new ArrayList<>(selectedToppings)); // Sync with pizza object
            selectedToppingsAdapter.notifyDataSetChanged();
            updateSubtotal();
        }

        // Display the topping image
        displayToppingImage(topping);
    }

    private void displayToppingImage(Topping topping) {
        String imageName = topping.toString().toLowerCase(); // Convert to lowercase
        int imageResId = getResources().getIdentifier(imageName, "drawable", getPackageName());
        toppingImageView.setImageResource(imageResId);
    }

    private void removeTopping() {
        int selectedIndex = selectedToppingsListView.getCheckedItemPosition();
        if (selectedIndex >= 0) {
            selectedToppings.remove(selectedIndex);
            pizza.setToppings(new ArrayList<>(selectedToppings)); // Sync with pizza object
            selectedToppingsAdapter.notifyDataSetChanged();
            updateSubtotal();
        }
    }

    private void addToOrder() {
        if (!isSizeSelected || !isCrustSelected) {
            showWarning(getString(R.string.warning_complete_selection));
            return;
        }

        pizza.setToppings(new ArrayList<>(selectedToppings));

        double subtotal = pizza.price();
        if (subtotal < 0) {
            showWarning(getString(R.string.warning_invalid_configuration));
            return;
        }

        OrderSingleton.getInstance().addPizzaToOrder(pizza);
        showAlert(getString(R.string.success_title), getString(R.string.success_message, pizza));
        resetSelections();
    }

    private void resetSelections() {
        sizeSpinner.setSelection(0);
        selectedToppings.clear();
        selectedToppingsAdapter.notifyDataSetChanged();
        pizza = new BuildYourOwn();
        crustImageView.setImageResource(0);
        toppingImageView.setImageResource(0);
        isSizeSelected = false;
        isCrustSelected = false;
        updateSubtotal();
    }

    private void updateSubtotal() {
        double subtotal = pizza.price();
        subtotalLabel.setText(getString(R.string.subtotal_label, Math.max(subtotal, 0)));
    }

    private void showWarning(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    private void showAlert(String title, String message) {
        new AlertDialog.Builder(this)
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton(getString(R.string.ok), null)
                .show();
    }
}