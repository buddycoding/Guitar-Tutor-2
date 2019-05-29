package com.example.guitartutor.Migrate;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.guitartutor.R;


public class GuitarTutorialActivity extends AppCompatActivity {

    private Button button1;
    private Button button2;
    private Button button3;
    private Button button4;
    private Button button5;
    private Button button6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guitar_tutorial);
        button1 = (Button) findViewById(R.id.buttonhome);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
                //openMainActivity();
            }
        });

        button2 = findViewById(R.id.button8);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openGuitarParts();
            }
        });

        button3 = findViewById(R.id.button10);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openGuitarStrings();
            }
        });

        button4 = findViewById(R.id.button14);
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openGuitarHold();
            }
        });

        button5 = findViewById(R.id.button15);
        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openGuitarStrum();
            }
        });

        button6 = findViewById(R.id.button16);
        button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openGuitarFinger();
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

    public void openGuitarParts () {
        Intent intent = new Intent (this, TutorialGuitarParts.class);
        startActivity(intent);
    }

    public void openGuitarStrings () {
        Intent intent = new Intent (this, TutorialGuitarString.class);
        startActivity(intent);
    }

    public void openGuitarHold () {
        Intent intent = new Intent (this, TutorialGuitarHolding.class);
        startActivity(intent);
    }

    public void openGuitarStrum () {
        Intent intent = new Intent (this, TutorialGuitarStrumming.class);
        startActivity(intent);
    }

    public void openGuitarFinger () {
        //Intent intent = new Intent (this, TutorialGuitarHolding.class);
        //startActivity(intent);
    }

    public void openChordsDetectActivity () {
        Intent intent = new Intent (this, ChordsDetectActivity.class);
        startActivity(intent);
    }
}
