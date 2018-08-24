package com.nixbyte.project.activities.changeInfo;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.nixbyte.project.R;
import com.nixbyte.project.activities.autorization.AutorizationFragment;
import com.nixbyte.project.activities.list.ListViewActivity;
import com.nixbyte.project.modules.actionbar.controller.ButtonBehavior;
import com.nixbyte.project.modules.actionbar.controller.buttons_behavior.BackPressed;
import com.nixbyte.project.modules.activity.AbstractActivity;
import com.nixbyte.project.modules.activity.Presenter;

public class ChangeInfoActivity extends AbstractActivity<ChangeInfoView> {

    public static final String TAG = ChangeInfoActivity.class.getName();

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
        getSupportFragmentManager().findFragmentByTag(ChangeInfoFragment.CHANGE_TAG).
                onActivityResult(requestCode,resultCode,data);
        hideProgress();
    }


    @Override
    protected Presenter initPresenter() {
        return new ChangeInfoPresenter();
    }

    @Override
    protected ChangeInfoView initView() {
        return new ChangeInfoView(this,findViewById(android.R.id.content),fragmentManager);
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

