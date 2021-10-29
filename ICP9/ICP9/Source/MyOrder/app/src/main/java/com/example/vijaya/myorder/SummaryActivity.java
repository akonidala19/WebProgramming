package com.example.vijaya.myorder;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.view.View;
import android.widget.TextView;

public class SummaryActivity extends AppCompatActivity {
    TextView summaryText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summary);
        summaryText = findViewById(R.id.summaryText);
        summaryText.setText(Html.fromHtml("<u>Your Orders:</u><br/><br/>"));
        if(getIntent() != null){
            summaryText.append(getIntent().getStringExtra("orderSummary"));
        }else{
            summaryText.setText("You have no orders. Order now!!");
        }
        summaryText.append(Html.fromHtml("<br/>"));
        summaryText.setVisibility(View.VISIBLE);
    }

    // action On Click of Order Now button -- navigates to place_order screen
    public void orderPizza(View view) {
        Intent intent = new Intent(SummaryActivity.this, OrderActivity.class);
        startActivity(intent);
    }
}
