/*
 * CMPUT 301
 * VERSION: 1.0
 * 2017-09-30
 * Copyright (c) 2017 VVKRAVCH CMPUT 301 University of Alberta - All Rights reserved. You may use, distribute or modify this code under terms and conditions of the Code of Student Behaviour at University odf Alberta.
 * You may find a copy of the license in this project. Otherwise please contact vvkravch@ualberta.ca
 */

package com.vladkravchenko.vvkravch_countbook;

import android.content.Context;
import android.content.Intent;
import android.provider.SyncStateContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;

/**
 * Created by Vlad Kravchnko on 9/29/2017.
 */
/**
 * Represents a Counter.
 * @author vvkravch
 * @version 1.0
 * @since 1.0
 *
 */

/**
 * Represents The main screen of the app
 * @oldCounterList Summary of all the counters ina ListView
 * @counterList All counters in ArrayList representation
 * @number: total number of existing counters at the moment
 */
public class CountBookActivity extends AppCompatActivity {
    public static final String EXTRA_MESSAGE = "com.vladkravchenko.vvkravch_countbook.MESSAGE";
    protected static final String FILENAME = "file.sav";
    protected ListView oldCounterList;
    protected ArrayList<Counter> counterList;
    protected ArrayAdapter<Counter> adapter;
    public static int number;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_count_book); //Set Content
        Button createCounterButton = (Button) findViewById(R.id.create); //Initialize buttons
        Button exit = (Button) findViewById(R.id.exit);
        oldCounterList = (ListView) findViewById(R.id.oldCounterList);// Initialize other visible structures
        TextView textView = (TextView) findViewById(R.id.textView);
        number = loadFromFile();
        String name = "TOTAL: "+String.valueOf(number); // summary of the number of counters

        //int number = oldCounterList.getAdapter().getCount();
        //String number2 = String.valueOf(number);
        textView.setText(name);



        exit.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {      //Exit button
                finish();
                System.exit(0);
            }
        });
        createCounterButton.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {                            // OnClick initiates new activity to create a new Counter
                Intent firstIntent = new Intent(CountBookActivity.this, SetCounter.class);

                startActivity(firstIntent);
                finish();
            }
        });
        //OnClick opens a specific Counter in a new SetCounter activity  for editing.
        oldCounterList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int clickedPosition, long id) {
                int clickedPosition1 = clickedPosition;
                Counter clickedCounter = (Counter) adapter.getItem(clickedPosition);
                //Taken from https://stackoverflow.com/questions/2736389/how-to-pass-an-object-from-one-activity-to-another-on-android
                //2017-09-30
                Intent editIntent = new Intent(CountBookActivity.this, EditCounter.class);
                editIntent.putExtra("Counter", clickedCounter);
                editIntent.putExtra("position", clickedPosition1 );



                startActivity(editIntent);
                finish();
            }

        });

    }
    @Override
    /**
     * On Start of the activity
     */
    protected void onStart() {
        // TODO Auto-generated method stub
        super.onStart();

        //Find out the number of Counters
        number = loadFromFile();
        //Taken from LonelyTwitter and Modified for use in CountBookActivity
        adapter = new ArrayAdapter<Counter>(this,
                R.layout.list_item, counterList);
        oldCounterList.setAdapter(adapter);
        number = oldCounterList.getAdapter().getCount();
    }
    //Taken from LonelyTwitter project from CMPUT 301.
    // Method modified to return a total number of counters as integer. Performs deserialization of the Object Counter
    //2017-09-30
    private int loadFromFile() {
        try {
            FileInputStream fis = openFileInput(FILENAME);
            BufferedReader in = new BufferedReader(new InputStreamReader(fis));

            Gson gson = new Gson();

            //Taken from https://stackoverflow.com/questions/12384064/gson-convert-from-json-to-a-typed-arraylistt
            // 2017-09-19
            Type listType = new TypeToken<ArrayList<Counter>>(){}.getType();
            counterList = gson.fromJson(in, listType);
            return (counterList.size());
        } catch (FileNotFoundException e) {    // Exception handler
            // TODO Auto-generated catch block
            counterList = new ArrayList<Counter>();
            return (counterList.size());

        } catch (IOException e) {
            // TODO Auto-generated catch block
            throw new RuntimeException();
        }

    }
    //Taken from LonelyTwitter project from CMPUT 301. Modified for the Counter. Saves a serialized object Counter in File
    //2017-09-30
    protected void saveInFile() {
        try {
            FileOutputStream fos = openFileOutput(FILENAME,
                    Context.MODE_PRIVATE);

            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(fos));

            Gson gson = new Gson();
            gson.toJson(counterList, out);
            out.flush();

            fos.close();
        } catch (FileNotFoundException e) { //Exception handler
            // TODO Auto-generated catch block
            throw new RuntimeException();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            throw new RuntimeException();
        }
    }

    /**
     * Opens fileWriter and writes an empty string to file
     */
    protected void deleteInFile(){
        try {
            FileWriter clearFile = new FileWriter(FILENAME);
            clearFile.write("");
            clearFile.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

