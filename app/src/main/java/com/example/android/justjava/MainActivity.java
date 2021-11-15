package com.example.android.justjava;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {
    int quantity = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        calculatePrice();
    }

    /**
     * Calculates the price of the order.
     * @return total price.
     */
    private int calculatePrice() {
        CheckBox whippedCream = (CheckBox) findViewById(R.id.whippedcream);
        CheckBox chocolate = (CheckBox) findViewById(R.id.chocolate);
        EditText etext = (EditText) findViewById(R.id.name);
        if(whippedCream.isChecked() && chocolate.isChecked()){
            return quantity * (5 + 1 + 2);
        }
        else if(whippedCream.isChecked()){
            return quantity * (5 + 1);
        }
        else if(chocolate.isChecked()){
            return quantity * (5 + 2);
        }
        else{
            return quantity * 5;
        }

    }
    /**
     * Creates order summary.
     * @pram a number taking total price.
     * @pram a string taking name
     * @return order summary.
     */

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
        int price =  calculatePrice();
        EditText nameField = (EditText) findViewById(R.id.name);
        String name = nameField.getText().toString();
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("*/*");
        CheckBox whippedCream = (CheckBox) findViewById(R.id.whippedcream);
        CheckBox chocolate = (CheckBox) findViewById(R.id.chocolate);
        EditText etext = (EditText) findViewById(R.id.name);
        intent.putExtra(Intent.EXTRA_SUBJECT, "Just java order for " + name);
        intent.putExtra(Intent.EXTRA_TEXT, getString(R.string.order_summary_name, name) + "\n" + getString(R.string.add_whipped_cream) + whippedCream.isChecked() + "\n" + getString(R.string.add_chocolate) + chocolate.isChecked() + "\n" + getString(R.string.quantity2) + quantity + "\n" + getString(R.string.total) + price + "\n" + getString(R.string.thank_you));
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
        }

    /**
     * This method displays the given text on the screen.
     */

    /**
     * This method is called when the plus is clicked.
     */
    public void increment(View view) {
        if(quantity == 100){
            Toast.makeText(this, "you can not order coffees greater 100", Toast.LENGTH_SHORT).show();
            return;
        }
        quantity = quantity + 1;
        displayQuantity(quantity);
    }

    /**
     * This method is called when the minus button is clicked.
     */
    public void decrement(View view) {
        if(quantity == 1){
            Toast.makeText(this, "you can not order coffees less than 1", Toast.LENGTH_SHORT).show();
            return;
        }
        quantity = quantity - 1;
        displayQuantity(quantity);
    }


    /**
     * This method displays the given quantity value on the screen.
     */
    private void displayQuantity(int no) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + no);
    }


}