package com.patarapolw.randomsentence;

import android.app.Activity;
import android.content.Context;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class POSTagger extends Activity {
    private Map<String, String> taggedDictionary = new HashMap<>();

    public POSTagger() {}

    public POSTagger(Context context) {
        try {
            InputStream is = context.getAssets().open("randomsentence/dictionary-tagged.txt");
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));

            String line;
            while((line = reader.readLine()) != null){
                String[] entry = line.split("\t");
                taggedDictionary.put(entry[0], entry[1]);
            }
            reader.close();
            is.close();
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public String[] tag (String[] keywordList) {
        ArrayList<String> taggedArrayList = new ArrayList<>();

        for(String keyword: keywordList){
            boolean added = false;

            String POS = taggedDictionary.get(keyword.toLowerCase());
            if(POS == null){
                POS = "XX";
            }
            taggedArrayList.add(POS);
        }

        return taggedArrayList.toArray(new String[0]);
    }
}
