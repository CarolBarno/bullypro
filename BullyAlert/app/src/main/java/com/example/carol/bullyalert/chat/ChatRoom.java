package com.example.carol.bullyalert.chat;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.format.DateFormat;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.carol.bullyalert.R;
import com.example.carol.bullyalert.aob.Awareness;
import com.example.carol.bullyalert.aob.ReportTest;
import com.example.carol.bullyalert.aob.Settings;
import com.example.carol.bullyalert.authentication.Signin;
import com.example.carol.bullyalert.service.MyService;
import com.firebase.jobdispatcher.Constraint;
import com.firebase.jobdispatcher.FirebaseJobDispatcher;
import com.firebase.jobdispatcher.GooglePlayDriver;
import com.firebase.jobdispatcher.Job;
import com.firebase.jobdispatcher.Lifetime;
import com.firebase.jobdispatcher.RetryStrategy;
import com.firebase.jobdispatcher.Trigger;
import com.firebase.ui.database.FirebaseListAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;


public class ChatRoom extends AppCompatActivity
{
    ListView listOfMessage;
    public EditText message_box_edittext;
    ImageView chatmessengersent;
    private FirebaseAuth mAuth;
    public FirebaseListAdapter<FirebaseMessage> adapter;
    DatabaseReference rootref, demoref;

