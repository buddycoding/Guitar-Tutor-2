package com.example.guitartutor.Migrate;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.guitartutor.R;
import com.example.guitartutor.Migrate.Adapters.SongsList;
import com.example.guitartutor.Migrate.Adapters.SongsListAdapter;

import java.util.ArrayList;


public class SongsActivity extends AppCompatActivity {

    private Button button1;
    ArrayList<SongsList> songs = SongsList.createSongList();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_songs);

        button1 = findViewById(R.id.buttonhome);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
                //openMainActivity();
            }
        });

        RecyclerView rvContacts = findViewById(R.id.rvContacts);
        SongsListAdapter adapter = new SongsListAdapter(songs);
        rvContacts.setAdapter(adapter);
        rvContacts.setLayoutManager(new LinearLayoutManager(this));
    }

    public void userItemClick(int pos) {
        Toast.makeText(this, "Clicked User : " + songs.get(pos).getName(), Toast.LENGTH_SHORT).show();

        Intent intent = new Intent (this, SongsChordsActivity.class);
        intent.putExtra("SongName", songs.get(pos).getName());
        intent.putExtra("ArtistName", songs.get(pos).getArtist());
        intent.putExtra("SongNumber", songs.get(pos).getNumber());
        startActivity(intent);
    }
}
