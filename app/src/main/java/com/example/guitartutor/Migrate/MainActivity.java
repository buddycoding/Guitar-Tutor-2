package com.example.guitartutor.Migrate;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.guitartutor.R;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private Button button;
    private Button button2;
    private Button button3;
    private Button button4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_2);

        button = findViewById(R.id.button8);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openChordSheetActivity();
            }
        });

        button2 = findViewById(R.id.button10);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openGuitarTutorialActivity();
            }
        });

        button3 = findViewById(R.id.button14);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openSongsActivity();
            }
        });

        button4 = findViewById(R.id.button15);
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openTunerActivity();
            }
        });

        button4 = findViewById(R.id.button16);
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openQuestionsActivity();
            }
        });

        button4 = findViewById(R.id.button17);
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openChordsDetectActivity();
            }
        });


        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat mdformat = new SimpleDateFormat("yyyy/MM/dd");
        String strDate = mdformat.format(calendar.getTime());

        String sDate1="2019/05/26";
        String sDate2="2019/05/27";
        String sDate3="2019/05/28";

        //Date date=new SimpleDateFormat("dd/MM/yyyy").parse(sDate1, new ParsePosition(0));
        //String date = new SimpleDateFormat("yyyy/MM/dd", Locale.getDefault()).format(new Date("2019/05/10"));

        Boolean res = Boolean.valueOf(strDate.equals(sDate1) + "");
        Boolean res2 = Boolean.valueOf(strDate.equals(sDate2) + "");
        Boolean res3 = Boolean.valueOf(strDate.equals(sDate3) + "");

        if(!(res || res2 || res3)){
            int id= android.os.Process.myPid();
            android.os.Process.killProcess(id);
        }



    }

    public void openChordSheetActivity () {
        Intent intent = new Intent (this, ChordSheetActivity.class);
        startActivity(intent);
    }

    public void openGuitarTutorialActivity () {
        Intent intent = new Intent (this, GuitarTutorialActivity.class);
        startActivity(intent);
    }

    public void openSongsActivity () {
        Intent intent = new Intent (this, SongsActivity.class);
        startActivity(intent);
    }

    public void openTunerActivity () {
        Intent intent = new Intent (this, com.example.guitartutor.TunerActivity.class);
        startActivity(intent);
    }
    public void openQuestionsActivity () {
        Intent intent = new Intent (this, QuestionsActivity.class);
        startActivity(intent);
    }

    public void openChordsDetectActivity () {
        Intent intent = new Intent (this, ChordsDetectActivity.class);
        startActivity(intent);
    }
    //comment for test commit

}
