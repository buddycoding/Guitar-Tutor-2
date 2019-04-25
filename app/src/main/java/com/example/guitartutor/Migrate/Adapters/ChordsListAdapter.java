package com.example.guitartutor.Migrate.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.guitartutor.R;
import com.example.guitartutor.Migrate.ChordSheetActivity;

import java.util.List;


public class ChordsListAdapter extends RecyclerView.Adapter<ChordsListAdapter.ViewHolder>{

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView nameTextView;
        public ImageView chordImageView;
        public LinearLayout linearLayout;

        public ViewHolder(View itemView, final Context context) {
            super(itemView);

            //nameTextView = itemView.findViewById(R.id.chord_name);
            chordImageView = itemView.findViewById(R.id.chord_image);
            linearLayout =  itemView.findViewById(R.id.ll_layout_chord);

            linearLayout.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view) {
                    ((ChordSheetActivity) context).userItemClick(getAdapterPosition());
                }
            });
        }
    }

    private List<ChordsList> mChordsList;
    Context context;

    public ChordsListAdapter(List<ChordsList> chords) {
        mChordsList = chords;
    }

    @Override
    public ChordsListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        if (context != null) {
            View chordListView = inflater.inflate(R.layout.activity_chord_list, parent, false);
            ViewHolder viewHolder = new ViewHolder(chordListView, context);
            return viewHolder;
        }

        return null;
    }

    @Override
    public void onBindViewHolder(ChordsListAdapter.ViewHolder viewHolder, int position) {
        // Get the data model based on position
        ChordsList chords = mChordsList.get(position);

        // Set item views based on your views and data model
        //TextView textView = viewHolder.nameTextView;
        //textView.setText(chords.getName());

        ImageView imageView = viewHolder.chordImageView;
        imageView.setImageResource(context.getResources().getIdentifier(chords.getName(), "drawable", context.getPackageName()));
    }

    @Override
    public int getItemCount() {
        return mChordsList.size();
    }
}