    public static final String USER_PREF = "USER_PREF";
    public static final String KEY_WORDS = "KEY_WORDS";
    SharedPreferences sp;



    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chatroom);

        //scheduleJob(this);

        Toolbar toolbar= findViewById(R.id.toolbar);
        toolbar.setTitle("ChatRoom");
        setSupportActionBar(toolbar);
        toolbar.inflateMenu(R.menu.main_menu);

        sp = getSharedPreferences(USER_PREF, Context.MODE_PRIVATE);

        message_box_edittext = findViewById(R.id.message_box_edittext);
        chatmessengersent = findViewById(R.id.chat_messenger_sent_imageview);
        listOfMessage = findViewById(R.id.list_of_messages);

        rootref = FirebaseDatabase.getInstance().getReference();
        demoref = rootref.child("chat");



        chatmessengersent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                //startService(new Intent(ChatRoom.this, MyService.class));

                String message = message_box_edittext.getText().toString();

                if(message.isEmpty())
                {
                    message_box_edittext.setError("Message can't be empty");
                    message_box_edittext.requestFocus();
                }
                else if(!(message.isEmpty())) {
                    FirebaseDatabase.getInstance().getReference().push()
                            .setValue(new FirebaseMessage(message,
                                    FirebaseAuth.getInstance().getCurrentUser().getEmail()));


                    message_box_edittext.setText("");

                }


                String line = "";
                List<String> wordList = new ArrayList<>();

                try {


                    InputStream inputStream = getAssets().open("swearWords.txt");
                    InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                    BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

                    while ((line = bufferedReader.readLine()) != null) {
                        wordList.add(line);
                    }

                    List<String> curseWords = new ArrayList<>();

                    String msgWord = message;


                        if (wordList.contains(msgWord)) {
                            curseWords.add(msgWord);
                            Toast.makeText(ChatRoom.this, "Found: " + curseWords, Toast.LENGTH_LONG).show();
                        }
                        else
                        if(msgWord.length() >= 2)
                        {
                            String [] msg = msgWord.split(" ");
                            for(String list : msg)
                            {
                                if(wordList.contains(list))
                                {
                                    curseWords.add(list);
                                    Toast.makeText(ChatRoom.this, "Found: " +curseWords, Toast.LENGTH_LONG).show();
                                }
                            }

                        }

                        for(String found : curseWords)
                        {
                            SharedPreferences.Editor editor = sp.edit();
                            editor.putString(KEY_WORDS, found);
                            editor.commit();

                           // Toast.makeText(ChatRoom.this, "Saved", Toast.LENGTH_SHORT).show();
                        }



                }
                catch (IOException e )
                {
                    e.printStackTrace();
                }


            }
        });

        displayChatMessage();



    }



    private void displayChatMessage()
    {
        adapter = new FirebaseListAdapter<FirebaseMessage>(this, FirebaseMessage.class, R.layout.message_layout, FirebaseDatabase.getInstance().getReference())
        {
            @Override
            protected void populateView(View v, FirebaseMessage model, int position)
            {
                TextView messageText = v.findViewById(R.id.chat_messenger_textview);
                TextView messageUser = v.findViewById(R.id.chat_user_textview);
                TextView messageTime = v.findViewById(R.id.chat_timestamp_textview);
                ImageView profile1 = v.findViewById(R.id.user_icon);
                ImageView profile2 = v.findViewById(R.id.user_icon2);

                if(FirebaseAuth.getInstance().getCurrentUser().getEmail().equals(model.getMessageUser()))
                {
                    profile1.setVisibility(View.INVISIBLE);
                    profile2.setVisibility(View.VISIBLE);
                    messageText.setBackgroundResource(R.drawable.messenger_border_green);
                    messageText.setText(model.getMessageText());
                    messageTime.setText(DateFormat.format("dd-MM (HH:mm)", model.getMessageTime()));
                    messageUser.setText(model.getMessageUser());
                }
                else
                {
                    profile1.setVisibility(View.VISIBLE);
                    profile2.setVisibility(View.INVISIBLE);
                    messageText.setBackgroundResource(R.drawable.message_border_ash);
                    messageText.setText(model.getMessageText());
                    messageUser.setText(model.getMessageUser());
                    messageTime.setText(DateFormat.format("dd-MM (HH:mm)", model.getMessageTime()));
                }

            }
        };

        listOfMessage.setAdapter(adapter);
        listOfMessage.setTranscriptMode(ListView.TRANSCRIPT_MODE_ALWAYS_SCROLL);
        listOfMessage.setStackFromBottom(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch(item.getItemId())
        {
            case R.id.report:
                startActivity(new Intent(ChatRoom.this, ReportTest.class));

                return true;

            case R.id.awareness:
                startActivity(new Intent(ChatRoom.this, Awareness.class));

                return true;

            case R.id.settings:
                startActivity(new Intent(ChatRoom.this, Settings.class));

                return true;

            case R.id.logout:
                FirebaseAuth.getInstance().signOut();
                Toast.makeText(ChatRoom.this, "Signed out", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(ChatRoom.this, Signin.class));
                finish();
                return true;

                default:
                    return super.onOptionsItemSelected(item);
        }
    }


    public static void scheduleJob(Context context)
    {
        FirebaseJobDispatcher dispatcher = new FirebaseJobDispatcher(new GooglePlayDriver(context));
        Job job = createJob(dispatcher);
        dispatcher.mustSchedule(job);
    }

    public static Job createJob(FirebaseJobDispatcher dispatcher)
    {
        Job job = dispatcher.newJobBuilder().setLifetime(Lifetime.FOREVER).setService(MyService.class).setTag("UniqueTagForYourJob")
                .setReplaceCurrent(false).setRecurring(true).setTrigger(Trigger.executionWindow(15, 30))
                .setRetryStrategy(RetryStrategy.DEFAULT_LINEAR).setConstraints(Constraint.ON_ANY_NETWORK, Constraint.ON_UNMETERED_NETWORK)
                .build();

        return job;
    }

    public static Job updateJob(FirebaseJobDispatcher dispatcher)
    {
        Job newJob = dispatcher.newJobBuilder().setReplaceCurrent(true).setService(MyService.class).setTag("UniqueTagForYourJob")
                .build();

        return newJob;
    }

    public void cancelJob(Context context)
    {
        FirebaseJobDispatcher dispatcher = new FirebaseJobDispatcher(new GooglePlayDriver(context));
        dispatcher.cancelAll();
        dispatcher.cancel("UniqueTagForYourJob");
    }

}

