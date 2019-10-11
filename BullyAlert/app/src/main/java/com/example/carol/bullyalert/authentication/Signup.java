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
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.carol.bullyalert.R;
import com.example.carol.bullyalert.chat.ChatRoom;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Signup extends AppCompatActivity
{
    public EditText email, phonenumber, username, password;
    Button register;
    FirebaseAuth firebaseAuth;
    private ProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup_activity);

        firebaseAuth = FirebaseAuth.getInstance();
        email = (EditText) findViewById(R.id.email);
        phonenumber = (EditText) findViewById(R.id.phone);
        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
        register =(Button) findViewById(R.id.register);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);

        Toolbar toolbar= findViewById(R.id.toolbar);
        toolbar.setTitle("BullyPro");
        setSupportActionBar(toolbar);

        register.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                String emailId = email.getText().toString();
                String name = username.getText().toString();
                String phone = phonenumber.getText().toString();
                String pass = password.getText().toString();

                if (name.isEmpty())
            {
                username.setError("Provide your username");
                username.requestFocus();
            }
            else if (emailId.isEmpty())
                {
                    email.setError("Provide your email first");
                    email.requestFocus();
                }
                else if(phone.isEmpty())
                {
                    phonenumber.setError("Provide your phone number");
                    phonenumber.requestFocus();
                }
                else if(pass.isEmpty())
                {
                    password.setError("Set your password");
                    password.requestFocus();
                }
                else if(pass.length() < 6 )
                {
                    password.setError("Password cannot be less than 6 characters!");
                    password.requestFocus();
                }
                else if(!(emailId.isEmpty() && name.isEmpty() && phone.isEmpty() && pass.isEmpty()))
                {
                    progressBar.setVisibility(View.VISIBLE);

                    firebaseAuth.createUserWithEmailAndPassword(emailId, pass).addOnCompleteListener(Signup.this, new OnCompleteListener<AuthResult>()
                    {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task)
                        {
                            progressBar.setVisibility(View.GONE);

                            if(!task.isSuccessful())
                            {
                                Toast.makeText(Signup.this.getApplicationContext(), "Something went wrong: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            }
                            else
                            {
                                Toast.makeText(Signup.this, "Registration successful", Toast.LENGTH_LONG).show();
                                startActivity(new Intent(Signup.this, ChatRoom.class));

                            }

                        }
                    });
                }
                else
                {
                    Toast.makeText(Signup.this, "Error", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

}
