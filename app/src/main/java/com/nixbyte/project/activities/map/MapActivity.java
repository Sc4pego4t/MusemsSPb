package com.nixbyte.project.activities.map;

import android.content.Context;
import android.os.Bundle;
import androidx.annotation.Nullable;
import android.view.Menu;
import android.view.MenuItem;

import com.nixbyte.project.R;
import com.nixbyte.project.modules.actionbar.controller.ButtonBehavior;
import com.nixbyte.project.modules.actionbar.controller.buttons_behavior.BackPressed;
import com.nixbyte.project.modules.activity.AbstractActivity;
import com.nixbyte.project.modules.activity.Presenter;

/**
 * Created by scapegoat on 18/04/2018.
 */

public class MapActivity extends AbstractActivity<MapView> {
    @Override
    protected ButtonBehavior initOnBackPressedButtonBehavior() {
        return new BackPressed(this);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
    }

    public MapView getView(){
        return view;
    }
    @Override
    protected Presenter initPresenter() {
        return new MapPresenter();
    }

    @Override
    protected MapView initView() {
        return new MapView(this,findViewById(android.R.id.content),fragmentManager);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return view.actionBar.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        return  view.actionBar.onOptionsItemSelected(menuItem);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
