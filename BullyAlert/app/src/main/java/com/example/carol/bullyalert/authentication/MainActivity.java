package com.example.carol.bullyalert.authentication;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.carol.bullyalert.R;

public class MainActivity extends AppCompatActivity
{
    TextView name;
    ImageView bully;

    private Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name = findViewById(R.id.name);
        bully = findViewById(R.id.bully);

        Toolbar toolbar= findViewById(R.id.toolbar);
        toolbar.setTitle("BullyPro");
        setSupportActionBar(toolbar);

        handler.postDelayed(new Runnable()
        {
            @Override
            public void run()
            {
                try
                {
                    Intent intent = new Intent(getApplicationContext(), Signin.class);
                    startActivity(intent);

                    finish();
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }

            }
        }, 2000);
    }

    @Override
    public void onDestroy()
    {
        super.onDestroy();

        handler.removeCallbacksAndMessages(null);
    }
}
