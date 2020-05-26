package com.example.cs492_s2.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.cs492_s2.R;

import java.util.ArrayList;

public class GalleryAdapter extends RecyclerView.Adapter<GalleryAdapter.GalleryViewHolder> {
    private ArrayList<String> mDataset;
    private Activity activity;


    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class GalleryViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public CardView cardView;
        public GalleryViewHolder(CardView v) {
            super(v);
            cardView = v;
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public GalleryAdapter(Activity activity, ArrayList<String> myDataset) {
        mDataset = myDataset;
        this.activity = activity;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public GalleryAdapter.GalleryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // create a new view
        CardView v = (CardView) LayoutInflater.from(parent.getContext()).inflate(R.layout.item_gallery, parent, false);
        GalleryViewHolder vh = new GalleryViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(GalleryViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        ImageView imageView = holder.cardView.findViewById(R.id.imageView2);
        Glide.with(activity).load(mDataset.get(position)).centerCrop().override(500).into(imageView);

    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.size();
    }
}
