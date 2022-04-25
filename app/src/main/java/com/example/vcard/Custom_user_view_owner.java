package com.example.vcard;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class Custom_user_view_owner extends BaseAdapter {
    String[]login_id,shop_name,phone_number,email,latitude,longitude;
    private Context context;
    public Custom_user_view_owner(Context appcontext, String[]login_id,String[]shop_name,String[]phone_number,String[]email,String[]latitude,String[]longitude) {
        this.context = appcontext;
        this.login_id = login_id;
        this.shop_name = shop_name;
        this.phone_number = phone_number;
        this.email = email;
        this.latitude = latitude;
        this.longitude = longitude;

    }

    @Override
    public int getCount() {
        return shop_name.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup parent) {

        LayoutInflater inflator=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View gridView;
        if(view==null)
        {
            gridView=new View(context);
            //gridView=inflator.inflate(R.layout.customview, null);
            gridView=inflator.inflate(R.layout.custom_user_view_owner,null);

        }
        else
        {
            gridView=(View)view;

        }
        TextView shopname=(TextView)gridView.findViewById(R.id.tvshopname);
        TextView temail=(TextView)gridView.findViewById(R.id.tvemail);
        TextView tvphone=(TextView)gridView.findViewById(R.id.tvphone);
        ImageView imgrate=(ImageView) gridView.findViewById(R.id.imgrating);
        imgrate.setTag(i);
        imgrate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int pos= (int) v.getTag();
                SharedPreferences sh= PreferenceManager.getDefaultSharedPreferences(context.getApplicationContext());
                SharedPreferences.Editor ed = sh.edit();
                ed.putString("ownerlid", login_id[pos]);


                ed.commit();


                Intent i = new Intent(context.getApplicationContext(),User_send_feedback_and_rationg.class);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(i);

            }
        });




        Button btproduct=(Button) gridView.findViewById(R.id.btproduct);
        btproduct.setTag(i);
        btproduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int pos= (int) v.getTag();
                SharedPreferences sh= PreferenceManager.getDefaultSharedPreferences(context.getApplicationContext());
                SharedPreferences.Editor ed = sh.edit();
                ed.putString("ownerlid", login_id[pos]);


                ed.commit();


                Intent i = new Intent(context,User_view_owner_product.class);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(i);

            }
        });

        Button btmap=(Button) gridView.findViewById(R.id.btmap);


        shopname.setTextColor(Color.BLACK);
        temail.setTextColor(Color.BLACK);
        tvphone.setTextColor(Color.BLACK);

        shopname.setText(shop_name[i]);
        temail.setText(email[i]);
        tvphone.setText(phone_number[i]);

        return gridView;
    }
}
