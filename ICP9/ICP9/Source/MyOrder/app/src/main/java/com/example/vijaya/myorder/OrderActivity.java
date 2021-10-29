package com.example.vijaya.myorder;

        import android.content.Context;
        import android.content.Intent;
        import android.os.Bundle;
        import android.support.v7.app.AppCompatActivity;
        import android.util.Log;
        import android.view.View;
        import android.widget.AdapterView;
        import android.widget.ArrayAdapter;
        import android.widget.CheckBox;
        import android.widget.EditText;
        import android.widget.Spinner;
        import android.widget.TextView;
        import android.widget.Toast;
        import org.apache.commons.lang3.BooleanUtils;
        import java.util.ArrayList;
        import java.util.List;

public class OrderActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{
    private static final Integer PIZZA_PRICE = 7;
    private static final Integer CHICKEN_PRICE = 3;
    private static final Integer ONION_PRICE = 2;
    private static final Integer PINEAPPLE_PRICE = 1;
    float totalPrice;
    Integer quantity = 1;
    String orderSummary;
    EditText userNameText;
    TextView quantityTextView;
    CheckBox chickenChecked, onionChecked, pineappleChecked;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        quantityTextView = findViewById(R.id.quantity);
        userNameText = findViewById(R.id.name);
        chickenChecked = findViewById(R.id.chicken_checked);
        onionChecked = findViewById(R.id.onion_checked);
        pineappleChecked = findViewById(R.id.pineapple_checked);
        Spinner spinner = (Spinner) findViewById(R.id.spinner);

        // Spinner click listener
        spinner.setOnItemSelectedListener((AdapterView.OnItemSelectedListener) this);

        // Spinner Drop down elements
        List<String> pizzaSize = new ArrayList<String>();
        pizzaSize.add("Small");
        pizzaSize.add("Medium");
        pizzaSize.add("Large");

        // Creating adapter for spinner
        ArrayAdapter<String> screenAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, pizzaSize);

        // Drop down layout style - list view with radio button
        screenAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        spinner.setAdapter(screenAdapter);
    }

    // validation to check if user name is empty
    private boolean noUser(){
        String userName = userNameText.getText().toString();
        if(userName == null || userName.isEmpty()){
            Context context = getApplicationContext();
            String upperLimitToast = getString(R.string.userNull);
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(context, upperLimitToast, duration);
            toast.show();
            return true;
        }
        return false;
    }

// retrieve the selected toppings and calculate the price
    private String getDetails() {
        boolean chicken = chickenChecked.isChecked();
        boolean onion = onionChecked.isChecked();
        boolean pineapple = pineappleChecked.isChecked();

        totalPrice = calculatePrice(chicken, onion, pineapple, quantity);
        return fetchOrderSummary(userNameText.getText().toString(), chicken, onion, pineapple, totalPrice);
    }

    public void orderSummary(View view) {
        if (!noUser()) {
            orderSummary = getDetails();
            Intent intent = new Intent(OrderActivity.this, SummaryActivity.class);
            intent.putExtra("orderSummary", orderSummary);
            startActivity(intent);
        }
    }

// define email content
    public void orderPizzaMain(View view) {
        if (!noUser()) {
            orderSummary = getDetails();
            Intent emailIntent = new Intent(Intent.ACTION_SEND);
            emailIntent.setType("plain/text");
            emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[] {"005kvs@gmail.com"});
            emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Order Summary");
            emailIntent.putExtra(Intent.EXTRA_TEXT, orderSummary);
            startActivity(Intent.createChooser(emailIntent, ""));
        }
    }

// get the order summary
    private String fetchOrderSummary(String Name, boolean chicken, boolean onion,
                                     boolean pineapple, float totalPrice) {
        return getString(R.string.order_summary_name,Name) +"\n"+
                getString(R.string.order_summary_chicken, BooleanUtils.toStringYesNo(chicken))+"\n"+
                getString(R.string.order_summary_Onion,BooleanUtils.toStringYesNo(onion)) +"\n"+
                getString(R.string.order_summary_Pineapple,BooleanUtils.toStringYesNo(pineapple)) +"\n"+
                getString(R.string.order_summary_quantity,quantity)+"\n"+
                getString(R.string.order_summary_total_price,totalPrice) +"\n"+
                getString(R.string.thank_you);
    }

// calculate total price with the selected toppings
    private float calculatePrice(boolean chicken, boolean onion, boolean pineapple, Integer quantity) {
        int basePrice = PIZZA_PRICE;
        if (chicken) {
            basePrice += CHICKEN_PRICE;
        }
        if (onion) {
            basePrice += ONION_PRICE;
        }

        if(pineapple){
            basePrice +=PINEAPPLE_PRICE;
        }
        return quantity * basePrice;
    }

// increment the quantity
// throw an error if quantity is more than 20
    public void increment(View view) {
        if (quantity < 20) {
            quantity = quantity + 1;
            display(quantity);
        } else {
            Log.i("PizzaOrder", "Please select less than 20 Pizzas");
            Context context = getApplicationContext();
            String lowerLimitToast = "Please select less than 20 Pizzas";
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(context, lowerLimitToast, duration);
            toast.show();
            return;
        }
    }

// decrement the quantity
// throw an error if quantity is less than one
    public void decrement(View view) {
        if (quantity > 1) {
            quantity = quantity - 1;
            display(quantity);
        } else {
            Log.i("pizzaOrder", "Please select at least one Pizza");
            Context context = getApplicationContext();
            String upperLimitToast = "Please select at  least one Pizza";
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(context, upperLimitToast, duration);
            toast.show();
            return;
        }
    }

    private void display(int number) {
        quantityTextView.setText("" + number);
    }

    @Override


    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        // On selecting a spinner item
        String item = parent.getItemAtPosition(position).toString();

        // Showing selected spinner item
        Toast.makeText(parent.getContext(), "Selected: " + item, Toast.LENGTH_LONG).show();
    }
    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub
    }

}


