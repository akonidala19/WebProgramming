package com.example.vijaya.myorder;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;



// Home Activity to show Order Now Button.
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    // action On Click of Order Now button -- navigates to place_order screen
 public void orderPizza(View view) {
        Intent intent = new Intent(MainActivity.this, OrderActivity.class);
        startActivity(intent);
    }
}