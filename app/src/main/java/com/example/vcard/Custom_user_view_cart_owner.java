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

public class Custom_user_view_cart_owner extends BaseAdapter {

    String[] companyname,phone,lid,count;
    private Context context;

    public Custom_user_view_cart_owner(Context appcontext,String[]cname,String[]phone,String[]lid,String[]count) {
        this.context = appcontext;
        this.companyname = cname;
        this.phone = phone;
        this.lid = lid;
        this.count = count;
    }

    @Override
    public int getCount() {
        return count.length;
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
            gridView = inflator.inflate(R.layout.custom_user_view_cart_owner, null);

        } else {
            gridView = (View) view;

        }
        TextView tv1cname = (TextView) gridView.findViewById(R.id.tvcartshopname);
        TextView tv2phone = (TextView) gridView.findViewById(R.id.tvcartshopphone);
        TextView tv2count = (TextView) gridView.findViewById(R.id.textView68);
        Button btcartproduct = (Button) gridView.findViewById(R.id.btcartproduct);
        btcartproduct.setTag(i);

        btcartproduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int pos= (int) btcartproduct.getTag();
                SharedPreferences sh= PreferenceManager.getDefaultSharedPreferences(context);
                SharedPreferences.Editor ed = sh.edit();
                ed.putString("shoplid",lid[pos]);
                ed.commit();


                Intent i = new Intent(context,User_view_cart.class);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(i);
            }
        });


        tv1cname.setTextColor(Color.BLACK);
        tv2phone.setTextColor(Color.BLACK);
        tv2count.setTextColor(Color.BLACK);


        tv1cname.setText(companyname[i]);
        tv2phone.setText(phone[i]);
        tv2count.setText(count[i]);


        return gridView;
    }
}
