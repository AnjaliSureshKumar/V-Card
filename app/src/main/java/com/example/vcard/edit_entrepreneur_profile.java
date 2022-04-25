package com.example.vcard;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URISyntaxException;

public class edit_entrepreneur_profile extends AppCompatActivity implements View.OnClickListener {
    ImageView img;
    EditText name;
    EditText shop_name;
    EditText place;
    EditText post;
    EditText district;
    EditText pin;
    EditText location;
    EditText phone_number;
    EditText email;
    EditText upi;
    Button btn;
    String path, atype, fname, attach, attatch1;
    byte[] byteArray = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_entrepreneur_profile);
        name = (EditText) findViewById(R.id.editTextTextPersonName51);
        shop_name = (EditText) findViewById(R.id.editTextTextPersonName52);
        place = (EditText) findViewById(R.id.editTextTextPersonName53);
        post = (EditText) findViewById(R.id.editTextTextPersonName54);
        district = (EditText) findViewById(R.id.editTextTextPersonName55);
        pin = (EditText) findViewById(R.id.editTextTextPersonName56);
        location = (EditText) findViewById(R.id.editTextTextPersonName57);
        phone_number = (EditText) findViewById(R.id.editTextTextPersonName58);
        email = (EditText) findViewById(R.id.editTextTextPersonName59);
        upi = (EditText) findViewById(R.id.editTextTextPersonName60);
        img = (ImageView) findViewById(R.id.imageView3);
        btn = (Button) findViewById(R.id.button3);
        btn.setOnClickListener(this);
        img.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view == btn) {
            final String uname = name.getText().toString();
            final String sname = shop_name.getText().toString();
            final String Place = place.getText().toString();
            final String Post = post.getText().toString();
            final String District = district.getText().toString();
            final String Pin = pin.getText().toString();
            final String Location = location.getText().toString();
            final String Phone_number = phone_number.getText().toString();
            final String Email = email.getText().toString();
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
            } else if (Phone_number.length() == 0) {
                phone_number.setError("Field cannot be empty");
            } else if (Email.length() == 0) {
                email.setError("Field cannot be empty");
            } else if (UPI.length() == 0) {
                upi.setError("Field cannot be empty");
            }
        } else {
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
                        //i1.setImageBitmap(myBitmap);

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

