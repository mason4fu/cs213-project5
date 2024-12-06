package com.example.pizzeria;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

/**
 * Activity for choosing non custom pizzas to add to the order.
 *
 * @author YU FU, JOHNATHAN CHAN
 */

public class StyleActivity extends AppCompatActivity {

    /** Radio groups corresponding to those in the xml */
    private RadioGroup styleGroup, typeGroup, sizeGroup;

    /** Button corresponding to the addToOrder button on the xml*/
    private Button orderButton;

    /** Text areas giving information about the pizza chosen*/
    private TextView costText, toppingText;

    /** Image area to show a preview of the pizza the user plans to order*/
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_style);
        /*
        EdgeToEdge.enable(this);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
         */
        this.styleGroup = findViewById(R.id.styleGroup);
        this.typeGroup = findViewById(R.id.typeGroup);
        this.sizeGroup = findViewById(R.id.sizeGroup);
        this.orderButton = findViewById(R.id.addToOrder);
        this.costText = findViewById(R.id.costText);
        this.imageView = findViewById(R.id.imageView);
        this.toppingText = findViewById(R.id.toppingsText);

        RadioGroup.OnCheckedChangeListener listener = (group, checkedId) -> fieldsFilled();
        styleGroup.setOnCheckedChangeListener(listener);
        typeGroup.setOnCheckedChangeListener(listener);
        sizeGroup.setOnCheckedChangeListener(listener);
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

    /**
     * Checks if the radio groups have values selected, enabling the order button if they all do
     */
    private void fieldsFilled() {
        if (styleGroup.getCheckedRadioButtonId() != -1 &&
                typeGroup.getCheckedRadioButtonId() != -1 &&
                sizeGroup.getCheckedRadioButtonId() != -1) {
            orderButton.setEnabled(true);
            Pizza pizza = makePizza();
            String cost = getString(R.string.pricing) + " $" + pizza.price();
            costText.setText(cost);
        } else {
            orderButton.setEnabled(false);
            costText.setText(R.string.finish_choosing_pizza_to_see_pricing);
        }
        updateToppings();
        updateImages();
    }

/**
 * Update images displayed.
 */
    private void updateImages() {
        if (styleGroup.getCheckedRadioButtonId() != -1 && typeGroup.getCheckedRadioButtonId() != -1){
            fullImageSetter();
        } else if (typeGroup.getCheckedRadioButtonId() != -1) {
            typeImageSetter();
        } else if (styleGroup.getCheckedRadioButtonId() != -1) {
            styleImageSetter();
        } else {
            imageView.setImageResource(R.drawable.pizza);
        }
    }
/**
 * Set images based on style.
 */

    private void styleImageSetter(){
        String style = ((RadioButton)findViewById(styleGroup.getCheckedRadioButtonId())).getText().toString();
        if (style.equalsIgnoreCase(getString(R.string.new_york))){
            imageView.setImageResource(R.drawable.ny);
        } else if (style.equalsIgnoreCase(getString(R.string.chicago))) {
            imageView.setImageResource(R.drawable.chicago);
        } else {
            Toast.makeText(this, getString(R.string.error), Toast.LENGTH_SHORT).show();
            imageView.setImageResource(R.drawable.pizza);
        }
    }


/**
 * Set images based on type.
 */
    private void typeImageSetter(){
        String type = ((RadioButton)findViewById(typeGroup.getCheckedRadioButtonId())).getText().toString();
        if (type.equalsIgnoreCase(getString(R.string.deluxe))){
            imageView.setImageResource(R.drawable.nydeluxe);
        } else if (type.equalsIgnoreCase(getString(R.string.bbq_chicken))) {
            imageView.setImageResource(R.drawable.nybbqchicken);
        } else if (type.equalsIgnoreCase(getString(R.string.meatzza))) {
            imageView.setImageResource(R.drawable.nymeatzza);
        }else {
            Toast.makeText(this, getString(R.string.error), Toast.LENGTH_SHORT).show();
            imageView.setImageResource(R.drawable.pizza);
        }
    }


