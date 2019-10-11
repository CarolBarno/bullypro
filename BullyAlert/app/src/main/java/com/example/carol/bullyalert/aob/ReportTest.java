package com.example.carol.bullyalert.aob;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ListView;
import android.widget.TextView;

import com.example.carol.bullyalert.R;

public class ReportTest extends AppCompatActivity
{
    TextView text;
    public static final String USER_PREF = "USER_PREF";
    public static final String KEY_WORDS = "KEY_WORDS";
    SharedPreferences sp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.report_test);

        text = findViewById(R.id.text);
        sp = getSharedPreferences(USER_PREF, Context.MODE_PRIVATE);

        Toolbar toolbar= findViewById(R.id.toolbar);
        toolbar.setTitle("Report");
        setSupportActionBar(toolbar);


        StringBuilder str = new StringBuilder();
        if(sp.contains(KEY_WORDS))
        {
            text.setText(sp.getString(KEY_WORDS, ""));
        }
    }

    }
