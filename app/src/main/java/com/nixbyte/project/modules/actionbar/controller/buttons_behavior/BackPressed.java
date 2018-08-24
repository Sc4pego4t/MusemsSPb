package com.nixbyte.project.modules.actionbar.controller.buttons_behavior;

import android.content.Intent;

import com.nixbyte.project.modules.actionbar.controller.ButtonBehavior;
import com.nixbyte.project.modules.activity.AbstractActivity;
import com.nixbyte.project.utils.store.TemporarilyStored;

/**
 * Created by scapegoat on 11/05/2018.
 */

public class BackPressed implements ButtonBehavior {
    AbstractActivity activity;
    public BackPressed(AbstractActivity activity){
        this.activity=activity;
    }
    @Override
    public void perform() {
        activity.startActivity(new Intent(activity, TemporarilyStored.getBackClass()));
    }
}
