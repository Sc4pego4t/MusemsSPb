package com.nixbyte.project.activities.details;

import android.content.res.TypedArray;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
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

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.nixbyte.project.utils.store.TemporarilyStored.row;

/**
 * Created by scapegoat on 21/04/2018.
 */

public class DetailsAdapter extends RecyclerView.Adapter<DetailsAdapter.ViewHolder> {

    int position;
    List<String> arrayList=new ArrayList<>();
    ArrayList<String> objectText=new ArrayList<>();
    private TypedArray ids;
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

    List<String> allCategories;
    public DetailsAdapter() {

        arrayList= Arrays.asList(App.getContext().getResources().getStringArray(R.array.objectInfo));
        allCategories=Arrays.asList(App.getContext().getResources().getStringArray(R.array.objectInfo));
        objectText.add(row.getDescription());
        objectText.add(row.getMetro());
        objectText.add(row.getAddress_manual());
        objectText.add(row.getDistrict());
        objectText.add(row.getWww());
        objectText.add(row.getPhone());
        objectText.add(row.getEmail());


        ArrayList<String> tempText=new ArrayList<>();
        ArrayList<String> tempCategory=new ArrayList<>();

        int i=0;
        for(String s : objectText){
            if(!s.equals("")) {
                tempText.add(s);
                tempCategory.add(arrayList.get(i));
            }
            i++;
        }

        objectText=tempText;
        arrayList=tempCategory;

    }

    // Create new views (invoked by the layout manager)
    @Override
    public DetailsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.objectinfo_item, parent, false);
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
        String category=arrayList.get(position);
        LinearLayout linearLayout=holder.cardView.findViewById(R.id.ll);
        ((TextView)linearLayout.findViewById(R.id.category)).setText(category);
        String text=objectText.get(position);
        TextView textView = linearLayout.findViewById(R.id.text);
        if(category.equals(allCategories.get(4))
                || category.equals(allCategories.get(5))
                || category.equals(allCategories.get(6))) {
            text = "<u>" + text + "</u>";
            textView.setTextColor(App.getContext().getResources().getColor(R.color.blood));
            textView.setLinksClickable(true);
            textView.setAutoLinkMask(Linkify.ALL);
        }
        textView.setText(Html.fromHtml(text));
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return arrayList.size();
    }


}
