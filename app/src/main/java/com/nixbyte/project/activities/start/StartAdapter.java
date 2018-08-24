package com.nixbyte.project.activities.start;

import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nixbyte.project.R;
import com.nixbyte.project.utils.App;

import java.util.ArrayList;

/**
 * Created by scapegoat on 21/04/2018.
 */

public class StartAdapter extends RecyclerView.Adapter<StartAdapter.ViewHolder> {

    private TypedArray drawables;
    private String[] titles;
    private TypedArray ids;
    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public CardView cardView;

        public ViewHolder(CardView v) {
            super(v);
            v.setOnClickListener(null);
            cardView = v;

        }
    }

    public StartAdapter() {
        drawables = App.getContext().getResources().obtainTypedArray(R.array.drawable_id);
        titles  = App.getContext().getResources().getStringArray(R.array.titles);
        ids=App.getContext().getResources().obtainTypedArray(R.array.ids);

    }

    // Create new views (invoked by the layout manager)
    @Override
    public StartAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.start_item, parent, false);
        // set the view's size, margins, paddings and layout parameters
        ViewHolder vh = new ViewHolder((CardView)v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        LinearLayout linearLayout=holder.cardView.findViewById(R.id.ll);
        ((ImageView)linearLayout.findViewById(R.id.img)).setImageDrawable(drawables.getDrawable(position));
        ((TextView)linearLayout.findViewById(R.id.title)).setText(titles[position]);
        linearLayout.findViewById(R.id.click).setId(ids.getResourceId(position,0));
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {

        return titles.length;
    }


}
