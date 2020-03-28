package com.example.pokeapi;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ListAdapter extends RecyclerView.Adapter<ViewHolder> {
    private List<Countries> values;



    public void add(int position, Countries item) {
        values.add(position, item);
        notifyItemInserted(position);
    }

    public void remove(int position) {
        values.remove(position);
        notifyItemRemoved(position);
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public ListAdapter(List<Countries> myDataset) {
        values = myDataset;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.row_layout, parent, false);
        // set the view's size, margins, paddings and layout parameters
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        final Countries currentCountry = values.get(position);
        holder.txtHeader.setText(currentCountry.toString());
        holder.txtHeader.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = v.getContext();
                Intent intent = new Intent(context,DetailActivity.class);
                intent.putExtra("TotalCase",currentCountry.getTotalConfirmed());
                intent.putExtra("NewCase",currentCountry.getNewConfirmed());
                intent.putExtra("TotalDead",currentCountry.getTotalDeath());
                intent.putExtra("NewDead",currentCountry.getNewDeath());
                intent.putExtra("TotalRecovered",currentCountry.getTotalRecovered());
                intent.putExtra("NewRecovered",currentCountry.getNewRecovered());
                intent.putExtra("Pays",currentCountry.getCountry());
                context.startActivity(intent);
            }
        });

    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return values.size();
    }

}

