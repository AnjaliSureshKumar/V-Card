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
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URISyntaxException;

public class view_more extends AppCompatActivity implements View.OnClickListener {
    ImageView img;
    TextView name;
    TextView price;
    TextView bname;
    Button btn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_more);
        name = (TextView) findViewById(R.id.textView31);
        price = (TextView) findViewById(R.id.textView34);
        bname = (TextView) findViewById(R.id.textView36);
        img = (ImageView) findViewById(R.id.imageView8);
        btn = (Button) findViewById(R.id.button33);
        btn.setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {


                }

    }
