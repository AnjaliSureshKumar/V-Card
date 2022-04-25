package com.example.vcard;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class edit_cust_profile extends AppCompatActivity implements View.OnClickListener {
    EditText name;
    EditText place;
    EditText post;
    EditText district;
    EditText pin;
    EditText phone_number;
    EditText email;
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_cust_profile);
        name = (EditText) findViewById(R.id.editTextTextPersonName44);
        place = (EditText) findViewById(R.id.editTextTextPersonName45);
        post = (EditText) findViewById(R.id.editTextTextPersonName46);
        district = (EditText) findViewById(R.id.editTextTextPersonName47);
        pin = (EditText) findViewById(R.id.editTextTextPersonName48);
        phone_number = (EditText) findViewById(R.id.editTextTextPersonName49);
        email = (EditText) findViewById(R.id.editTextTextPersonName50);
        btn = (Button) findViewById(R.id.button29);
        btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        final String uname = name.getText().toString();
        final String Place = place.getText().toString();
        final String Post = post.getText().toString();
        final String District = district.getText().toString();
        final String Pin = pin.getText().toString();
        final String Phone_number = phone_number.getText().toString();
        final String Email = email.getText().toString();
        if (uname.length()==0){
            name.setError("Field cannot be empty");
        }
        else if (Place.length()==0) {
            place.setError("Field cannot be empty");
        }
        else if (Post.length()==0) {
            post.setError("Field cannot be empty");
        }
        else if (District.length()==0) {
            district.setError("Field cannot be empty");
        }
        else if (Pin.length()==0) {
            pin.setError("Field cannot be empty");
        }
        else if (Phone_number.length()==0) {
            phone_number.setError("Field cannot be empty");
        }
        else if (Email.length()==0) {
            email.setError("Field cannot be empty");
        }
        else {

        }
    }
}