package com.nixbyte.project.modules.actionbar.controller.buttons_behavior;

import android.content.Intent;

import com.nixbyte.project.activities.registration.RegistrationActivity;
import com.nixbyte.project.activities.start.StartActivity;
import com.nixbyte.project.modules.actionbar.controller.ButtonBehavior;
import com.nixbyte.project.utils.store.TemporarilyStored;

import androidx.appcompat.app.AppCompatActivity;

public class StartMain implements ButtonBehavior {
    AppCompatActivity activity;

    public StartMain(AppCompatActivity activity) {
        this.activity = activity;
    }

    @Override
    public void perform() {
        TemporarilyStored.backClass.add(activity.getClass());
        activity.startActivity(new Intent(activity, StartActivity.class));
    }
}