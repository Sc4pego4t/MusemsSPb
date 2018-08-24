package com.nixbyte.project.modules.actionbar;
import android.content.Intent;
import android.content.SharedPreferences;
import androidx.appcompat.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.nixbyte.project.R;
import com.nixbyte.project.activities.list.ListViewActivity;
import com.nixbyte.project.modules.actionbar.controller.ButtonBehavior;
import com.nixbyte.project.modules.actionbar.controller.buttons_behavior.BackPressed;
import com.nixbyte.project.modules.actionbar.controller.buttons_behavior.FinishActivity;
import com.nixbyte.project.modules.actionbar.controller.buttons_behavior.Logout;
import com.nixbyte.project.modules.actionbar.controller.buttons_behavior.OpenMap;
import com.nixbyte.project.modules.actionbar.controller.buttons_behavior.StartAutorization;
import com.nixbyte.project.modules.actionbar.controller.buttons_behavior.StartInfoWindow;
import com.nixbyte.project.modules.activity.AbstractActivity;
import com.nixbyte.project.utils.App;
import com.nixbyte.project.utils.Configurations;

import static com.nixbyte.project.utils.App.getContext;

/**
 * Created by nixbyte on 26.01.17.
 */

public class ActionBarConstructor extends ActionBar {
    private static final String TAG = ActionBarConstructor.class.getName();

    String title;
    int titleColor;
    int menuId;
    int icon;
    boolean setIcon;
    int logo;
    int appbarBackgroundColor;
    int appbarBackgroundImage;
    int toolbarHeight;
    int appbarHeight;

    AppCompatActivity activity;
    ButtonBehavior buttonBehavior;
    ButtonBehavior buttonMapBehavior;
    private ActionBarConstructor(AppCompatActivity activity
            ,View rootView
            ,String title
            ,int titleColor
            ,int icon
            ,boolean setIcon
            ,int logo
            ,int menuId
            ,int toolbarHeight
            ,int appbarHeight
            ,int appbarBackgroundColor
            ,int appbarBackgroundImage
            ,ButtonBehavior buttonBehavior
            ,ButtonBehavior buttonMapBehavior
            , ButtonBehavior buttonInfoBehavior
            ,ButtonBehavior buttonAutorizeBehavior
            ,ButtonBehavior buttonExitBehavior) {
        super(rootView);


        this.buttonBehavior = buttonBehavior;
        this.buttonMapBehavior=buttonMapBehavior;
        this.title = title;
        this.titleColor = titleColor;
        this.icon = icon;
        this.setIcon=setIcon;
        this.logo = logo;
        this.menuId = menuId;
        this.toolbarHeight = toolbarHeight;
        this.appbarHeight = appbarHeight;
        this.appbarBackgroundColor = appbarBackgroundColor;
        this.appbarBackgroundImage = appbarBackgroundImage;
        this.activity = activity;
        setHomeButtonBehavior(buttonBehavior);
        setMapButtonBehavior(buttonMapBehavior);
        setinfoButtonBehavior(buttonInfoBehavior);
        setAutorizationButtonAction(buttonAutorizeBehavior);
        setExitButtonAction(buttonExitBehavior);

    }

    @Override
    public void setActionBar() {
        appbarLayout.getLayoutParams().height = appbarHeight;
        appbarLayout.setBackgroundResource(appbarBackgroundColor);
        toolbar.getLayoutParams().height = toolbarHeight;
        if(setIcon) {
            toolbar.setNavigationIcon(icon);
        }
        toolbar.setLogo(logo);
        toolbar.setTitle(title);
        toolbar.setTitleTextColor(activity.getResources().getColor(titleColor));
        activity.setSupportActionBar(toolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        activity.getMenuInflater().inflate(menuId,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            default:
                super.onOptionsItemSelected(menuItem);
        }
        return true;
    }

    public static class Builder {

        AppCompatActivity activity;
        View rootView;
        String title;
        int titleColor;
        int menuId;
        int icon;
        boolean setIcon;
        int logo;
        int toolbarHeight;
        int appbarHeight;
        int appbarBackgroundColor;
        int appbarBackgroundImage;
        ButtonBehavior homeBehavior;
        ButtonBehavior mapBehavior;
        ButtonBehavior infoBehavior;
        ButtonBehavior autorizeBehavior;
        ButtonBehavior exitBehavior;


        public Builder(AbstractActivity activity){
            this.activity = activity;
            this.rootView = activity.getWindow().getDecorView().getRootView();
            this.title = activity.getString(R.string.app_name);
            this.titleColor = R.color.colorAccent;
            this.icon = R.mipmap.arrow_back;
            this.setIcon=true;

            if(activity.autorized.equals("")){
                this.menuId=R.menu.menu;
            } else {
                this.menuId=R.menu.menu_autorized;
            }

            this.logo = android.R.color.transparent;
            this.appbarBackgroundColor = R.color.colorPrimary;
            this.appbarHeight = (int)rootView.getResources().getDimension(R.dimen.action_bar_size);
            this.toolbarHeight = (int)rootView.getResources().getDimension(R.dimen.action_bar_size);
            this.appbarBackgroundImage = android.R.color.transparent;
            this.homeBehavior = new BackPressed(activity);
            this.mapBehavior=null;
            this.infoBehavior=new StartInfoWindow(activity);
            this.autorizeBehavior=new StartAutorization(activity);
            this.exitBehavior=new Logout(activity);
        }

        public Builder title(int title){
            this.title = activity.getString(title);
            return this;
        }
        public  Builder titleFromString(String title){
            this.title=title;
            return this;
        }

        public Builder titleColor(int titleColor){
            this.titleColor = titleColor;
            return this;
        }
        public Builder setIcon(boolean setIcon){
            this.setIcon=setIcon;
            return this;
        }
        public Builder menu(int menuId) {
            this.menuId = menuId;
            return this;
        }

        public Builder homeBehavior(ButtonBehavior behavior) {
            this.homeBehavior = behavior;
            return this;
        }
        public Builder mapBehavior(ButtonBehavior behavior) {
            this.mapBehavior = behavior;
            return this;
        }


        public Builder icon(int icon) {
            this.icon = icon;
            return this;
        }

        public Builder toolbarHeight(int dp) {
            this.toolbarHeight = Configurations.dpToPx(dp);
            return this;
        }

        public Builder appbarHeight(int dp) {
            this.appbarHeight = Configurations.dpToPx(dp);
            return this;
        }

        public Builder appbarBackgroundColor(int appbarBackground) {
            this.appbarBackgroundColor = appbarBackground;
            return this;
        }

        public Builder appbarBackgroundImage(int appbarBackgroundImage) {
            this.appbarBackgroundImage = appbarBackgroundImage;
            return this;
        }

        public Builder logo(int logo) {
            this.logo = logo;
            return this;
        }

        public ActionBarConstructor build(){
            return new ActionBarConstructor(activity
                    ,rootView
                    ,title
                    ,titleColor
                    ,icon
                    ,setIcon
                    ,logo
                    ,menuId
                    ,toolbarHeight
                    ,appbarHeight
                    ,appbarBackgroundColor
                    ,appbarBackgroundImage
                    ,homeBehavior
                    ,mapBehavior
                    ,infoBehavior
                    ,autorizeBehavior
                    ,exitBehavior);
        }
    }
}

