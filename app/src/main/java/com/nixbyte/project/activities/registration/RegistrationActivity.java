package com.nixbyte.project.activities.registration;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.nixbyte.project.R;
import com.nixbyte.project.activities.list.ListViewActivity;
import com.nixbyte.project.modules.actionbar.controller.ButtonBehavior;
import com.nixbyte.project.modules.actionbar.controller.buttons_behavior.BackPressed;
import com.nixbyte.project.modules.activity.AbstractActivity;
import com.nixbyte.project.modules.activity.Presenter;
import com.nixbyte.project.utils.ApiFactory;
import com.nixbyte.project.utils.creatingObservers.CreatingObservers;

public class RegistrationActivity extends AbstractActivity<RegistrationView> {

    public Class backClass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.fragment_container);
        super.onCreate(savedInstanceState);
    }

    @Override
    protected ButtonBehavior initOnBackPressedButtonBehavior() {
        return new BackPressed(this);
    }

    public void click(View v){
        String email=view.email.getText().toString();
        String password=view.password.getText().toString();
        Log.e("waaaat",email+"/"+password);
        ApiFactory.register(email,password).subscribe(
                CreatingObservers.registrationSuccessObserver(
                        this,
                        null,
                        "Данный email адрес уже используется",
                        false)
        );
    }

    @Override
    protected Presenter initPresenter() {
        return new RegistrationPresenter();
    }

    @Override
    protected RegistrationView initView() {
        return new RegistrationView(this,findViewById(android.R.id.content));
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

