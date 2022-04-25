package com.example.vcard;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

public class signup extends AppCompatActivity implements View.OnClickListener {
    ImageView customer;
    ImageView entrepreneur;
    ImageView manufacturer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);
        customer = (ImageView) findViewById(R.id.imageView4);
        entrepreneur = (ImageView) findViewById(R.id.imageView5);
        manufacturer = (ImageView) findViewById(R.id.imageView6);
        customer.setOnClickListener(this);
        entrepreneur.setOnClickListener(this);
        manufacturer.setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {
        if(view==customer)
        {

            Intent i = new Intent(getApplicationContext(),Customer_Signup.class);
            startActivity(i);
        }

        if(view==entrepreneur)
        {

            Intent i = new Intent(getApplicationContext(),Entrepreneur_Signup.class);
            startActivity(i);
        }

        if(view==manufacturer)
        {

            Intent i = new Intent(getApplicationContext(),Manufacturer_Signup.class);
            startActivity(i);
        }
    }
}