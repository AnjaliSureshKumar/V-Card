package com.example.vcard;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AdminHome extends AppCompatActivity implements View.OnClickListener {
Button bmanu,banter,btmanufrate,entrate,logout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_home);
        bmanu=(Button) findViewById(R.id.btmanuf);
        banter=(Button) findViewById(R.id.button25);
        btmanufrate=(Button) findViewById(R.id.btmanufrate);
        entrate=(Button) findViewById(R.id.ent_ratings);
        logout=(Button) findViewById(R.id.btlogout);

        bmanu.setOnClickListener(this);
        banter.setOnClickListener(this);
        btmanufrate.setOnClickListener(this);
        entrate.setOnClickListener(this);
        logout.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v==bmanu){
            startActivity(new Intent(getApplicationContext(),Admin_view_manuf.class));
        }if(v==banter){
            startActivity(new Intent(getApplicationContext(),Admin_view_enterP.class));
        }if(v==btmanufrate){
            startActivity(new Intent(getApplicationContext(),Admin_view_manuf_feedbacks.class));
        }if(v==entrate){
            startActivity(new Intent(getApplicationContext(),Admin_view_entrp_feedback.class));
        }if(v==logout){
            startActivity(new Intent(getApplicationContext(),Login.class));
        }
    }
}