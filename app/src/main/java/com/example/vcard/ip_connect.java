package com.example.vcard;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class ip_connect extends AppCompatActivity implements View.OnClickListener {
    EditText ip_ed;
    Button btn;
    @Override
    public void onBackPressed() {
        super.onBackPressed();

        startActivity(new Intent(getApplicationContext(),ip_connect.class));
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ip_connect);
        ip_ed = (EditText) findViewById(R.id.editTextTextPersonName36);
        ip_ed.setText("192.168.43.37");
        btn = (Button) findViewById(R.id.button6);
        btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        final String ip_connect = ip_ed.getText().toString();
        if (ip_connect.length()==0){
            ip_ed.setError("Field cannot be empty");
        }
        else {
            SharedPreferences sh= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
            SharedPreferences.Editor ed = sh.edit();
            ed.putString("ip", ip_connect);
            ed.commit();


            Intent i = new Intent(getApplicationContext(),Login.class);
            startActivity(i);





        }
    }
}