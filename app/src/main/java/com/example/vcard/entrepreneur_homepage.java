package com.example.vcard;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class entrepreneur_homepage extends AppCompatActivity implements View.OnClickListener {
    Button btn1;
    Button btn2;
    Button btn3;
    Button btn4;
    Button btn5;
    Button btn6;
    Button btn7,btvent_cart,btmyorder,uorders,btent_logout;

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(getApplicationContext(),Login.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.entrepreneur_homepage);
        btn1 = (Button) findViewById(R.id.button15);

        btn3 = (Button) findViewById(R.id.button17);
        btn4 = (Button) findViewById(R.id.button18);
        btn5 = (Button) findViewById(R.id.button19);

        btn7 = (Button) findViewById(R.id.btent_logout);
        btvent_cart = (Button) findViewById(R.id.btent_cart);
        btmyorder = (Button) findViewById(R.id.btmyorders);
        uorders = (Button) findViewById(R.id.uorder);
        btent_logout = (Button) findViewById(R.id.btent_logout);



        btn3.setOnClickListener(this);
        btn4.setOnClickListener(this);
        btn1.setOnClickListener(this);
        btn5.setOnClickListener(this);

        btvent_cart.setOnClickListener(this);
        btmyorder.setOnClickListener(this);
        uorders.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        if (view == btn3) {
            Intent i = new Intent(getApplicationContext(), ent_add_product.class);
            startActivity(i);
        }
        if (view == btn4) {
            Intent i = new Intent(getApplicationContext(), view_product.class);
            startActivity(i);
        }
        if (view == btn1) {
            Intent i = new Intent(getApplicationContext(), ent_change_password.class);
            startActivity(i);
        }
        if (view == btn5) {
            Intent i = new Intent(getApplicationContext(), view_manufacturer.class);
            startActivity(i);
        }
        if (view == btvent_cart) {
            Intent i = new Intent(getApplicationContext(), ent_view_cart_manuf.class);
            startActivity(i);
        }
        if (view == btmyorder) {
            Intent i = new Intent(getApplicationContext(), ent_view_order_myhistory.class);
            startActivity(i);
        }  if (view == uorders) {
            Intent i = new Intent(getApplicationContext(), ent_view_orders_from_users.class);
            startActivity(i);
        }
 if (view == btent_logout) {
            Intent i = new Intent(getApplicationContext(), Login.class);
            startActivity(i);
        }



    }
}