package com.example.guitartutor.Migrate;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.guitartutor.R;

public class MainActivity extends AppCompatActivity {

    private Button button;
    private Button button2;
    private Button button3;
    private Button button4;
    private Button button5;

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
