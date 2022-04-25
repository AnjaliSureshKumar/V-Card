package com.example.vcard;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class User_view_owner_rate_feedback extends AppCompatActivity implements View.OnClickListener {
FloatingActionButton f1;

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        startActivity(new Intent(getApplicationContext(),UserHome.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_view_owner_rate_feedback);
        f1=(FloatingActionButton) findViewById(R.id.floatingActionButton);
        f1.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        startActivity(new Intent(getApplicationContext(),User_send_feedback_and_rationg.class));
    }
}