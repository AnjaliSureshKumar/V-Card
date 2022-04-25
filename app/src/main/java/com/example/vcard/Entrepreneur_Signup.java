package com.example.vcard;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;

import android.preference.PreferenceManager;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

public class Entrepreneur_Signup extends AppCompatActivity implements View.OnClickListener {
    TextView Entrepreneur_signup;
    ImageView img;
    EditText name;
    EditText shop_name;
    EditText place;
    EditText post;
    EditText district;
    EditText pin;
    EditText location;
    EditText latitude;
    EditText longitude;
    EditText phone_number;
    EditText email;
    EditText pwd;
    EditText confirm_pwd;
    EditText upi;
    Button btn;
    String path, atype, fname, attach, attatch1;
    byte[] byteArray = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.entrepreneur_signup);
        name = (EditText) findViewById(R.id.editTextTextPersonName13);
        shop_name = (EditText) findViewById(R.id.editTextTextPersonName14);
        place = (EditText) findViewById(R.id.editTextTextPersonName15);
        post = (EditText) findViewById(R.id.editTextTextPersonName16);
        district = (EditText) findViewById(R.id.editTextTextPersonName17);
        pin = (EditText) findViewById(R.id.editTextTextPersonName20);
        location = (EditText) findViewById(R.id.editTextTextPersonName18);
        latitude = (EditText) findViewById(R.id.editTextTextPersonName69);
        longitude = (EditText) findViewById(R.id.editTextTextPersonName70);
        phone_number = (EditText) findViewById(R.id.editTextTextPersonName21);
        email = (EditText) findViewById(R.id.editTextTextPersonName22);
        pwd = (EditText) findViewById(R.id.editTextTextPersonName23);
        confirm_pwd = (EditText) findViewById(R.id.editTextTextPersonName24);
        upi = (EditText) findViewById(R.id.editTextTextPersonName25);
        img = (ImageView)  findViewById(R.id.imageView);
        btn = (Button) findViewById(R.id.button3);
        btn.setOnClickListener(this);
        img.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if(view==btn) {
            final String uname = name.getText().toString();
            final String sname = shop_name.getText().toString();
            final String Place = place.getText().toString();
            final String Post = post.getText().toString();
            final String District = district.getText().toString();
            final String Pin = pin.getText().toString();
            final String Location = location.getText().toString();
            final String Latitude = latitude.getText().toString();
            final String Longitude = longitude.getText().toString();
            final String Phone_number = phone_number.getText().toString();
            final String Email = email.getText().toString();
            final String Password = pwd.getText().toString();
            final String confirm_password = confirm_pwd.getText().toString();
            final String UPI = upi.getText().toString();


            if (uname.length() == 0) {
                name.setError("Field cannot be empty");
            } else if (sname.length() == 0) {
                shop_name.setError("Field cannot be empty");
            } else if (Place.length() == 0) {
                place.setError("Field cannot be empty");
            } else if (Post.length() == 0) {
                post.setError("Field cannot be empty");
            } else if (District.length() == 0) {
                district.setError("Field cannot be empty");
            } else if (Pin.length() == 0) {
                pin.setError("Field cannot be empty");
            } else if (Location.length() == 0) {
                location.setError("Field cannot be empty");
            } else if (Latitude.length() == 0) {
                latitude.setError("Field cannot be empty");
            } else if (Longitude.length() == 0) {
                longitude.setError("Field cannot be empty");
            } else if (Phone_number.length() == 0) {
                phone_number.setError("Field cannot be empty");
            } else if (Email.length() == 0) {
                email.setError("Field cannot be empty");
            } else if (Password.length() == 0) {
                pwd.setError("Field cannot be empty");
            } else if (confirm_password.length() == 0) {
                confirm_pwd.setError("Field cannot be empty");
            } else if (UPI.length() == 0) {
                upi.setError("Field cannot be empty");
            } else {
                SharedPreferences sh = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                String hu = sh.getString("ip", "");
                String url = "http://" + hu + ":5000/entrepreneur_signup";


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
                        params.put("image", attach);
                        params.put("name", uname);
                        params.put("shop_name", sname);
                        params.put("place", Place);
                        params.put("post", Post);
                        params.put("district", District);
                        params.put("pin", Pin);
                        params.put("location", Location);
                        params.put("latitude", Latitude);
                        params.put("longitude", Longitude);
                        params.put("phone_number", Phone_number);
                        params.put("email", Email);
                        params.put("password", Password);
                        params.put("upi", UPI);
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
        else{
            showfilechooser(1);
        }
    }

    void showfilechooser(int string) {
        // TODO Auto-generated method stub
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        //getting all types of files

        intent.setType("*/*");
        intent.addCategory(Intent.CATEGORY_OPENABLE);

        try {
            startActivityForResult(Intent.createChooser(intent, "Select a File to Upload"), string);
        } catch (android.content.ActivityNotFoundException ex) {
            // Potentially direct the user to the Market with a Dialog
            Toast.makeText(getApplicationContext(), "Please install a File Manager.", Toast.LENGTH_SHORT).show();

        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            if (requestCode == 1) {
                ////
                Uri uri = data.getData();

                try {
                    path = FileUtils.getPath(this, uri);

                    File fil = new File(path);
                    float fln = (float) (fil.length() / 1024);
                    atype = path.substring(path.lastIndexOf(".") + 1);


                    fname = path.substring(path.lastIndexOf("/") + 1);
                    //ed15.setText(fname);
                } catch (URISyntaxException e) {
                    e.printStackTrace();
                }

                try {

                    File imgFile = new File(path);

                    if (imgFile.exists()) {

                        Bitmap myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
                        img.setImageBitmap(myBitmap);

                    }


                    File file = new File(path);
                    byte[] b = new byte[8192];
                    Log.d("bytes read", "bytes read");

                    InputStream inputStream = new FileInputStream(file);
                    ByteArrayOutputStream bos = new ByteArrayOutputStream();

                    int bytesRead = 0;

                    while ((bytesRead = inputStream.read(b)) != -1) {
                        bos.write(b, 0, bytesRead);
                    }
                    byteArray = bos.toByteArray();

                    String str = Base64.encodeToString(byteArray, Base64.NO_WRAP);
                    attach = str;


                } catch (Exception e) {
                    Toast.makeText(this, "String :" + e.getMessage().toString(), Toast.LENGTH_LONG).show();
                }

                ///

            }
        }

}
}

