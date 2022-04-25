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
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Manufacturer_Signup extends AppCompatActivity implements View.OnClickListener {
    TextView Manufacturer_signup;
    EditText name;
    EditText company_name;
    EditText place;
    EditText post;
    EditText district;
    EditText pin;
    EditText phone_number;
    EditText email;
    EditText pwd;
    EditText confirm_pwd;
    Button btn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.manufacturer_signup);
        name = (EditText) findViewById(R.id.editTextTextPersonName26);
        company_name = (EditText) findViewById(R.id.editTextTextPersonName27);
        place = (EditText) findViewById(R.id.editTextTextPersonName28);
        post = (EditText) findViewById(R.id.editTextTextPersonName29);
        district = (EditText) findViewById(R.id.editTextTextPersonName30);
        pin = (EditText) findViewById(R.id.editTextTextPersonName31);
        phone_number = (EditText) findViewById(R.id.editTextTextPersonName32);
        email = (EditText) findViewById(R.id.editTextTextPersonName33);
        pwd = (EditText) findViewById(R.id.editTextTextPersonName34);
        confirm_pwd = (EditText) findViewById(R.id.editTextTextPersonName35);
        btn = (Button) findViewById(R.id.button4);
        btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        final String uname = name.getText().toString();
        final String cname = company_name.getText().toString();
        final String Place = place.getText().toString();
        final String Post = post.getText().toString();
        final String District = district.getText().toString();
        final String Pin = pin.getText().toString();
        final String Phone_number = phone_number.getText().toString();
        final String Email = email.getText().toString();
        final String Password = pwd.getText().toString();
        final String confirm_password = confirm_pwd.getText().toString();
        if (uname.length()==0){
            name.setError("Field cannot be empty");
        }
        else if (cname.length()==0){
            company_name.setError("Field cannot be empty");
        }
        else if (Place.length()==0) {
            place.setError("Field cannot be empty");
        }
        else if (Post.length()==0) {
            post.setError("Field cannot be empty");
        }
        else if (District.length()==0) {
            district.setError("Field cannot be empty");
        }
        else if (Pin.length()==0) {
            pin.setError("Field cannot be empty");
        }
        else if (Phone_number.length()==0) {
            phone_number.setError("Field cannot be empty");
        }
        else if (Email.length()==0) {
            email.setError("Field cannot be empty");
        }
        else if (Password.length()==0) {
            pwd.setError("Field cannot be empty");
        }
        else if (confirm_password.length()==0) {
            confirm_pwd.setError("Field cannot be empty");
        }
        else {
            SharedPreferences sh = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
            String hu = sh.getString("ip", "");
            String url = "http://" + hu + ":5000/manufacturer_signup";


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
                                    Toast.makeText(getApplicationContext(), "Success", Toast.LENGTH_LONG).show();
                                    Intent i = new Intent(getApplicationContext(), Login.class);
                                    startActivity(i);


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
                    params.put("name", uname);
                    params.put("company_name", cname);
                    params.put("place",Place);
                    params.put("post", Post);
                    params.put("district",District);
                    params.put("pin", Pin);
                    params.put("phone_number",Phone_number);
                    params.put("email", Email);
                    params.put("password",Password);
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

