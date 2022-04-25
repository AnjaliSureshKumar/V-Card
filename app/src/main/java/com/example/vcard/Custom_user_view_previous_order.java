package com.example.vcard;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

public class Custom_user_view_previous_order extends BaseAdapter {
    String[]seller_name,total,date,oid;
    private Context context;
    public Custom_user_view_previous_order(Context appcontext, String[]seller_name,String[]total,String[]date,String[]oid) {
        this.context = appcontext;
        this.seller_name = seller_name;
        this.total = total;
        this.date = date;
        this.oid = oid;


    }

    @Override
    public int getCount() {
        return oid.length;
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

        LayoutInflater inflator = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View gridView;
        if (view == null) {
            gridView = new View(context);
            //gridView=inflator.inflate(R.layout.customview, null);
            gridView = inflator.inflate(R.layout.custom_user_view_previous_order, null);

        } else {
            gridView = (View) view;

        }
        TextView shopname = (TextView) gridView.findViewById(R.id.tvordershop);
        TextView tdate = (TextView) gridView.findViewById(R.id.tvorderdate);
        TextView tvamount = (TextView) gridView.findViewById(R.id.tvorderamount);
        Button btproduct = (Button) gridView.findViewById(R.id.btorderproduct);
        btproduct.setTag(i);
        btproduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int pos = (int) v.getTag();
                SharedPreferences sh = PreferenceManager.getDefaultSharedPreferences(context.getApplicationContext());
                SharedPreferences.Editor ed = sh.edit();
                ed.putString("orderid", oid[pos]);


                ed.commit();


                Intent i = new Intent(context.getApplicationContext(), User_view_previous_hostory_more.class);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(i);

            }
        });

//        Button btmap = (Button) gridView.findViewById(R.id.btmap);


        shopname.setTextColor(Color.BLACK);
        tvamount.setTextColor(Color.BLACK);
        tdate.setTextColor(Color.BLACK);

        shopname.setText(seller_name[i]);
        tvamount.setText(total[i]);
        tdate.setText(date[i]);

        return gridView;
    }
}
