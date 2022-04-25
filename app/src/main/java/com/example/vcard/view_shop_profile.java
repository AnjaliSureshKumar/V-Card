package com.example.vcard;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class view_shop_profile extends AppCompatActivity {
    TextView view_profile;
    TextView name;
    TextView shop;
    TextView address;
    TextView contact;
    ImageView img;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_shop_profile);
        view_profile = (TextView) findViewById(R.id.textView3);
        name = (TextView) findViewById(R.id.textView4);
        shop = (TextView) findViewById(R.id.textView6);
        address = (TextView) findViewById(R.id.textView8);
        contact = (TextView) findViewById(R.id.textView10);
        img = (ImageView)  findViewById(R.id.imageView2);

    }
}