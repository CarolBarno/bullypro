package com.example.carol.bullyalert.aob;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import com.example.carol.bullyalert.R;

import java.util.ArrayList;
import java.util.List;

public class Awareness extends AppCompatActivity
{
    private TextView text, text5w, texttypes, texteffects, textvictims, texthelp, textstand;
    private Animation up, down;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_awareness);

        Toolbar toolbar= findViewById(R.id.toolbar);
        toolbar.setTitle("Awareness");
        setSupportActionBar(toolbar);

        text = findViewById(R.id.text);
        TextView content = findViewById(R.id.content);
        text.setVisibility(View.GONE);

        up = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_up);
        down = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.slide_down);

        content.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                if(text.isShown())
                {
                    text.setVisibility(View.GONE);
                    text.startAnimation(up);
                }

                else
                {
                    text.setVisibility(View.VISIBLE);
                    text.startAnimation(down);
                }

            }
        });

        text5w = findViewById(R.id.text5w);
        TextView content5w = findViewById(R.id.content5w);
        text5w.setVisibility(View.GONE);

        content5w.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                if(text5w.isShown())
                {
                    text5w.setVisibility(View.GONE);
                    text5w.startAnimation(up);
                }

                else
                {
                    text5w.setVisibility(View.VISIBLE);
                    text5w.startAnimation(down);
                }

            }
        });

        texttypes = findViewById(R.id.texttypes);
        TextView contenttypes = findViewById(R.id.contenttypes);
        texttypes.setVisibility(View.GONE);

        contenttypes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                if(texttypes.isShown())
                {
                    texttypes.setVisibility(View.GONE);
                    texttypes.startAnimation(up);
                }

                else
                {
                    texttypes.setVisibility(View.VISIBLE);
                    texttypes.startAnimation(down);
                }

            }
        });

        texteffects = findViewById(R.id.texteffects);
        TextView contenteffects = findViewById(R.id.contenteffects);
        texteffects.setVisibility(View.GONE);

        contenteffects.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                if(texteffects.isShown())
                {
                    texteffects.setVisibility(View.GONE);
                    texteffects.startAnimation(up);
                }

                else
                {
                    texteffects.setVisibility(View.VISIBLE);
                    texteffects.startAnimation(down);
                }

            }
        });

        textvictims = findViewById(R.id.textvictims);
        TextView contentvictims = findViewById(R.id.contentvictims);
        textvictims.setVisibility(View.GONE);

        contentvictims.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                if(textvictims.isShown())
                {
                    textvictims.setVisibility(View.GONE);
                    textvictims.startAnimation(up);
                }

                else
                {
                    textvictims.setVisibility(View.VISIBLE);
                    textvictims.startAnimation(down);
                }

            }
        });

        texthelp = findViewById(R.id.texthelp);
        TextView contenthelp = findViewById(R.id.contenthelp);
        texthelp.setVisibility(View.GONE);

        contenthelp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                if(texthelp.isShown())
                {
                    texthelp.setVisibility(View.GONE);
                    texthelp.startAnimation(up);
                }

                else
                {
                    texthelp.setVisibility(View.VISIBLE);
                    texthelp.startAnimation(down);
                }

            }
        });

        textstand = findViewById(R.id.textstand);
        TextView contentstand = findViewById(R.id.contentstand);
        textstand.setVisibility(View.GONE);

        contentstand.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                if(textstand.isShown())
                {
                    textstand.setVisibility(View.GONE);
                    textstand.startAnimation(up);
                }

                else
                {
                    textstand.setVisibility(View.VISIBLE);
                    textstand.startAnimation(down);
                }

            }
        });


    }

}
