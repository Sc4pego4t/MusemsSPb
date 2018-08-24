package com.nixbyte.project.modules.actionbar.controller.buttons_behavior;

import com.nixbyte.project.R;
import com.nixbyte.project.modules.actionbar.controller.ButtonBehavior;
import com.nixbyte.project.modules.activity.AbstractActivity;
import com.nixbyte.project.utils.Keywords;

/**
 * Created by scapegoat on 04/05/2018.
 */

public class Logout implements ButtonBehavior {
    AbstractActivity activity;
    public Logout(AbstractActivity activity){
        this.activity=activity;
    }
    @Override
    public void perform() {
        activity.getSharedPreferences(activity.getString(R.string.pref),0).edit().clear().apply();
        activity.recreate();
    }
}
