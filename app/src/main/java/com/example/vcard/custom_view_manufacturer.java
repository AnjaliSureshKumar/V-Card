package com.example.vcard;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class custom_view_manufacturer extends BaseAdapter {
    String[] name, company_name, email, phone_number,manufacturer_login_id;
    private Context context;


    public custom_view_manufacturer(Context appcontext, String[] name, String[] company_name, String[] email, String[] phone_number,String[]manufacturer_login_id) {
        this.context = appcontext;
        this.name = name;
        this.company_name = company_name;
        this.email = email;
        this.phone_number = phone_number;
        this.manufacturer_login_id=manufacturer_login_id;


    }

    @Override
    public int getCount() {
        return email.length;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        LayoutInflater inflator = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View gridView;
        if (view == null) {
            gridView = new View(context);
            //gridView=inflator.inflate(R.layout.customview, null);
            gridView = inflator.inflate(R.layout.custom_view_manufacturer, null);

        } else {
            gridView = (View) view;

        }
        TextView tv1 = (TextView) gridView.findViewById(R.id.textView49);
        TextView tv2 = (TextView) gridView.findViewById(R.id.textView50);
        TextView tv3 = (TextView) gridView.findViewById(R.id.textView51);
        TextView tv4 = (TextView) gridView.findViewById(R.id.textView52);
        Button view_product = (Button) gridView.findViewById(R.id.button32);
        view_product.setTag(manufacturer_login_id[i]);
        view_product.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                SharedPreferences sh = PreferenceManager.getDefaultSharedPreferences(context.getApplicationContext());
                SharedPreferences.Editor ed = sh.edit();
                ed.putString("manufacturer_login_id", manufacturer_login_id[i]);
                ed.commit();


                Intent i = new Intent(context.getApplicationContext(), view_product_man.class);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(i);

            }

        });
        ImageView brate = (ImageView) gridView.findViewById(R.id.rate);
        brate.setTag(manufacturer_login_id[i]);
        brate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                SharedPreferences sh = PreferenceManager.getDefaultSharedPreferences(context.getApplicationContext());
                SharedPreferences.Editor ed = sh.edit();
                ed.putString("manufacturer_login_id", manufacturer_login_id[i]);
                ed.commit();


                Intent i = new Intent(context.getApplicationContext(), ent_send_rate_review_manuf.class);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(i);

            }

        });


        tv1.setTextColor(Color.BLACK);
        tv2.setTextColor(Color.BLACK);
        tv3.setTextColor(Color.BLACK);
        tv4.setTextColor(Color.BLACK);


        tv1.setText(name[i]);
        tv2.setText(company_name[i]);
        tv3.setText(email[i]);
        tv4.setText(phone_number[i]);


        SharedPreferences sh = PreferenceManager.getDefaultSharedPreferences(context);
        String ip = sh.getString("ip", "");


        return gridView;
    }

    }


