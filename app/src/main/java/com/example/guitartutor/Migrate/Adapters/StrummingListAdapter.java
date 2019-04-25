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

import java.util.List;


public class StrummingListAdapter extends RecyclerView.Adapter<StrummingListAdapter.ViewHolder>{

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView captionTextView;
        public TextView titleTextView;
        public ImageView imageImageView;
        public LinearLayout linearLayout;

        public ViewHolder(View itemView, final Context context) {
            super(itemView);

            captionTextView = itemView.findViewById(R.id.strumContent);
            titleTextView = itemView.findViewById(R.id.strumTitle);
            imageImageView = itemView.findViewById(R.id.imgIcon);
            linearLayout =  itemView.findViewById(R.id.ll_layout);
        }
    }

    private List<StrummingList> mStrumList;
    Context context;

    public StrummingListAdapter(List<StrummingList> strumlist) {
        mStrumList = strumlist;
    }

    @Override
    public StrummingListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        if (context != null) {
            View strumListView = inflater.inflate(R.layout.fragment_strumming_one_list, parent, false);
            ViewHolder viewHolder = new ViewHolder(strumListView, context);
            return viewHolder;
        }

        return null;
    }

    @Override
    public void onBindViewHolder(StrummingListAdapter.ViewHolder viewHolder, int position) {
        // Get the data model based on position
        StrummingList strum = mStrumList.get(position);

        // Set item views based on your views and data model
        TextView textView = viewHolder.captionTextView;
        TextView textView2 = viewHolder.titleTextView;
        ImageView imageView = viewHolder.imageImageView;
        textView.setText(strum.getCaption());
        textView2.setText(strum.getTitle());
        //imageView.setImageResource(R.drawable.strum_tutor_01);

        imageView.setImageResource(context.getResources().getIdentifier(strum.getImageName(), "drawable", context.getPackageName()));
    }

    @Override
    public int getItemCount() {
        return mStrumList.size();
    }
}
