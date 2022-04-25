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

public class Custom_manuf_view_orderfrom_entr extends BaseAdapter { String[]uname,uphone,ordermount,orderdate,oid;
    private Context context;
    public Custom_manuf_view_orderfrom_entr(Context appcontext, String[]uname,String[]uphone,String[]ordermount,String[]orderdate,String[]oid) {
        this.context = appcontext;
        this.uname = uname;
        this.uphone = uphone;
        this.orderdate = orderdate;
        this.ordermount = ordermount;
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
            gridView = inflator.inflate(R.layout.custom_manuf_view_order_from_entr, null);

        } else {
            gridView = (View) view;

        }
        TextView shopname = (TextView) gridView.findViewById(R.id.name);
        TextView tvphone = (TextView) gridView.findViewById(R.id.phone);
        TextView tdate = (TextView) gridView.findViewById(R.id.date);
        TextView tvamount = (TextView) gridView.findViewById(R.id.amount);
        Button btproduct = (Button) gridView.findViewById(R.id.products);
        btproduct.setTag(i);
        btproduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int pos = (int) v.getTag();
                SharedPreferences sh = PreferenceManager.getDefaultSharedPreferences(context.getApplicationContext());
                SharedPreferences.Editor ed = sh.edit();
                ed.putString("orderid", oid[pos]);


                ed.commit();


                Intent i = new Intent(context.getApplicationContext(), ent_view_previous_history_more.class);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(i);

            }
        });

//        Button btmap = (Button) gridView.findViewById(R.id.btmap);


        shopname.setTextColor(Color.BLACK);
        tvamount.setTextColor(Color.BLACK);
        tdate.setTextColor(Color.BLACK);
        tvphone.setTextColor(Color.BLACK);

        shopname.setText(uname[i]);
        tvamount.setText(ordermount[i]);
        tdate.setText(orderdate[i]);
        tvphone.setText(uphone[i]);

        return gridView;
    }
}
