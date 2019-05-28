package com.example.guitartutor.Migrate;

import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.guitartutor.R;

import java.util.ArrayList;
import java.util.List;


public class ChordsSoundsActivity extends AppCompatActivity {

    private MediaPlayer media;
    private List<Button> btnList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chords_sounds);

        Intent myIntent = getIntent();
        TextView chordsName = findViewById(R.id.chordsNames);

        String chordsNameString = myIntent.getStringExtra("ChordsName");
        int colorChords = myIntent.getIntExtra("ChordsColor",0);

        chordsName.setText(chordsNameString.toUpperCase().replaceAll(".(?!$)", "$0 "));
        String chordsNameTV = chordsName.getText().toString();
        chordsNameTV = chordsNameTV.replaceAll(" M", "m");
        chordsNameTV = chordsNameTV.replaceAll(" 7", "7");
        chordsNameTV = chordsNameTV.replaceAll(" 9", "9");
        chordsNameTV = chordsNameTV.replaceAll(" S", "#");
        chordsName.setText(chordsNameTV);

        ImageView imageView =  findViewById(R.id.chords_sounds_image);
        imageView.setImageResource(this.getResources().getIdentifier(chordsNameString + "2", "drawable", this.getPackageName()));

        RelativeLayout lLayout = findViewById(R.id.ll_chordsNames);

        LinearLayout newLL = new LinearLayout(this);
        newLL.setOrientation(LinearLayout.HORIZONTAL);

        btnList = new ArrayList<>();
        int weightCount = 0;
        for(int x=0;x<chordsNameString.length();x++)
        {
            String textChordName = chordsNameString.charAt(x) + "";

            //final String chordSoundName = chordsNameString.charAt(x) + "";
            String chordSoundName = chordsNameString.charAt(x) + "";
            final String filename = "android.resource://" + this.getPackageName() + "/raw/"; //issue wrong file pointer for fixing

            switch (chordsNameString.charAt(x) + ""){
                case "m":   btnList.get(btnList.size()-1).setText(btnList.get(btnList.size()-1).getText() + "m");
                            //setFileName(btnList.size()-1, filename + btnList.get(btnList.size()-1).getText().toString().toLowerCase().replace("play ",""));
                            setFileNamePost(filename);
                            break;
                case "7":   btnList.get(btnList.size()-1).setText(btnList.get(btnList.size()-1).getText() +"7");
                            //setFileName(btnList.size()-1, filename + btnList.get(btnList.size()-1).getText().toString().toLowerCase().replace("play ",""));
                            setFileNamePost(filename);
                            break;
                case "9":   btnList.get(btnList.size()-1).setText(btnList.get(btnList.size()-1).getText() +"9");
                            //setFileName(btnList.size()-1, filename + btnList.get(btnList.size()-1).getText().toString().toLowerCase().replace("play ",""));
                            setFileNamePost(filename);
                            break;
                case "s":   btnList.get(btnList.size()-1).setText(btnList.get(btnList.size()-1).getText() + "#");
                            //setFileName(btnList.size()-1, filename + btnList.get(btnList.size()-1).getText().toString().toLowerCase().replace("play ",""));
                            setFileNamePost(filename);
                            break;
                default:
                    btnList.add(new Button(this));
                    btnList.get(btnList.size()-1).setText("Play " + textChordName.toUpperCase() + "");

                    /*
                    btnList.get(btnList.size()-1).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            //media = MediaPlayer.create(getApplicationContext(), R.raw.g);
                            media = new MediaPlayer();
                            try { media.setDataSource(getApplicationContext(), Uri.parse(filename)); } catch (Exception e) {}
                            try { media.prepare(); } catch (Exception e) {}
                            media.start();
                        }
                    });
                    */


                    setFileName(btnList.size()-1, filename + chordSoundName);

                    btnList.get(btnList.size()-1).setAllCaps(false);
                    LinearLayout.LayoutParams p = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                    p.weight = 1;

                    btnList.get(btnList.size()-1).setLayoutParams(p);
                    newLL.addView(btnList.get(btnList.size()-1));

                    weightCount += 1;
            }
        }

        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(-1, -2);
        newLL.setWeightSum(weightCount);

        if(colorChords == 0)
        {
            newLL.setBackgroundColor(Color.rgb(239,167,52));
        }
        else
        {
            newLL.setBackgroundColor(Color.rgb(207,52,52));
        }

        newLL.setPadding(0,0,0,40);
        newLL.setLayoutParams(lp);

        RelativeLayout.LayoutParams params= new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        params.addRule(RelativeLayout.BELOW, R.id.ll_chordsNames2);
        newLL.setLayoutParams(params);

        lLayout.addView(newLL);

        //
        /*
        ChordView mChordView = findViewById(R.id.chordView1);
        //Chord chord = new Chord(new int[]{-1, 3, 2, 0, 1, 4}, new int[]{0, 3, 2, 0, 1, 4});
        //fret number & (string position and label)
        Chord chord = new Chord(new int[]{-1, -1, 0, 2, 3, 2}, new int[]{0, 0, 0, 1, 3, 2});
        mChordView.setChord(chord);
        */
    }

    private void setFileName(int index, final String filename){

        btnList.get(index).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                media = new MediaPlayer();
                try { media.setDataSource(getApplicationContext(), Uri.parse(filename)); } catch (Exception e) {}
                try { media.prepare(); } catch (Exception e) {}
                media.start();

            }
        });
    }

    private void setFileNamePost(String filename){
        //setFileName(btnList.size()-1, filename + btnList.get(btnList.size()-1).getText().toString().toLowerCase().replace("play ",""));
        Log.w("fileName",filename + btnList.get(btnList.size()-1).getText().toString().toLowerCase().replace("play ","").replace("#", "s"));
        setFileName(btnList.size()-1, filename + btnList.get(btnList.size()-1).getText().toString().toLowerCase().replace("play ","").replace("#", "s"));

    }
}
