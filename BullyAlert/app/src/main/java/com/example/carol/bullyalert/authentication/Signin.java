package com.example.carol.bullyalert.authentication;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.carol.bullyalert.R;
import com.example.carol.bullyalert.chat.ChatRoom;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Signin extends AppCompatActivity
{
    TextView register, reg;
    public EditText email, password;
    Button login;
    FirebaseAuth firebaseAuth;
    ProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signin_activity);

        firebaseAuth = FirebaseAuth.getInstance();
        register = (TextView) findViewById(R.id.register);
        reg = (TextView) findViewById(R.id.reg);
        email = (EditText) findViewById(R.id.email);
        password = (EditText) findViewById(R.id.password);
        login = (Button) findViewById(R.id.login);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);

        Toolbar toolbar= findViewById(R.id.toolbar);
        toolbar.setTitle("BullyPro");
        setSupportActionBar(toolbar);


        reg.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(Signin.this, Signup.class);
                startActivity(intent);


            }
        });

        login.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                String em = email.getText().toString();
                String pass = password.getText().toString();

                if(em.isEmpty())
                {
                    email.setError("Provide your email");
                    email.requestFocus();
                }
                else if(pass.isEmpty())
                {
                    password.setError("Enter your password");
                    password.requestFocus();
                }
                else if(!(em.isEmpty() && pass.isEmpty()))
                {
                    progressBar.setVisibility(View.VISIBLE);
                    firebaseAuth.signInWithEmailAndPassword(em, pass).addOnCompleteListener(Signin.this, new OnCompleteListener<AuthResult>()
                    {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task)
                        {
                            progressBar.setVisibility(View.GONE);
                            if(!task.isSuccessful())
                            {
                                Toast.makeText(Signin.this, "Something went wrong", Toast.LENGTH_SHORT).show();
                            }
                            else
                            {
                                Toast.makeText(Signin.this, "Authentication success", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(Signin.this, ChatRoom.class));

                            }

                        }
                    });
                }
                else
                {
                    Toast.makeText(Signin.this, "Error", Toast.LENGTH_SHORT).show();

                }

            }
        });
    }


    }

