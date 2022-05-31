package com.example.myapp_maptrack;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    //INITIALIZE VARIABLE

    EditText inputSource, inputDestination;
    Button btnTrack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //assign var

        inputSource =findViewById(R.id.enter_source);
        inputDestination = findViewById(R.id.enter_destination);
        btnTrack = findViewById(R.id.btn_track);

        btnTrack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //get value from edit text
                String sSource = inputSource.getText().toString().trim();
                String sDestination = inputDestination.getText().toString().trim();

                //check condition
                if (sSource.equals("") && sDestination.equals("")){
                    //when value is blank
                    Toast.makeText(getApplicationContext()
                            ,"enter both location",Toast.LENGTH_SHORT).show();
                }else{
                    //when both value is filled
                    //display track
                    DisplayTrack(sSource,sDestination);
                }
            }
        });
    }

    private void DisplayTrack(String sSource, String sDestination) {
        //if the device does not have a map installed, then redirect to play store
        try {
            //when google map is installed.
            //initialize uri
            Uri uri = Uri.parse("https://www.google.co.in/maps/dir/" + sSource + "/"
                    + sDestination);
            //Initialize intent with action view

            Intent intent = new Intent(Intent.ACTION_VIEW,uri);
            //Set package
            intent.setPackage("com.google.android.apps.maps");
            //Set Flag
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            //Start activity
            startActivity(intent);
        }catch (ActivityNotFoundException e){
            //when google map is not installed
            //Initialize URI

            Uri uri = Uri.parse("https://play.google.com/store/apps/details?id=com.google.android.apps.maps");
            //initialize intent with no action view
            Intent intent = new Intent(Intent.ACTION_VIEW,uri);
            //set flag
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            //Start activity
            startActivity(intent);
        }
    }

}