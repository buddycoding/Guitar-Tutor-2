package com.example.guitartutor.Migrate.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.guitartutor.R;
import com.example.guitartutor.Migrate.SongsActivity;

import java.util.List;


public class SongsListAdapter extends RecyclerView.Adapter<SongsListAdapter.ViewHolder>{

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView nameTextView;
        public TextView artistTextView;
        public TextView noTextView;
        public LinearLayout linearLayout;

        public ViewHolder(View itemView, final Context context) {
            super(itemView);

            noTextView = itemView.findViewById(R.id.song_number);
            nameTextView = itemView.findViewById(R.id.song_title);
            artistTextView = itemView.findViewById(R.id.song_artist);
            linearLayout =  itemView.findViewById(R.id.ll_layout);

            linearLayout.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view) {
                    ((SongsActivity) context).userItemClick(getAdapterPosition());
                }
            });
        }
    }

    private List<SongsList> mSongsList;

    public SongsListAdapter(List<SongsList> contacts) {
        mSongsList = contacts;
    }

    @Override
    public SongsListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        if (context != null) {
            View songListView = inflater.inflate(R.layout.activity_song_chords_list, parent, false);
            ViewHolder viewHolder = new ViewHolder(songListView, context);
            return viewHolder;
        }

        return null;
    }

    @Override
    public void onBindViewHolder(SongsListAdapter.ViewHolder viewHolder, int position) {
        // Get the data model based on position
        SongsList songs = mSongsList.get(position);

        // Set item views based on your views and data model
        TextView textView3 = viewHolder.noTextView;
        TextView textView = viewHolder.nameTextView;
        TextView textView2 = viewHolder.artistTextView;
        textView.setText(songs.getName());
        textView2.setText(songs.getArtist());
        textView3.setText(songs.getNumber());
    }

    @Override
    public int getItemCount() {
        return mSongsList.size();
    }
}
