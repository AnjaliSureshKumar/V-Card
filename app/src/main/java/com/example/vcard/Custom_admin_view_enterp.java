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

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Custom_admin_view_enterp extends BaseAdapter {
    String[]login_id,shop_name,phone_number,email,latitude,longitude;
    private Context context;
    public Custom_admin_view_enterp(Context appcontext, String[]login_id,String[]shop_name,String[]phone_number,String[]email,String[]latitude,String[]longitude) {
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

        LayoutInflater inflator = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View gridView;
        if (view == null) {
            gridView = new View(context);
            //gridView=inflator.inflate(R.layout.customview, null);
            gridView = inflator.inflate(R.layout.custom_admin_view_enterp, null);

        } else {
            gridView = (View) view;

        }
        TextView shopname = (TextView) gridView.findViewById(R.id.tvshopname);
        TextView temail = (TextView) gridView.findViewById(R.id.tvemail);
        TextView tvphone = (TextView) gridView.findViewById(R.id.tvphone);
        Button btblock = (Button) gridView.findViewById(R.id.bttblock);

        btblock.setTag(i);
        btblock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int pos= (int) v.getTag();
                SharedPreferences sh=PreferenceManager.getDefaultSharedPreferences(context);
                String hu = sh.getString("ip", "");
                String url = "http://" + hu + ":5000/admin_block_manufact";

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

                                        Toast.makeText(context, "deleted", Toast.LENGTH_SHORT).show();
                                        Intent ij = new Intent(context, Admin_view_enterP.class);
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

                        params.put("lid", login_id[pos]);


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


        shopname.setTextColor(Color.BLACK);
        temail.setTextColor(Color.BLACK);
        tvphone.setTextColor(Color.BLACK);

        shopname.setText(shop_name[i]);
        temail.setText(email[i]);
        tvphone.setText(phone_number[i]);

        return gridView;
    }
}
