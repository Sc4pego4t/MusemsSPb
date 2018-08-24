package com.nixbyte.project.activities.list;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nixbyte.project.R;
import com.nixbyte.project.activities.details.DetailsActivity;
import com.nixbyte.project.modules.actionbar.controller.buttons_behavior.StartDetailsActivity;
import com.nixbyte.project.utils.App;
import com.nixbyte.project.utils.Keywords;
import com.nixbyte.project.utils.responseType.Row;
import com.nixbyte.project.utils.store.TemporarilyStored;

import java.util.ArrayList;
import java.util.Map;


/**
 * Created by scapegoat on 21/04/2018.
 */

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder> {

    private ArrayList<Row> mDataset;
    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public CardView cardView;
        public ViewHolder(CardView v) {
            super(v);

            cardView = v;
            Log.e("gg","3");
        }

    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public ListAdapter(ArrayList<Row> myDataset) {
        mDataset = myDataset;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public ListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Log.e("gg","1");
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item, parent, false);
        // set the view's size, margins, paddings and layout parameters

        ViewHolder vh = new ViewHolder((CardView)v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Log.e("gg","2");
        Row currentRow=mDataset.get(position);
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        LinearLayout linearLayout=holder.cardView.findViewById(R.id.ll);
        ((TextView)linearLayout.findViewById(R.id.title)).setText(currentRow.getName());
        ((TextView)linearLayout.findViewById(R.id.address)).setText(currentRow.getAddress_manual());
        ((TextView)linearLayout.findViewById(R.id.descr)).setText(currentRow.getDescription());

        linearLayout.setOnClickListener(e->{
            new StartDetailsActivity(MyListView.abstractActivity).perform();
            TemporarilyStored.row=currentRow;
        });
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.size();
    }


}
