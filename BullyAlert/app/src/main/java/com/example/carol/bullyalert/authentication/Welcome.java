package com.example.carol.bullyalert.authentication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.carol.bullyalert.R;

public class Welcome extends AppCompatActivity
{
    Button signup, signin;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        signup = findViewById(R.id.signup);
        signin = findViewById(R.id.signin);

        signup.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(Welcome.this, Signup.class);
                startActivity(intent);
            }
        });

        signin.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                startActivity(new Intent(Welcome.this, Signin.class));

            }
        });

    }

}
