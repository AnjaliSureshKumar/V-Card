package com.example.vcard;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class manufacturer_homepage extends AppCompatActivity implements View.OnClickListener {
    Button btn1;
    Button btn2;
    Button btn3;
    Button btn4;
    Button btn5;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.manufacturer_homepage);
        btn1 = (Button) findViewById(R.id.button20);
        btn2 = (Button) findViewById(R.id.button21);
        btn3 = (Button) findViewById(R.id.button22);
        btn4 = (Button) findViewById(R.id.button23);
        btn5 = (Button) findViewById(R.id.button24);
        btn2.setOnClickListener(this);
        btn1.setOnClickListener(this);
        btn3.setOnClickListener(this);
        btn4.setOnClickListener(this);
        btn5.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view == btn2) {
            Intent i = new Intent(getApplicationContext(), man_add_product.class);
            startActivity(i);
        }
        if (view == btn3) {
            Intent i = new Intent(getApplicationContext(), view_man_product.class);

            startActivity(i);
        }
        if (view == btn1) {
            Intent i = new Intent(getApplicationContext(), man_change_password.class);
            startActivity(i);
        } if (view == btn4) {
            Intent i = new Intent(getApplicationContext(),manuf_view_order_from_entr.class);
            startActivity(i);
        }if (view == btn5) {
            Intent i = new Intent(getApplicationContext(),Login.class);
            startActivity(i);
        }

    }
}