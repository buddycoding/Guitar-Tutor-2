package com.example.guitartutor.Migrate.Adapters;

import java.util.ArrayList;

public class SongsList {
    private String mSongName;
    private String mSongArtist;
    private String mSongNo;

    public SongsList(String name, String artist, String number){
        mSongNo = number;
        mSongName = name;
        mSongArtist = artist;
    }

    public String getNumber() {
        return mSongNo;
    }
    public String getName() {
        return mSongName;
    }
    public String getArtist() {
        return mSongArtist;
    }

    public static ArrayList<SongsList> createSongList(){
        ArrayList<SongsList> songs = new ArrayList<>();
        songs.add(new SongsList("Can't Help Falling In Love", "Elvis Presley", "01"));
        songs.add(new SongsList("Malibu Nights", "LANY", "02"));
        songs.add(new SongsList("Let It Be", "The Beatles", "03"));
        songs.add(new SongsList("You're Beautiful", "James Blunt", "04"));
        songs.add(new SongsList("How Deep Is Your Love", "Bee Gees", "05"));
        songs.add(new SongsList("Just The Way You Are", "Bruno Mars", "06"));
        songs.add(new SongsList("Fix You", "Coldplay", "07"));
        songs.add(new SongsList("Thinking Out Loud", "Ed Sheeran", "08"));
        songs.add(new SongsList("Wonderful Tonight", "Eric Clapton", "09"));
        songs.add(new SongsList("She Will Be Loved", "Maroon 5", "10"));

        return songs;
    }
}
