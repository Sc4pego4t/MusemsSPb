package com.nixbyte.project.activities.details;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.gms.location.LocationSettingsStates;
import com.nixbyte.project.R;
import com.nixbyte.project.activities.list.ListViewActivity;
import com.nixbyte.project.modules.actionbar.controller.ButtonBehavior;
import com.nixbyte.project.modules.actionbar.controller.buttons_behavior.BackPressed;
import com.nixbyte.project.modules.activity.AbstractActivity;
import com.nixbyte.project.modules.activity.Presenter;

public class DetailsActivity extends AbstractActivity<DetailsView> {

    public static final int REQUEST_CHECK_SETTINGS=0xff;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_object_details);
        super.onCreate(savedInstanceState);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        Log.e("gg","selected");
        switch (menuItem.getItemId()){
            case android.R.id.home:
                new BackPressed(this).perform();
        }
        return super.onOptionsItemSelected(menuItem);
    }

    @Override
    protected Presenter initPresenter() {
        return new DetailsPresenter();
    }

    @Override
    protected DetailsView initView() {
        return new DetailsView(this,findViewById(android.R.id.content));
    }

    @Override
    protected ButtonBehavior initOnBackPressedButtonBehavior() {
        return new BackPressed(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        presenter.onActivityResult(requestCode,resultCode,data);
    }
}

