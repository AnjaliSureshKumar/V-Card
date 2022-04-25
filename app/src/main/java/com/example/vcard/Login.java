package com.example.vcard;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import com.android.volley.DefaultRetryPolicy;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.android.volley.Request;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Login extends AppCompatActivity implements View.OnClickListener {
    TextView login;
    TextView signup;
    EditText uname;
    EditText pwd;
    Button btn;

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        startActivity(new Intent(getApplicationContext(),ip_connect.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        login = (TextView) findViewById(R.id.manufname);
        signup = (TextView) findViewById(R.id.textView);
        uname = (EditText) findViewById(R.id.editTextTextPersonName);
        pwd = (EditText) findViewById(R.id.editTextTextPersonName2);
        btn = (Button) findViewById(R.id.button);
        btn.setOnClickListener(this);
        signup.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if(view==signup)
        {

            Intent i = new Intent(getApplicationContext(),signup.class);
            startActivity(i);
        }
        else {
            final String username = uname.getText().toString();
            final String password = pwd.getText().toString();
            if (username.length() == 0) {
                uname.setError("Field cannot be empty");
            } else if (password.length() == 0) {
                pwd.setError("Field cannot be empty");
            } else {
                SharedPreferences sh = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                String hu = sh.getString("ip", "");
                String url = "http://" + hu + ":5000/login";


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
String lid=jsonObj.getString("lid");
                                        SharedPreferences sh= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                                        SharedPreferences.Editor ed = sh.edit();
                                        ed.putString("lid", lid);
                                        ed.commit();





                                        if (jsonObj.getString("type").equalsIgnoreCase("manufacturer")) {
                                            Intent i = new Intent(getApplicationContext(), manufacturer_homepage.class);
                                            startActivity(i);


                                        }
                                        if (jsonObj.getString("type").equalsIgnoreCase("entrepreneur")) {
                                            Intent i = new Intent(getApplicationContext(), entrepreneur_homepage.class);
                                            startActivity(i);


                                        }
                                        if (jsonObj.getString("type").equalsIgnoreCase("customer")) {

                                            Intent ser = new Intent(getApplicationContext(), LocationService.class);
                                            startService(ser);

                                            Intent i = new Intent(getApplicationContext(), UserHome.class);
                                            startActivity(i);


                                        }
                                        if (jsonObj.getString("type").equalsIgnoreCase("admin")) {
                                            Intent i = new Intent(getApplicationContext(), AdminHome.class);
                                            startActivity(i);


                                        }

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
                        params.put("username", username);
                        params.put("password",password);

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
    }
}
