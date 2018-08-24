package com.nixbyte.project.modules.actionbar.controller.buttons_behavior;

import android.content.Intent;

import com.nixbyte.project.activities.map.MapActivity;
import com.nixbyte.project.modules.actionbar.controller.ButtonBehavior;
import com.nixbyte.project.modules.activity.AbstractActivity;
import com.nixbyte.project.utils.store.TemporarilyStored;

/**
 * Created by scapegoat on 02/05/2018.
 */

public class OpenMap implements ButtonBehavior {

    AbstractActivity activity;
    Intent intent;
    public OpenMap(AbstractActivity activity){
        this.activity=activity;
        intent=new Intent(activity.getBaseContext(), MapActivity.class);
    }

    @Override
    public void perform() {
        TemporarilyStored.backClass.add(activity.getClass());
        activity.startActivity(intent);
    }
}
