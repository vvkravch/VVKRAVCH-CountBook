/*
 * CMPUT 301
 * VERSION: 1.0
 * 2017-09-30
 * Copyright (c) 2017 VVKRAVCH CMPUT 301 University of Alberta - All Rights reserved. You may use, distribute or modify this code under terms and conditions of the Code of Student Behaviour at University odf Alberta.
 * You may find a copy of the license in this project. Otherwise please contact vvkravch@ualberta.ca
 */

package com.vladkravchenko.vvkravch_countbook;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Creates an activity where the counter can be modified reset or deleted.
 */

public class EditCounter extends CountBookActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_counter);
        //Taken from https://stackoverflow.com/questions/2736389/how-to-pass-an-object-from-one-activity-to-another-on-android
        //2017-09-30
        Intent editIntent = getIntent();
        final Counter clickedCounter = (Counter)editIntent.getSerializableExtra("Counter");
        final int clickedPosition1 = (int) editIntent.getSerializableExtra("position");
        TextView textView = (TextView) findViewById(R.id.textView2);
        //Initialize all the buttons and textboxes from layout
        textView.setText(clickedCounter.toString2());
        Button buttonSave = (Button) findViewById(R.id.buttonSave);
        Button buttonDelete = (Button) findViewById(R.id.buttonDelete);
        Button submitName = (Button) findViewById(R.id.buttonSubmitName);
        Button submitCount = (Button) findViewById(R.id.buttonCount);
        Button submitComment = (Button) findViewById(R.id.buttonComment);
        Button minus = (Button) findViewById(R.id.minusbutton);
        Button plus = (Button) findViewById(R.id.plusbutton);
        Button reset = (Button) findViewById(R.id.buttonReset);

        submitName.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                EditText editText = (EditText) findViewById(R.id.editTextCountersName);
                counterList.get(clickedPosition1).setName(editText.getText().toString());
                //Handle empty input
                if (editText.getText().toString().matches("")) {
                    Toast.makeText(getApplicationContext(), "You cant make name empty", Toast.LENGTH_SHORT).show();
                    return;}
                TextView textView = (TextView) findViewById(R.id.textView2);
                textView.setText(counterList.get(clickedPosition1).toString2());
            }
        });
        submitComment.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                EditText editText = (EditText) findViewById(R.id.editTextComment);
                counterList.get(clickedPosition1).setComment(editText.getText().toString());
                TextView textView = (TextView) findViewById(R.id.textView2);
                textView.setText(counterList.get(clickedPosition1).toString2());
            }
        });
        submitCount.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                EditText editText = (EditText) findViewById(R.id.editTextCount);
                //Handle wrong input
                if (editText.getText().toString().matches("")) {
                    Toast.makeText(getApplicationContext(), "You cant make count empty", Toast.LENGTH_SHORT).show();
                    return;
                }
                int i = Integer.parseInt(editText.getText().toString());
                counterList.get(clickedPosition1).setCurrentValue(i);
                TextView textView = (TextView) findViewById(R.id.textView2);
                textView.setText(counterList.get(clickedPosition1).toString2());
            }
        });
        minus.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                counterList.get(clickedPosition1).setCurrentValue(clickedCounter.getCurrentValue()-1);
                //handle attempt for illegal value
                if (counterList.get(clickedPosition1).getCurrentValue() < 0) {
                    Toast.makeText(getApplicationContext(), "You cant make count negative", Toast.LENGTH_SHORT).show();
                    return;}
                TextView textView = (TextView) findViewById(R.id.textView2);
                textView.setText(counterList.get(clickedPosition1).toString2());
            }
        });

        plus.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                counterList.get(clickedPosition1).setCurrentValue(clickedCounter.getCurrentValue()+1);
                TextView textView = (TextView) findViewById(R.id.textView2);
                textView.setText(counterList.get(clickedPosition1).toString2());
            }
        });
        reset.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                counterList.get(clickedPosition1).setCurrentValue(clickedCounter.getInitialValue());
                TextView textView = (TextView) findViewById(R.id.textView2);
                textView.setText(counterList.get(clickedPosition1).toString2());
            }
        });
        //delete the item from file, by clearing the file and rerecording it
        buttonDelete.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                deleteInFile();
                counterList.remove(clickedPosition1);
                saveInFile();
                number-=1;
                Intent intent = new Intent(EditCounter.this, CountBookActivity.class);
                startActivity(intent);
                finish();


            }
        });
        // Save the  modified item from file, by clearing the file and rerecording it
        buttonSave.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                deleteInFile();
                saveInFile();
                Intent intent = new Intent(EditCounter.this, CountBookActivity.class);
                startActivity(intent);
                finish();


            }
        });




    }
}

