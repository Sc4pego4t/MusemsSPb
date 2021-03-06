package com.nixbyte.project.activities.autorization;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.nixbyte.project.R;
import com.nixbyte.project.activities.list.ListViewActivity;
import com.nixbyte.project.modules.actionbar.controller.ButtonBehavior;
import com.nixbyte.project.modules.actionbar.controller.buttons_behavior.BackPressed;
import com.nixbyte.project.modules.activity.AbstractActivity;
import com.nixbyte.project.modules.activity.Presenter;
import com.nixbyte.project.utils.Keywords;

public class AutorizationActivity extends AbstractActivity<AutorizationView> {

    public static final String TAG = AutorizationActivity.class.getName();

    @Override
    protected ButtonBehavior initOnBackPressedButtonBehavior() {
        return new BackPressed(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.fragment_container);
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        getSupportFragmentManager().findFragmentByTag(AutorizationFragment.LOG_TAG).
                onActivityResult(requestCode,resultCode,data);
        hideProgress();
    }

    @Override
    protected Presenter initPresenter() {
        return new AutorizationPresenter();
    }

    @Override
    protected AutorizationView initView() {
        return new AutorizationView(this,findViewById(android.R.id.content),fragmentManager);
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

