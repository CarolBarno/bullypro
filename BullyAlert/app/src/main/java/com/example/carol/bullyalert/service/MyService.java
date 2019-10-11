package com.example.carol.bullyalert.service;


import android.content.Context;
import android.content.res.AssetManager;
import android.os.Looper;
import android.util.Log;
import android.widget.Toast;

import com.example.carol.bullyalert.chat.ChatRoom;
import com.firebase.jobdispatcher.JobParameters;
import com.firebase.jobdispatcher.JobService;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Handler;

import opennlp.tools.lemmatizer.DictionaryLemmatizer;
import opennlp.tools.postag.POSModel;
import opennlp.tools.postag.POSTaggerME;
import opennlp.tools.tokenize.SimpleTokenizer;

public class MyService extends JobService
{

    private static final String TAG = MyService.class.getSimpleName();

    @Override
    public boolean onStartJob(final JobParameters job)
    {
        new Thread(new Runnable()
        {
            @Override
            public void run()
            {
                detection(job);

            }
        }).start();

        return true;
    }

    @Override
    public boolean onStopJob(JobParameters job)
    {
        return true;
    }

    public void detection(final JobParameters parameters)
    {
        try
        {
            ChatRoom chat = new ChatRoom();

            String sentense = chat.message_box_edittext.getText().toString();


            SimpleTokenizer simpleTokenizer = SimpleTokenizer.INSTANCE;
            String tokens[] = simpleTokenizer.tokenize(sentense);
            System.out.println("Tokens"+ Arrays.asList(tokens));


            InputStream posModelIn = new FileInputStream("file:///android_asset/en-pos-maxent.bin");


            POSModel posModel = new POSModel(posModelIn);

            POSTaggerME posTagger = new POSTaggerME(posModel);

            String tags[] = posTagger.tag(tokens);
            System.out.println("Tag"+ Arrays.asList(tags));



            InputStream dictLemmatizer = new FileInputStream("file:///android_asset/en-lemmatizer.dict");



            DictionaryLemmatizer lemmatizer = new DictionaryLemmatizer(dictLemmatizer);


            String[] lemmas = lemmatizer.lemmatize(tokens, tags);


            System.out.println("Lemmas"+ Arrays.asList(lemmas));
            List<String> wordWithZeros = new ArrayList<>();
            for (int i = 0; i < lemmas.length; i++) {
                if (!lemmas[i].equals("O")) {
                    wordWithZeros.add(lemmas[i]);
                }
            }

            System.out.println("Message: "+ wordWithZeros);


            File file = new File("file:///android_asset/swearWords.txt");
            BufferedReader br = new BufferedReader(new FileReader(file));

            List<String> words = new ArrayList<>();
            String line = "";
            while ((line = br.readLine()) != null) {
                words.add(line);
            }

            List<String> curseWords = new ArrayList<>();

            for (String msgWord : wordWithZeros) {
                if (words.contains(msgWord)) {
                    curseWords.add(msgWord);
                }
            }

            System.out.println("Found: "+ curseWords);

            Toast.makeText(MyService.this, "Found: " + curseWords, Toast.LENGTH_LONG).show();

            Thread.sleep(2000);

            Log.d(TAG, "CompleteJob");
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        } catch (IOException e)
        {
            e.printStackTrace();
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
        finally
        {
            jobFinished(parameters, true);
        }
    }

}
