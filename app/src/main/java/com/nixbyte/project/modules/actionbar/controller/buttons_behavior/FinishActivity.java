package com.nixbyte.project.modules.actionbar.controller.buttons_behavior;

import androidx.appcompat.app.AppCompatActivity;
import android.util.Log;

import com.nixbyte.project.modules.actionbar.controller.ButtonBehavior;


/**
 * Created by nixbyte on 31.01.17.
 */

public class FinishActivity implements ButtonBehavior {
    private AppCompatActivity activity;

    public FinishActivity(AppCompatActivity activity) {
        this.activity = activity;
    }
    @Override
    public void perform() {
        android.os.Process.killProcess(android.os.Process.myPid());
        Log.e("gg","fin");
    }
}
