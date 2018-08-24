package com.nixbyte.project.activities.details;

import android.content.Context;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.nixbyte.project.R;
import com.nixbyte.project.modules.actionbar.ActionBarConstructor;
import com.nixbyte.project.modules.actionbar.controller.buttons_behavior.FinishActivity;
import com.nixbyte.project.modules.activity.AbstractActivity;
import com.nixbyte.project.modules.activity.Viewable;
import com.nixbyte.project.utils.responseType.Row;
import com.nixbyte.project.utils.store.TemporarilyStored;


/**
 * Created by nixbyte on 07.09.16.
 */
public class DetailsView implements Viewable<String> {


    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    RecyclerView.LayoutManager manager;
    public Context context;
    AbstractActivity activity;
    FloatingActionButton fab;
    public ImageView imageView;


    View rootView;

    public DetailsView(AbstractActivity activity, View rootView) {
        this.context = rootView.getContext();
        this.activity=activity;
        Toolbar toolbar =rootView.findViewById(R.id.toolbar);
        activity.setSupportActionBar(toolbar);
        activity.getSupportActionBar().setTitle(TemporarilyStored.row.getName());
        activity.getSupportActionBar().setHomeButtonEnabled(true);
        activity.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        fab=rootView.findViewById(R.id.fab);
        imageView=rootView.findViewById(R.id.image);
        recyclerView=rootView.findViewById(R.id.recycler_view);
        this.rootView=rootView;
        manager=new LinearLayoutManager(context);
    }


    public void onAttach(String authorizationData) {

    }
}
