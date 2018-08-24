package com.nixbyte.project.modules.actionbar.controller.buttons_behavior;

import android.content.Intent;

import com.nixbyte.project.activities.details.DetailsActivity;
import com.nixbyte.project.modules.actionbar.controller.ButtonBehavior;
import com.nixbyte.project.modules.activity.AbstractActivity;
import com.nixbyte.project.utils.store.TemporarilyStored;

/**
 * Created by scapegoat on 15/05/2018.
 */

public class StartDetailsActivity implements ButtonBehavior {

    AbstractActivity activity;
    Intent intent;

    public StartDetailsActivity(AbstractActivity activity){
        this.activity=activity;
    }

    @Override
    public void perform() {
        TemporarilyStored.backClass.add(activity.getClass());
        Intent intent=new Intent(activity, DetailsActivity.class);
        activity.startActivity(intent);
    }
}
