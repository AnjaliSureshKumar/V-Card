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

public class custom_view_product_man extends BaseAdapter implements View.OnClickListener {
    String[] image,product_id,product_name,price,quantity,brand_name;
    private Context context;


    public custom_view_product_man(Context appcontext,String[]image,String[]product_id,String[]product_name,String[]price,String[]quantity,String[]brand_name) {
        this.context = appcontext;
        this.image = image;
        this.product_id = product_id;
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
            gridView=inflator.inflate(R.layout.custom_view_product_man,null);

        }
        else
        {
            gridView=(View)view;

        }
        TextView tv1=(TextView)gridView.findViewById(R.id.textView55);
        TextView tv2=(TextView)gridView.findViewById(R.id.textView56);
        TextView tv3=(TextView)gridView.findViewById(R.id.textView61);
        TextView tv4=(TextView)gridView.findViewById(R.id.textView62);
        ImageView im=(ImageView) gridView.findViewById(R.id.imageView15);
        Button order = (Button) gridView.findViewById(R.id.button37);
        order.setTag(i);
        order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                SharedPreferences sh= PreferenceManager.getDefaultSharedPreferences(context.getApplicationContext());
                SharedPreferences.Editor ed = sh.edit();
                ed.putString("pd", product_id[i]);
                ed.putString("totalstock",quantity[i]);

                ed.commit();
                Toast.makeText(context, "manufature", Toast.LENGTH_SHORT).show();

                Intent i = new Intent(context.getApplicationContext(),ent_cart_product.class);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(i);

            }
        });







        tv1.setTextColor(Color.BLACK);
        tv2.setTextColor(Color.BLACK);
        tv3.setTextColor(Color.BLACK);
        tv4.setTextColor(Color.BLACK);





        tv1.setText(product_name[i]);
        tv2.setText("Price    :"+price[i]);
        tv3.setText("Quantity :"+quantity[i]);
        tv4.setText(brand_name[i]);





        SharedPreferences sh= PreferenceManager.getDefaultSharedPreferences(context);
        String ip=sh.getString("ip","");

        String url="http://" + ip + ":5000"+image[i];


        Picasso.with(context).load(url). into(im);

        return gridView;
    }


    @Override
    public void onClick(View view) {

    }
}





