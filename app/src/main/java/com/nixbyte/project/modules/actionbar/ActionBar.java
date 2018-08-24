package com.nixbyte.project.modules.actionbar;

import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.appcompat.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.nixbyte.project.R;
import com.nixbyte.project.modules.actionbar.controller.ButtonAction;
import com.nixbyte.project.modules.actionbar.controller.ButtonBehavior;
import com.nixbyte.project.utils.App;


/**
 * Created by nixbyte on 24.01.17.
 */

public abstract class ActionBar {

    protected static Toolbar toolbar;
    protected static AppBarLayout appbarLayout;
    private static final String TAG = ActionBar.class.getName();

    protected ButtonAction homeButtonAction;
    protected ButtonAction contextButtonAction;
    protected CoordinatorLayout.Behavior logoBehavior;
    protected ButtonAction mapButtonAction;
    protected ButtonAction autorizationButtonAction;
    protected ButtonAction exitButtonAction;
    protected ButtonAction changeInfoButtonAction;

    public ActionBar(View rootView) {
        homeButtonAction = new ButtonAction();
        mapButtonAction=new ButtonAction();
        contextButtonAction = new ButtonAction();
        autorizationButtonAction=new ButtonAction();
        changeInfoButtonAction=new ButtonAction();
        exitButtonAction=new ButtonAction();
        appbarLayout = rootView.findViewById(R.id.appbar);
        toolbar = rootView.findViewById(R.id.action_bar);
    }

    public abstract void setActionBar();
    public abstract boolean onCreateOptionsMenu(Menu menu);

    public void setHomeButtonBehavior(ButtonBehavior behavior) {
        homeButtonAction.setBehavior(behavior);
    }
    public void setMapButtonBehavior(ButtonBehavior behavior){
        mapButtonAction.setBehavior(behavior);
    }
    public void setExitButtonAction(ButtonBehavior behavior){
        exitButtonAction.setBehavior(behavior);
    }
    public void setinfoButtonBehavior(ButtonBehavior behavior){
        changeInfoButtonAction.setBehavior(behavior);
    }
    public void setAutorizationButtonAction(ButtonBehavior behavior){
        autorizationButtonAction.setBehavior(behavior);
    }

    public void setLogoBehavior(CoordinatorLayout.Behavior logoBehavior) {
        this.logoBehavior = logoBehavior;
    }

    public void setContextButtonBehavior(ButtonBehavior behavior) {
        contextButtonAction.setBehavior(behavior);
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case android.R.id.home:
                homeButtonAction.performAction();
                break;
            case R.id.map:
                mapButtonAction.performAction();
                break;
            case R.id.exit:
                exitButtonAction.performAction();
                break;
            case R.id.login:
                autorizationButtonAction.performAction();
                break;
            case R.id.info:
                changeInfoButtonAction.performAction();
                break;
            default:
                return true;
        }
        return true;
    }
}
