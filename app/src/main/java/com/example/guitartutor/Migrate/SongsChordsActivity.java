package com.example.guitartutor.Migrate;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.guitartutor.R;
import com.github.chrisbanes.photoview.PhotoView;
//import com.github.chrisbanes.photoview.PhotoView;


public class SongsChordsActivity extends AppCompatActivity {

    private Button button1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_songs_chords);

        Intent myIntent = getIntent();
        TextView songName = findViewById(R.id.songName);
        TextView artistName = findViewById(R.id.artistName);

        songName.setText(myIntent.getStringExtra("SongName"));
        artistName.setText(myIntent.getStringExtra("ArtistName"));


        PhotoView photoView = findViewById(R.id.pv_songimage);
        //photoView.setImageResource(R.drawable.song01);
        photoView.setImageResource(this.getResources().getIdentifier("song" + myIntent.getStringExtra("SongNumber"), "drawable", this.getPackageName()));



        button1 = findViewById(R.id.buttonhome);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

    }
}
