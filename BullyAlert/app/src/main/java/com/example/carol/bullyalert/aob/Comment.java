package com.example.carol.bullyalert.aob;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.carol.bullyalert.R;
import com.example.carol.bullyalert.model.Comments;
import com.firebase.client.Firebase;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Comment extends AppCompatActivity
{
    TextView yourcomment;
    EditText comment;
    Button send;

    public static final String Firebase_Server_URL = "https://cyber-123.firebaseio.com/";
    String text;
    Firebase firebase;
    DatabaseReference databaseReference;
    public static final String Database_Path = "Comments_Data";

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment);

        Toolbar toolbar= findViewById(R.id.toolbar);
        toolbar.setTitle("Comments");
        setSupportActionBar(toolbar);

        yourcomment = findViewById(R.id.yourcomment);
        comment = findViewById(R.id.editcomment);
        send = findViewById(R.id.send);

        Firebase.setAndroidContext(Comment.this);
        firebase = new Firebase(Firebase_Server_URL);
        databaseReference = FirebaseDatabase.getInstance().getReference(Database_Path);

        send.setOnClickListener(view -> {
            Comments comments = new Comments();

            text = comment.getText().toString();

            if(text.isEmpty())
            {
                comment.setError("Can't be empty");
                comment.requestFocus();
            }

            else
            {
                comments.setInput(text);
                String userId = databaseReference.push().getKey();
                databaseReference.child(userId).setValue(comments);

                Toast.makeText(Comment.this, "Comment sent successfully", Toast.LENGTH_LONG).show();
                comment.setText("");
            }

        });
    }
}
