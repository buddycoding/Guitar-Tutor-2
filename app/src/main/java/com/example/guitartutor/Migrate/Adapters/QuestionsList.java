package com.example.guitartutor.Migrate.Adapters;

import java.util.ArrayList;

public class QuestionsList {
    private String mChordName;
    private int[] mChordFret;
    private int[] mChordString;


    public QuestionsList(String chordName, int[] chordFret, int[] chordString){
        mChordName = chordName;
        mChordFret = chordFret;
        mChordString = chordString;
    }

    public String getName() { return mChordName; }
    public int[]getFret(){ return mChordFret; }
    public int[]getString(){ return mChordString; }

    public static ArrayList<QuestionsList> createQuestionList(){
        ArrayList<QuestionsList> chords = new ArrayList<>();

        chords.add(new QuestionsList("d", new int[]{ -1, -1, 0, 2, 3, 2 }, new int[]{ -1, -1, 0, 1, 3, 2 }));
        chords.add(new QuestionsList("e", new int[]{ 0, 2, 2, 1, 0, 0 }, new int[]{ 0, 2, 3, 1, 0, 0 }));
        chords.add(new QuestionsList("g", new int[]{ 3,2,0,0,0,3 }, new int[]{ 2,1,0,0,0,3 }));

        chords.add(new QuestionsList("c", new int[]{ -1,3,2,0,1,0 }, new int[]{ -1,3,2,0,1,0 }));
        chords.add(new QuestionsList("em", new int[]{ 0,2,2,0,0,0 }, new int[]{ 0,1,2,0,0,0 }));
        chords.add(new QuestionsList("am", new int[]{ -1,0,2,2,1,0 }, new int[]{ -1,0,2,3,1,0 }));
        chords.add(new QuestionsList("a", new int[]{ -1,0,2,2,2,0 }, new int[]{ -1,0,2,1,3,0 }));
        chords.add(new QuestionsList("e", new int[]{ 0,2,2,1,0,0 }, new int[]{ 0,2,3,1,0,0 }));
        chords.add(new QuestionsList("dm", new int[]{ -1,-1,0,2,3,1 }, new int[]{ -1,-1,0,2,3,1 }));

        chords.add(new QuestionsList("bm", new int[]{ -1,2,4,4,3,0 }, new int[]{ -1,1,3,4,2,0 }));
        chords.add(new QuestionsList("f", new int[]{ -1,-1,3,2,1,0 }, new int[]{ -1,-1,3,2,1,0 }));
        chords.add(new QuestionsList("f7", new int[]{ -1,-1,3,2,1,0 }, new int[]{ -1,-1,3,2,1,0 }));
        chords.add(new QuestionsList("f9", new int[]{ -1,-1,3,2,1,3 }, new int[]{ -1,-1,3,2,1,4 }));
        chords.add(new QuestionsList("d7", new int[]{ -1,-1,0,2,1,2 }, new int[]{ -1,-1,0,2,1,3 }));
        chords.add(new QuestionsList("e7", new int[]{ 0,2,0,1,0,0 }, new int[]{ 0,2,0,1,0,0 }));
        chords.add(new QuestionsList("a7", new int[]{ -1,0,2,0,2,0 }, new int[]{ -1,0,1,0,2,0 }));
        chords.add(new QuestionsList("g7", new int[]{ 3,2,0,0,0,1 }, new int[]{ 3,2,0,0,0,1 }));
        chords.add(new QuestionsList("c7", new int[]{ -1,3,2,3,1,0 }, new int[]{ -1,3,2,4,1,0 }));
        chords.add(new QuestionsList("b7", new int[]{ -1,2,1,2,0,2 }, new int[]{ -1,2,1,3,0,4 }));


        return chords;
    }
}