/**
 * Full image setter.
 */
    private void fullImageSetter(){
        String style = ((RadioButton)findViewById(styleGroup.getCheckedRadioButtonId())).getText().toString();
        if (style.equalsIgnoreCase(getString(R.string.new_york))){
            typeImageSetter();
        } else if (style.equalsIgnoreCase(getString(R.string.chicago))) {
            String type = ((RadioButton)findViewById(typeGroup.getCheckedRadioButtonId())).getText().toString();
            if (type.equalsIgnoreCase(getString(R.string.deluxe))){
                imageView.setImageResource(R.drawable.chicagodeluxe);
            } else if (type.equalsIgnoreCase(getString(R.string.bbq_chicken))) {
                imageView.setImageResource(R.drawable.chicagobbqchicken);
            } else if (type.equalsIgnoreCase(getString(R.string.meatzza))) {
                imageView.setImageResource(R.drawable.chicagomeatzza);
            }else {
                Toast.makeText(this, getString(R.string.error), Toast.LENGTH_SHORT).show();
                imageView.setImageResource(R.drawable.pizza);
            }
        } else {
            Toast.makeText(this, getString(R.string.error), Toast.LENGTH_SHORT).show();
            imageView.setImageResource(R.drawable.pizza);
        }
    }

    /**
     * Creates a pizza object depending on what the user chooses
     *
     * @return pizza with the chosen parameters from the UI
     */
    private Pizza makePizza(){
        PizzaFactory pizzaFactory;
        Pizza currentPizza = null;
        try{
            String style = ((RadioButton)findViewById(styleGroup.getCheckedRadioButtonId())).getText().toString();
            if (style.equalsIgnoreCase(getString(R.string.new_york))){
                pizzaFactory = new NYPizza();
            } else if (style.equalsIgnoreCase(getString(R.string.chicago))) {
                pizzaFactory = new ChicagoPizza();
            } else {
                throw new RuntimeException();
            }
            String type = ((RadioButton)findViewById(typeGroup.getCheckedRadioButtonId())).getText().toString();
            if (type.equalsIgnoreCase(getString(R.string.deluxe))){
                currentPizza = pizzaFactory.createDeluxe();
            } else if (type.equalsIgnoreCase(getString(R.string.bbq_chicken))) {
                currentPizza = pizzaFactory.createBBQChicken();
            } else if (type.equalsIgnoreCase(getString(R.string.meatzza))) {
                currentPizza = pizzaFactory.createMeatzza();
            }else {
                throw new RuntimeException();
            }
            String size = ((RadioButton)findViewById(sizeGroup.getCheckedRadioButtonId())).getText().toString();
            if (size.equalsIgnoreCase(getString(R.string.large))){
                currentPizza.setSize(Size.LARGE);
            } else if (size.equalsIgnoreCase(getString(R.string.medium))) {
                currentPizza.setSize(Size.MEDIUM);
            } else if (size.equalsIgnoreCase(getString(R.string.small))) {
                currentPizza.setSize(Size.SMALL);
            }else {
                throw new RuntimeException();
            }
        } catch (RuntimeException e) {
            Toast.makeText(this, getString(R.string.error), Toast.LENGTH_SHORT).show();
        }
        return currentPizza;
    }

    /**
     * Updates the text that tells what toppings are on the selected type of pizza
     */
    private void updateToppings() {
        if(typeGroup.getCheckedRadioButtonId() == -1){
            return;
        }
        String type = ((RadioButton)findViewById(typeGroup.getCheckedRadioButtonId())).getText().toString();
        if (type.equalsIgnoreCase(getString(R.string.deluxe))){
            toppingText.setText(getString(R.string.deluxe_info));
        } else if (type.equalsIgnoreCase(getString(R.string.bbq_chicken))) {
            toppingText.setText(getString(R.string.bbq_chicken_info));
        } else if (type.equalsIgnoreCase(getString(R.string.meatzza))) {
            toppingText.setText(getString(R.string.meatzza_info));
        }else {
            toppingText.setText(getString(R.string.type_toppings));
        }
    }

    /**
     * Adds the pizza with the user's specified parameters to the order
     *
     * @param view is unused in the method, but is required for the signature
     */
    public void addToOrder(View view) {
        Order order = OrderSingleton.getInstance().getCurrentOrder();
        order.orderAdd(makePizza());
        styleGroup.clearCheck();
        typeGroup.clearCheck();
        sizeGroup.clearCheck();
        toppingText.setText(getString(R.string.type_toppings));
        fieldsFilled();
        Toast.makeText(this, getString(R.string.ordered), Toast.LENGTH_SHORT).show();
    }

    /**
     * Opens the main activity, where the user can then navigate else where
     *
     * @param view is unused in the method, but is required for the signature
     */
    public void toMain(View view){
        Log.d("ActivityNavigation", "toMain triggered by: " + view.getId());
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}