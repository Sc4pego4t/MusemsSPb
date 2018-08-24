package com.nixbyte.project.activities.map;

import android.content.res.TypedArray;
import android.text.Html;
import android.text.util.Linkify;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nixbyte.project.R;
import com.nixbyte.project.utils.App;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import static com.nixbyte.project.utils.store.TemporarilyStored.row;

/**
 * Created by scapegoat on 21/04/2018.
 */

public class CommentsAdapter extends RecyclerView.Adapter<CommentsAdapter.ViewHolder> {

    int position;
    List<String> commentList;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public CardView cardView;

        public ViewHolder(CardView v) {
            super(v);
            cardView = v;

        }
    }

    public CommentsAdapter(List<String> commentList) {
        Log.e("SIZE",commentList.size()+"");
        this.commentList=commentList;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public CommentsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.comment_item, parent, false);
        // set the view's size, margins, paddings and layout parameters
        ViewHolder vh = new ViewHolder((CardView)v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        this.position=position;
        LinearLayout linearLayout=holder.cardView.findViewById(R.id.layout);
        ((TextView)linearLayout.findViewById(R.id.text)).setText(commentList.get(position));

    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return commentList.size();
    }


}
