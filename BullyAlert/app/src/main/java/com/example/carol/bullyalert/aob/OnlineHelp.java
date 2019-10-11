package com.example.carol.bullyalert.aob;

import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.TextView;

import com.example.carol.bullyalert.R;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class OnlineHelp extends AppCompatActivity
{
    TextView terms, contactus, help;

     @Override
    protected void onCreate(Bundle savedInstanceState)
     {
         super.onCreate(savedInstanceState);
         setContentView(R.layout.activity_help);

         Toolbar toolbar= findViewById(R.id.toolbar);
         toolbar.setTitle("Help");
         setSupportActionBar(toolbar);

         terms = findViewById(R.id.terms);
         contactus = findViewById(R.id.contactus);
         help = findViewById(R.id.help);

         help.setOnClickListener(new View.OnClickListener()
         {
             @Override
             public void onClick(View view)
             {
                 startActivity(new Intent(OnlineHelp.this, Help.class));

             }
         });

         contactus.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 startActivity(new Intent(OnlineHelp.this, ContactUs.class));

             }
         });

         terms.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 startActivity(new Intent(OnlineHelp.this, Terms.class));

             }
         });



     }
}
