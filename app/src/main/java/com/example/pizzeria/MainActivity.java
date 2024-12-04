package com.example.pizzeria;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

/**
 * MainActivity: Activity that controls activity_main.xml.
 * This is the main controller for the Pizzeria application.
 * Handles navigation to different features of the app.
 *
 * @author YU
 * @author JOHNATHAN CHAN
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); // Load XML layout
    }

    /**
     * Navigates to the StyleActivity where the user can select pizza styles.
     */
    public void openStyleActivity(View view) {
        Intent intent = new Intent(MainActivity.this, StyleActivity.class);
        startActivity(intent);
    }

    /**
     * Navigates to the BuildYourOwnActivity for customizing pizzas.
     */
    public void openBuildYourOwnActivity(View view) {
        Intent intent = new Intent(MainActivity.this, BuildYourOwnActivity.class);
        startActivity(intent);
    }

    /**
     * Navigates to the CheckoutActivity for reviewing and finalizing orders.
     */
    public void openCheckoutActivity(View view) {
        Intent intent = new Intent(MainActivity.this, CheckoutActivity.class);
        startActivity(intent);
    }

    /**
     * Navigates to the ViewOrdersActivity to manage and view store orders.
     */
    public void openViewOrdersActivity(View view) {
        Intent intent = new Intent(MainActivity.this, OrdersActivity.class);
        startActivity(intent);
    }
}