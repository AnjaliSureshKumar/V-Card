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

public class Custom_user_view_owner_products extends BaseAdapter {
    String[] image,product_id,product_name,price,quantity,type,made_id,brand_name;
    private Context context;

    public Custom_user_view_owner_products(Context appcontext,String[]image,String[]product_id,String[]product_name,String[]price,String[]quantity,String[]type,String[]made_id,String[]brand_name) {
        this.context = appcontext;
        this.image = image;
        this.product_id = product_id;
        this.product_name = product_name;
        this.price = price;
        this.quantity = quantity;
        this.type = type;
        this.made_id = made_id;
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
            gridView=inflator.inflate(R.layout.custom_user_view_owner_products,null);

        }
        else
        {
            gridView=(View)view;

        }
        TextView tv1=(TextView)gridView.findViewById(R.id.textView47);
        TextView tv2=(TextView)gridView.findViewById(R.id.textView48);
        TextView tv3=(TextView)gridView.findViewById(R.id.textView53);
        ImageView im=(ImageView) gridView.findViewById(R.id.imageView11);


        tv1.setTextColor(Color.BLACK);
        tv2.setTextColor(Color.BLACK);
        tv3.setTextColor(Color.BLACK);




        tv1.setText(product_name[i]);
        tv2.setText(price[i]);
        tv3.setText(brand_name[i]);

        Button btcart=(Button) gridView.findViewById(R.id.btcart);
        btcart.setTag(i);
        btcart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int pos= (int) v.getTag();
                SharedPreferences sh= PreferenceManager.getDefaultSharedPreferences(context);
                SharedPreferences.Editor ed = sh.edit();
                ed.putString("prid", product_id[pos]);
                ed.putString("qty", quantity[pos]);


                ed.commit();


                Intent i = new Intent(context.getApplicationContext(),User_add_to_cart.class);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(i);
            }
        });




        SharedPreferences sh= PreferenceManager.getDefaultSharedPreferences(context);
        String ip=sh.getString("ip","");

        String url="http://" + ip + ":5000"+image[i];


        Picasso.with(context).load(url). into(im);
        Log.d("iiiiiiiiii",url);
        return gridView;
    }
}
