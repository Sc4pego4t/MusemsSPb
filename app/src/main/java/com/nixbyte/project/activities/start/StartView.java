package com.nixbyte.project.activities.start;

import android.content.Context;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.nixbyte.project.R;
import com.nixbyte.project.modules.actionbar.ActionBarConstructor;
import com.nixbyte.project.modules.actionbar.controller.buttons_behavior.FinishActivity;
import com.nixbyte.project.modules.activity.AbstractActivity;
import com.nixbyte.project.modules.activity.Viewable;


/**
 * Created by nixbyte on 07.09.16.
 */
public class StartView implements Viewable<String> {


    private ActionBarConstructor.Builder actionBarBulder;

    public ActionBarConstructor actionBar;

    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    RecyclerView.LayoutManager manager;
    Context context;

    View rootView;

    public StartView(AbstractActivity activity, View rootView) {
        this.context = rootView.getContext();

        this.actionBarBulder = new ActionBarConstructor.Builder(activity)
                .homeBehavior(new FinishActivity(activity))
                .title(R.string.startact)
                .setIcon(false);
        recyclerView=rootView.findViewById(R.id.recycler_view);
        this.rootView=rootView;
        manager=new LinearLayoutManager(context);
    }


    public void onAttach(String authorizationData) {
//
//        switch (App.getContext().getSharedPreferences(ChangeInfoActivity.LOGIN_STATE_BUNDLE_KEY, Context.MODE_PRIVATE)
//                .getInt(ChangeInfoActivity.LOGIN_STATE_BUNDLE_KEY,0)) {
//            case 0:
//                actionBarBulder.icon(R.drawable.ic_account_circle_white_24dp);
//                actionBarBulder.menu(R.menu.);
//                actionBarBulder.homeBehavior(new OpenLoginActivityBehavior(this.activity));
//                actionBarBulder.exitBehavior(new ExitApplicationBehavior(this.activity));
//                break;
//            default:
//                actionBarBulder.menu(R.menu.menu_main);
//                actionBarBulder.icon(R.drawable.ic_menu_white_24dp);
//                actionBarBulder.homeBehavior(new OpenMainMenuBehavior(this.activity));
//                actionBarBulder.exitBehavior(new LogoutBehavior(this.activity));
//        }

        this.actionBar = actionBarBulder.build();
        this.actionBar.setActionBar();
    }
}
