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
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;


import static com.vladkravchenko.vvkravch_countbook.CountBookActivity.EXTRA_MESSAGE;

/**
 * SetCounter represents the activity where the user can initialize the counter.
 */
public class SetCounter extends CountBookActivity {
    //private static final String FILENAME = "file.sav";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_counter);

        Button saveButton = (Button) findViewById(R.id.save);
        saveButton.setOnClickListener(new View.OnClickListener() {
        public void onClick(View v) {
            Intent intent = new Intent(SetCounter.this, CountBookActivity.class);

            EditText editText = (EditText) findViewById(R.id.editText);
            String name = editText.getText().toString();
            if (name.matches("")) {
                //Learned from https://developer.android.com/guide/topics/ui/notifiers/toasts.html
                //2017-10-01
                //Creates user input control
                Toast.makeText(getApplicationContext(), "You did not enter a name", Toast.LENGTH_SHORT).show();
                return;
            }

            //Reads the comment entered by user.
            EditText editText2 = (EditText) findViewById(R.id.editText2);
            String comment = editText2.getText().toString();

            //Reads the Initial Value entered by user.
            EditText editText3 = (EditText) findViewById(R.id.editText3);
            String initialValue = editText3.getText().toString();

            //Creates user input control
            if (initialValue.matches("")) {
                Toast.makeText(getApplicationContext(), "You did not enter the initial value", Toast.LENGTH_SHORT).show();
                return;
            }
            int intInitialValue = Integer.parseInt(initialValue);
            //Construct a Counter
            Counter newCounter = new Counter(name, intInitialValue, comment);

            //else
            //Counter newCounter1 = new Counter(name, intInitialValue);

            counterList.add(newCounter);
            saveInFile();
            number +=1;
            startActivity(intent); // Start new CountBook Activity
            //For Efficiency activity is finished every time, will be improved in future versions
            finish();
        }
        });
    }

}
