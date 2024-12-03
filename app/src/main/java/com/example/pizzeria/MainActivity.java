package com.example.pizzeria;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

/**
 * Main entry point for the Pizzeria app.
 * Sets up the initial activity and GUI for the pizzeria.
 *
 * @author YU FU
 * @author JOHNATHAN CHAN
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Set the layout for the activity
        setContentView(R.layout.activity_main);

        // Adjust for system bars (status bar and navigation bar)
        /*
        View rootView = findViewById(R.id.main);
        ViewCompat.setOnApplyWindowInsetsListener(rootView, (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        */

        //Toast.makeText(this, "onCreate()", Toast.LENGTH_SHORT).show();
    }

    /**
     * A callback method executed right after onCreate().
     */
    protected void onStart() {
        super.onStart();
        //Toast.makeText(this, "onStart()", Toast.LENGTH_SHORT).show();
    }

    /**
     * A callback method executed right after onStart().
     */
    protected void onResume() {
        super.onResume();
        //Toast.makeText(this, "onResume()", Toast.LENGTH_SHORT).show();
    }

    public void openStyle(View view){
        Intent intent = new Intent(this, StyleActivity.class);
        startActivity(intent);
    }
}