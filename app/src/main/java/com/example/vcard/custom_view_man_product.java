package com.example.vcard;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
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

public class custom_view_man_product extends BaseAdapter {
    String[] image,product_id,product_name,price,quantity,type,made_id,brand_name;
    private Context context;

    public custom_view_man_product(Context appcontext,String[]image,String[]product_id,String[]product_name,String[]price,String[]quantity,String[]type,String[]made_id,String[]brand_name) {
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
                gridView=inflator.inflate(R.layout.custom_view_man_product,null);

            }
            else
            {
                gridView=(View)view;

            }
            TextView tv1=(TextView)gridView.findViewById(R.id.textView47);
            TextView tv2=(TextView)gridView.findViewById(R.id.textView48);
        TextView tv3=(TextView)gridView.findViewById(R.id.textView53);
            ImageView im=(ImageView) gridView.findViewById(R.id.imageView11);
            ImageView imdelete=(ImageView) gridView.findViewById(R.id.imgdelete);
            imdelete.setTag(i);
            imdelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos= (int) v.getTag();
SharedPreferences sh=PreferenceManager.getDefaultSharedPreferences(context);
                    String hu = sh.getString("ip", "");
                    String url = "http://" + hu + ":5000/delete_product";

                    RequestQueue requestQueue = Volley.newRequestQueue(context);
                    StringRequest postRequest = new StringRequest(Request.Method.POST, url,
                            new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {
                                    //  Toast.makeText(getApplicationContext(), response, Toast.LENGTH_LONG).show();

                                    // response
                                    try {
                                        JSONObject jsonObj = new JSONObject(response);
                                        if (jsonObj.getString("status").equalsIgnoreCase("ok")) {

                                            Toast.makeText(context, "addedd", Toast.LENGTH_SHORT).show();
                                            Intent ij = new Intent(context, view_man_product.class);
                                            ij.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                            context.startActivity(ij);

                                        }


                                        // }
                                        else {
                                            Toast.makeText(context, "error", Toast.LENGTH_LONG).show();
                                        }

                                    } catch (Exception e) {
                                        Toast.makeText(context, "Error" + e.getMessage().toString(), Toast.LENGTH_SHORT).show();
                                    }
                                }
                            },
                            new Response.ErrorListener() {
                                @Override
                                public void onErrorResponse(VolleyError error) {
                                    // error
                                    Toast.makeText(context, "eeeee" + error.toString(), Toast.LENGTH_SHORT).show();
                                }
                            }
                    ) {
                        @Override
                        protected Map<String, String> getParams() {
                            SharedPreferences sh = PreferenceManager.getDefaultSharedPreferences(context);
                            Map<String, String> params = new HashMap<String, String>();

                            params.put("pid", product_id[pos]);


                            return params;
                        }
                    };

                    int MY_SOCKET_TIMEOUT_MS = 100000;

                    postRequest.setRetryPolicy(new DefaultRetryPolicy(
                            MY_SOCKET_TIMEOUT_MS,
                            DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                            DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
                    requestQueue.add(postRequest);


                }
            });


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
Log.d("iiiiiiiiii",url);
            return gridView;
        }
    }




