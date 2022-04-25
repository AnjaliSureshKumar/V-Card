package com.example.vcard;

import static java.lang.Integer.parseInt;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

public class User_add_to_cart extends AppCompatActivity implements View.OnClickListener {
EditText ed1;
Button b1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_add_to_cart);
        ed1=(EditText) findViewById(R.id.editTextTextPersonName84);
        b1=(Button) findViewById(R.id.bttcart);
        b1.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        String qty = ed1.getText().toString();
        SharedPreferences sh = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        String hu = sh.getString("ip", "");
        String totalstock = sh.getString("qty", "");
        Toast.makeText(this, "++++"+qty, Toast.LENGTH_SHORT).show();
        Toast.makeText(this, "*****"+totalstock, Toast.LENGTH_SHORT).show();
        int aa = parseInt(totalstock);
        int bb = parseInt(qty);
        if (bb > aa) {
            Toast.makeText(this, "Please Provide a value", Toast.LENGTH_SHORT).show();

        } else {

            String url = "http://" + hu + ":5000/user_add_to_cart";


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

                                    Toast.makeText(User_add_to_cart.this, "Order taken", Toast.LENGTH_SHORT).show();

                                    Intent i = new Intent(getApplicationContext(), User_view_owner_product.class);
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
                    params.put("pid", sh.getString("pd", ""));
                    params.put("ulid", sh.getString("lid", ""));
                    params.put("qty", ed1.getText().toString());
                    params.put("owlid", sh.getString("ownerlid", ""));


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