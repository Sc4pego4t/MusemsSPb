package com.nixbyte.project.modules.actionbar.controller.buttons_behavior;

import android.content.Intent;

import com.nixbyte.project.activities.autorization.AutorizationActivity;
import com.nixbyte.project.modules.actionbar.controller.ButtonBehavior;
import com.nixbyte.project.modules.activity.AbstractActivity;
import com.nixbyte.project.utils.store.TemporarilyStored;

/**
 * Created by scapegoat on 03/05/2018.
 */

public class StartAutorization implements ButtonBehavior {

    AbstractActivity activity;
    Intent intent;
    public StartAutorization(AbstractActivity activity){
        this.activity=activity;
    }
    @Override
    public void perform() {
        TemporarilyStored.backClass.add(activity.getClass());
        Intent intent=new Intent(activity, AutorizationActivity.class);
        activity.startActivity(intent);
    }
}
