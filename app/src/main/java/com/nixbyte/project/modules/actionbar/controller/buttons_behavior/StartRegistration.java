package com.nixbyte.project.modules.actionbar.controller.buttons_behavior;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;

import com.nixbyte.project.activities.changeInfo.ChangeInfoActivity;
import com.nixbyte.project.activities.registration.RegistrationActivity;
import com.nixbyte.project.modules.actionbar.controller.ButtonBehavior;
import com.nixbyte.project.utils.store.TemporarilyStored;

/**
 * Created by scapegoat on 11/05/2018.
 */

public class StartRegistration implements ButtonBehavior {
    AppCompatActivity activity;

    public StartRegistration(AppCompatActivity activity) {
        this.activity = activity;
    }

    @Override
    public void perform() {
        TemporarilyStored.backClass.add(activity.getClass());
        activity.startActivity(new Intent(activity, RegistrationActivity.class));
    }
}
