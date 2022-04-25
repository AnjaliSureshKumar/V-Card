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
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Custom_user_view_previous_order_more extends BaseAdapter {
    String[] product_name,quantity,image,price;

    private Context context;

    public Custom_user_view_previous_order_more(Context appcontext,String[]product_name,String[]quantity,String[]image,String[]price)
    {
        this.context=appcontext;

        this.product_name=product_name;
        this.quantity=quantity;
        this.image=image;
        this.price=price;




    }

    @Override
    public int getCount() {
        return price.length;
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
    public View getView(final int i, View view, ViewGroup viewGroup) {
        LayoutInflater inflator = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View gridView;
        if (view == null) {
            gridView = new View(context);
            //gridView=inflator.inflate(R.layout.customview, null);
            gridView = inflator.inflate(R.layout.custom_user_view_previous_order_more, null);

        } else {
            gridView = (View) view;

        }
        TextView tv1 = (TextView) gridView.findViewById(R.id.textView16);
        TextView tv2 = (TextView) gridView.findViewById(R.id.textView17);
        TextView tv3 = (TextView) gridView.findViewById(R.id.textView84);

        ImageView im = (ImageView) gridView.findViewById(R.id.imageView6);



        tv1.setTextColor(Color.BLACK);
        tv2.setTextColor(Color.BLACK);
        tv3.setTextColor(Color.BLACK);


        tv1.setText(product_name[i]);
        tv2.setText(quantity[i]);
        tv3.setText(price[i]);
//        Toast.makeText(context, tv2.getText(), Toast.LENGTH_SHORT).show();


        SharedPreferences sh = PreferenceManager.getDefaultSharedPreferences(context);
        String ip = sh.getString("ip", "");

        String url = "http://" + ip + ":5000/" + image[i];
//
//
        Picasso.with(context).load(url).into(im);

        return gridView;


    }

}
