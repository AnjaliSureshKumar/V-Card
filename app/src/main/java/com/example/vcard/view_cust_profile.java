package com.example.vcard;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class view_cust_profile extends AppCompatActivity {
    TextView view_profile;
    TextView name;
    TextView address;
    TextView contact;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_cust_profile);
        view_profile = (TextView) findViewById(R.id.textView12);
        name = (TextView) findViewById(R.id.textView13);
        address = (TextView) findViewById(R.id.textView15);
        contact = (TextView) findViewById(R.id.textView17);
    }

}