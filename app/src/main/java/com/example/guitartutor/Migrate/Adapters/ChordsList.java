package com.example.guitartutor.Migrate.Adapters;

import java.util.ArrayList;

public class ChordsList {
    private String mChordName;
    private int mChordColor;

    public ChordsList(String chordName, int chordNameColor){
        mChordName = chordName;
        mChordColor = chordNameColor;
    }

    public String getName() {
        return mChordName;
    }
    public int getColor(){ return mChordColor; }

    public static ArrayList<ChordsList> createChordList(){
        ArrayList<ChordsList> chords = new ArrayList<>();

        /*
            color codes:
                0   yellow
                1   red
         */
        chords.add(new ChordsList("gdc", 0));
        chords.add(new ChordsList("emamg", 1));
        chords.add(new ChordsList("ae", 0));
        chords.add(new ChordsList("dmbmf", 1));
        chords.add(new ChordsList("f7f9",  0));
        chords.add(new ChordsList("d7e7a7", 1));
        chords.add(new ChordsList("g7c7b7", 1));
        return chords;
    }
}
