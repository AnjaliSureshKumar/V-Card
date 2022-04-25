package com.example.vcard;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class custom_view_manufacturer_product extends BaseAdapter {
    String[] image,product_name,price,quantity,brand_name;
    private Context context;


    public custom_view_manufacturer_product(Context appcontext,String[]image,String[]product_name,String[]price,String[]quantity,String[]brand_name) {
        this.context = appcontext;
        this.image = image;
        this.product_name = product_name;
        this.price = price;
        this.quantity = quantity;
        this.brand_name = brand_name;

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
    public View getView(int i, View view, ViewGroup viewGroup) {

        LayoutInflater inflator=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View gridView;
        if(view==null)
        {
            gridView=new View(context);
            //gridView=inflator.inflate(R.layout.customview, null);
            gridView=inflator.inflate(R.layout.custom_view_manufacturer_product,null);

        }
        else
        {
            gridView=(View)view;

        }
        TextView tv1=(TextView)gridView.findViewById(R.id.textView57);
        TextView tv2=(TextView)gridView.findViewById(R.id.textView58);
        TextView tv3=(TextView)gridView.findViewById(R.id.textView59);
        ImageView im=(ImageView) gridView.findViewById(R.id.imageView14);







        tv1.setTextColor(Color.BLACK);
        tv2.setTextColor(Color.BLACK);
        tv3.setTextColor(Color.BLACK);





        tv1.setText(product_name[i]);
        tv2.setText(price[i]);
        tv3.setText(brand_name[i]);




        SharedPreferences sh= PreferenceManager.getDefaultSharedPreferences(context);
        String ip=sh.getString("ip","");

        String url="http://" + ip + ":5000"+image[i];


        Picasso.with(context).load(url). into(im);

        return gridView;
    }
}


