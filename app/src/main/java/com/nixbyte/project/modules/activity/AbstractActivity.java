package com.nixbyte.project.modules.activity;

import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.appcompat.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.nixbyte.project.R;
import com.nixbyte.project.modules.actionbar.ActionBarConstructor;
import com.nixbyte.project.modules.actionbar.controller.ButtonBehavior;
import com.nixbyte.project.utils.App;


/**
 * Created by nixbyte on 26.01.17.
 */


public abstract class AbstractActivity<V> extends AppCompatActivity {

    protected FragmentManager fragmentManager;
    protected Presenter presenter;
    protected V view;
    protected ButtonBehavior backPressedBehavior;


    protected abstract Presenter initPresenter();
    protected abstract V initView();
    protected abstract ButtonBehavior initOnBackPressedButtonBehavior();
    public String autorized;
    public static Context context;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context=this;
        autorized=getApplicationContext().getSharedPreferences(getString(R.string.pref),0).
                getString(getString(R.string.keyid),"");
        fragmentManager = getSupportFragmentManager();
    }

    @Override
    protected void onStart() {
        super.onStart();
        view = initView();
        presenter = initPresenter();
        backPressedBehavior=initOnBackPressedButtonBehavior();
        presenter.onViewAttached(view);
    }


    @Override
    protected void onStop() {
        presenter.onViewDetached();
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        presenter.onDestroyed();
        super.onDestroy();
    }


    @Override
    public void onBackPressed() {
        backPressedBehavior.perform();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        //presenter.onActivityResult(requestCode,resultCode,data);
        //super.onActivityResult(requestCode, resultCode, data);
    }
    private static ProgressDialog pd;

    public static void showProgress() {
        pd = new ProgressDialog(context, getStyle());
        pd.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        pd.setMessage("Загрузка...");
        pd.setCancelable(false);
        pd.setCanceledOnTouchOutside(false);
        pd.show();

        //после какого то промежутка времени отменяем задание и выключаем
        //прогресс диалог

    }

    public static class MyThread extends Thread {
        public void run() {
            Log.d("gg", "Mой поток запущен...");
            try {
                Log.e("gg","sleep");
                Thread.currentThread().sleep(5000);
                Log.e("gg","awake");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            hideProgress();

        }
    }

    public static int getStyle() {
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.KITKAT) {
            return R.style.AppCompatAlertDialogStyle;
        } else {
            return R.style.AppCompatAlertDialogStyleForOldDevices;
        }
    }
    public static void hideProgress() {
        pd.dismiss();
    }
}
