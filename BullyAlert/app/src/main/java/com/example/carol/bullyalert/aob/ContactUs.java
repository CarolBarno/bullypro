package com.example.carol.bullyalert.aob;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.example.carol.bullyalert.R;

public class ContactUs extends AppCompatActivity
{
    TextView text, text1, text2, text3, text4, text5;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contactus);


        Toolbar toolbar= findViewById(R.id.toolbar);
        toolbar.setTitle("Contact us");
        setSupportActionBar(toolbar);

        text = findViewById(R.id.text);
        text1 = findViewById(R.id.text1);
        text2 = findViewById(R.id.text2);
        text3 = findViewById(R.id.text3);
        text4 = findViewById(R.id.text4);
        text5 = findViewById(R.id.text5);
    }
}
