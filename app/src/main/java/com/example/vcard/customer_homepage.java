package com.example.vcard;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class customer_homepage extends AppCompatActivity implements View.OnClickListener {
    Button btn1;
    Button btn2;
    Button btn3;
    Button btn4;
    Button btn5;
    Button btn6;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.customer_homepage);
        btn1 = (Button) findViewById(R.id.button8);
        btn2 = (Button) findViewById(R.id.button9);
        btn3 = (Button) findViewById(R.id.button10);
        btn4 = (Button) findViewById(R.id.button11);
        btn5 = (Button) findViewById(R.id.button12);
        btn6 = (Button) findViewById(R.id.button26);
        btn1.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        if (view == btn1) {
            Intent i = new Intent(getApplicationContext(), change_password.class);
            startActivity(i);
        }
    }
}
