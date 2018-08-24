package com.nixbyte.project.activities.list;

import android.content.Context;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;

import com.nixbyte.project.R;
import com.nixbyte.project.modules.actionbar.ActionBarConstructor;
import com.nixbyte.project.modules.actionbar.controller.buttons_behavior.OpenMap;
import com.nixbyte.project.modules.activity.AbstractActivity;
import com.nixbyte.project.modules.activity.Viewable;
import com.nixbyte.project.utils.store.TemporarilyStored;

/**
 * Created by scapegoat on 18/04/2018.
 */

public class MyListView implements Viewable<String> {

    public static RecyclerView recyclerView;
    public static RecyclerView.Adapter adapter;
    RecyclerView.LayoutManager manager;

    ActionBarConstructor.Builder builder;
    ActionBarConstructor actionBar;
    Context context;
    public static AbstractActivity abstractActivity;




    public MyListView(AbstractActivity activity, View rootView) {
        recyclerView=rootView.findViewById(R.id.recycler_view);
        abstractActivity=activity;
        context=rootView.getContext();
        builder = new ActionBarConstructor.Builder(activity)
                .titleFromString(TemporarilyStored.title)
                .mapBehavior(new OpenMap(activity));


        //создание appbar, в зависимости от того авторизован пользователь чи нет
        if(activity.autorized.equals("")){
            builder.menu(R.menu.menu_with_map);
        } else {
            builder.menu(R.menu.menu_with_map_autorized);
        }

        manager=new LinearLayoutManager(context);
    }

    @Override
    public void onAttach(String data) {
        this.actionBar=builder.build();
        this.actionBar.setActionBar();
    }
}
