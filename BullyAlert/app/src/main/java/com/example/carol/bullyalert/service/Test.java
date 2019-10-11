package com.example.carol.bullyalert.service;

import android.content.Context;
import android.content.res.AssetManager;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import opennlp.tools.lemmatizer.DictionaryLemmatizer;
import opennlp.tools.postag.POSModel;
import opennlp.tools.postag.POSTaggerME;
import opennlp.tools.tokenize.SimpleTokenizer;

public class Test
{

    public static void main(String[] args)
    {

        try {
            String sentense = "Go kill yourself";


            SimpleTokenizer simpleTokenizer = SimpleTokenizer.INSTANCE;
            String tokens[] = simpleTokenizer.tokenize(sentense);
            System.out.println("Tokens"+ Arrays.asList(tokens));

            InputStream posModelIn = new FileInputStream("file:///android_/asset/en-pos-maxent.bin");

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

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
