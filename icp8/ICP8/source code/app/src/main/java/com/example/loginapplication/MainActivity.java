package com.example.loginapplication;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btn = (Button) findViewById(R.id.lgnbutton);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Loginpage();
            }
        });
    }

    public void Loginpage()
    {
        EditText usernameCtrl = (EditText) findViewById(R.id.username);
        EditText passwordCtrl = (EditText) findViewById(R.id.password);

        String username = usernameCtrl.getText().toString();
        String password = passwordCtrl.getText().toString();

        if(!username.isEmpty() && !password.isEmpty())
        {
            if(username.equals("admin")&&password.equals("1234"))
            {
                Intent redirect = new Intent(MainActivity.this, SecondActivity.class);
                startActivity(redirect);

            }
            else
            {
                new AlertDialog.Builder(MainActivity.this)
                        .setMessage("Invalid Credentials, please re-enter username and password")
                        .setCancelable(true)
                        .setPositiveButton(
                                "OK", (dialog, id) -> dialog.cancel())
                        .show();
            }
        }

    }
}