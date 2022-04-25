package com.example.vcard;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class view_manufacturer_profile extends AppCompatActivity {
    TextView view_profile;
    TextView name;
    TextView address;
    TextView contact;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_manufacturer_profile);
        view_profile = (TextView) findViewById(R.id.textView19);
        name = (TextView) findViewById(R.id.textView20);
        address = (TextView) findViewById(R.id.textView22);
        contact = (TextView) findViewById(R.id.textView24);

    }
}