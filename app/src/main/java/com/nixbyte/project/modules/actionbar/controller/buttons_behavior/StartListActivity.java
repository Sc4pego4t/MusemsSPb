package com.nixbyte.project.modules.actionbar.controller.buttons_behavior;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;

import com.nixbyte.project.activities.changeInfo.ChangeInfoActivity;
import com.nixbyte.project.modules.actionbar.controller.ButtonBehavior;
import com.nixbyte.project.utils.store.TemporarilyStored;

/**
 * Created by scapegoat on 11/05/2018.
 */

public class StartListActivity implements ButtonBehavior {
    AppCompatActivity activity;

    public StartListActivity(AppCompatActivity activity,String title) {
        this.activity = activity;
        TemporarilyStored.title=title;
    }

    @Override
    public void perform() {
        TemporarilyStored.backClass.add(activity.getClass());
        activity.startActivity(new Intent(activity, ChangeInfoActivity.class));
    }
}