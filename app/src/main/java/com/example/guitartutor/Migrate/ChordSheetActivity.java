package com.example.guitartutor.Migrate;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.example.guitartutor.R;
import com.example.guitartutor.Migrate.Adapters.ChordsList;
import com.example.guitartutor.Migrate.Adapters.ChordsListAdapter;

import java.util.ArrayList;


public class ChordSheetActivity extends AppCompatActivity {

    ArrayList<ChordsList> chords = ChordsList.createChordList();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chord_sheet);

        /*
        button1 = (Button) findViewById(R.id.buttonhome);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMainActivity();
            }
        });*/

        RecyclerView rvChords = findViewById(R.id.rvChords);
        ChordsListAdapter adapter = new ChordsListAdapter(chords);
        rvChords.setAdapter(adapter);
        rvChords.setLayoutManager(new LinearLayoutManager(this));
    }

    public void userItemClick(int pos) {

        Toast.makeText(this, "Clicked User : " + chords.get(pos).getName(), Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, ChordsSoundsActivity.class);
        intent.putExtra("ChordsName", chords.get(pos).getName());
        intent.putExtra("ChordsColor", chords.get(pos).getColor());
        startActivity(intent);
        /*
        Intent intent = new Intent (this, SongsChordsActivity.class);
        intent.putExtra("ChordsName", chords.get(pos).getName());
        startActivity(intent);*/
    }
}
