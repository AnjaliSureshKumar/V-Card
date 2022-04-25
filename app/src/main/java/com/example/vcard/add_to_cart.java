package com.example.vcard;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class add_to_cart extends AppCompatActivity implements View.OnClickListener {
    ImageView img;
    TextView product_name;
    TextView price;
    TextView quantity;
    Button add;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_to_cart);
        product_name = (TextView) findViewById(R.id.textView32);
        price = (TextView) findViewById(R.id.textView39);
        quantity = (TextView) findViewById(R.id.textView41);
        img = (ImageView) findViewById(R.id.imageView9);
        add.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

    }
}