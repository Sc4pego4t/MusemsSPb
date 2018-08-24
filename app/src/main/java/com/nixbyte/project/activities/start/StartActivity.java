package com.nixbyte.project.activities.start;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.nixbyte.project.R;
import com.nixbyte.project.activities.list.ListViewActivity;
import com.nixbyte.project.modules.actionbar.controller.ButtonBehavior;
import com.nixbyte.project.modules.actionbar.controller.buttons_behavior.StartListActivity;
import com.nixbyte.project.modules.activity.AbstractActivity;
import com.nixbyte.project.modules.activity.Presenter;

public class StartActivity extends AbstractActivity<StartView> {

    public static final String TAG = StartActivity.class.getName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_start);

        super.onCreate(savedInstanceState);
    }

    public void click(View view){
        switch (view.getId()){
            case R.id.mus:
                new StartListActivity(this,getString(R.string.text1)).perform();
                break;
            case R.id.zal:
                new StartListActivity(this,getString(R.string.text2)).perform();
                break;
            case R.id.teatr:
                new StartListActivity(this,getString(R.string.text3)).perform();
                break;
        }
        startActivity(new Intent(this, ListViewActivity.class));
    }


    @Override
    protected Presenter initPresenter() {
        return new StartPresenter();
    }

    @Override
    protected StartView initView() {
        return new StartView(this,findViewById(android.R.id.content));
    }

    @Override
    protected ButtonBehavior initOnBackPressedButtonBehavior() {
        return null;
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
    public void onBackPressed() {
        startActivity(new Intent(this,ListViewActivity.class));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}

