package com.oadex.starter.model;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.oadex.starter.R;

import java.util.List;

/**
 * Created by Malik on 01-Nov-17.
 */

public class StarterAdapter extends RecyclerView.Adapter<StarterAdapter.StarterViewHolder> {

    private List<Starter> items;

    public StarterAdapter(List<Starter> list)
    {
        items = list;
    }

    @Override
    public StarterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_items, parent, false);
        return new StarterViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(StarterViewHolder holder, int position) {

        Starter starter = items.get(position);
        holder.titleTextView.setText(starter.getTitle());
//        holder.descriptionTextView.setText(starter.getDescription());
        Log.i("PIC_DATA", "" +starter.getPicture());
        holder.imageView.setImageBitmap(Util.decodePicture(starter.getPicture()));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class StarterViewHolder extends RecyclerView.ViewHolder
    {
        public ImageView imageView;
        public TextView titleTextView;
//        public TextView descriptionTextView;

        public StarterViewHolder(View itemView)
        {
            super(itemView);

            imageView = itemView.findViewById(R.id.imageView);
            titleTextView = itemView.findViewById(R.id.titleView);
//            descriptionTextView = itemView.findViewById(R.id.descriptionView);
        }
    }
}
