package com.example.vcard;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Admin_view_enterP extends AppCompatActivity implements TextWatcher {
    String[]ent_login_id,shop_name,phone_number,email,latitude,longitude;
    ListView ls;
    EditText ed2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_view_enter_p);


        ls = (ListView) findViewById(R.id.ls);
        ed2=(EditText)findViewById(R.id.edsearch2);
        ed2.addTextChangedListener(this);


        SharedPreferences sh = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        String hu = sh.getString("ip", "");
        String url = "http://" + hu + ":5000/user_view_owners";


        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        StringRequest postRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //  Toast.makeText(getApplicationContext(), response, Toast.LENGTH_LONG).show();

                        // response
                        try {
                            JSONObject jsonObj = new JSONObject(response);
                            if (jsonObj.getString("status").equalsIgnoreCase("ok")) {
//`ent_login_id`,`shop_name`,`phone_number`,`email`,`latitude`,`longitude`

                                JSONArray js= jsonObj.getJSONArray("users");
                                ent_login_id=new String[js.length()];
                                shop_name=new String[js.length()];
                                phone_number=new String[js.length()];
                                email=new String[js.length()];
                                latitude=new String[js.length()];
                                longitude=new String[js.length()];


                                for(int i=0;i<js.length();i++)
                                {
                                    JSONObject u=js.getJSONObject(i);
                                    ent_login_id[i]=u.getString("ent_login_id");
                                    shop_name[i]=u.getString("shop_name");
                                    phone_number[i]=u.getString("phone_number");
                                    email[i]=u.getString("email");
                                    latitude[i]=u.getString("latitude");
                                    longitude[i]=u.getString("longitude");


                                }
//                                login_id,shop_name,phone_number,email,latitude,longitude;

                                // ArrayAdapter<String> adpt=new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_list_item_1,name);
                                ls.setAdapter(new Custom_admin_view_enterp(getApplicationContext(),ent_login_id,shop_name,phone_number,email,latitude,longitude));
                                // l1.setAdapter(new Custom(getApplicationContext(),gamecode,name,type,discription,image,status));


                            }


                            // }
                            else {
                                Toast.makeText(getApplicationContext(), "Not found", Toast.LENGTH_LONG).show();
                            }

                        } catch (Exception e) {
                            Toast.makeText(getApplicationContext(), "Error" + e.getMessage().toString(), Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // error
                        Toast.makeText(getApplicationContext(), "eeeee" + error.toString(), Toast.LENGTH_SHORT).show();
                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams() {
                SharedPreferences sh = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                Map<String, String> params = new HashMap<String, String>();

                String id = sh.getString("uid", "");
                params.put("lid",sh.getString("lid",""));



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

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
        SharedPreferences sh = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        String hu = sh.getString("ip", "");
        String url = "http://" + hu + ":5000/admin_view_owners_search";


        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        StringRequest postRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //  Toast.makeText(getApplicationContext(), response, Toast.LENGTH_LONG).show();

                        // response
                        try {
                            JSONObject jsonObj = new JSONObject(response);
                            if (jsonObj.getString("status").equalsIgnoreCase("ok")) {
//`ent_login_id`,`shop_name`,`phone_number`,`email`,`latitude`,`longitude`

                                JSONArray js= jsonObj.getJSONArray("users");
                                ent_login_id=new String[js.length()];
                                shop_name=new String[js.length()];
                                phone_number=new String[js.length()];
                                email=new String[js.length()];
                                latitude=new String[js.length()];
                                longitude=new String[js.length()];

//`email`,`latitude`,`longitude`
                                for(int i=0;i<js.length();i++)
                                {
                                    JSONObject u=js.getJSONObject(i);
                                    ent_login_id[i]=u.getString("ent_login_id");
                                    shop_name[i]=u.getString("shop_name");
                                    phone_number[i]=u.getString("phone_number");
                                    email[i]=u.getString("email");
                                    latitude[i]=u.getString("latitude");
                                    longitude[i]=u.getString("longitude");

                                }
//                                login_id,shop_name,phone_number,email,latitude,longitude;

                                // ArrayAdapter<String> adpt=new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_list_item_1,name);
                                ls.setAdapter(new Custom_admin_view_enterp(getApplicationContext(),ent_login_id,shop_name,phone_number,email,latitude,longitude));
                                // l1.setAdapter(new Custom(getApplicationContext(),gamecode,name,type,discription,image,status));


                            }


                            // }
                            else {
                                Toast.makeText(getApplicationContext(), "Not found", Toast.LENGTH_LONG).show();
                            }

                        } catch (Exception e) {
                            Toast.makeText(getApplicationContext(), "Error" + e.getMessage().toString(), Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // error
                        Toast.makeText(getApplicationContext(), "eeeee" + error.toString(), Toast.LENGTH_SHORT).show();
                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams() {
                SharedPreferences sh = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                Map<String, String> params = new HashMap<String, String>();

                String id = sh.getString("uid", "");
                params.put("name",ed2.getText().toString());



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
}