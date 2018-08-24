package com.nixbyte.project.activities.autorization;

import android.content.Intent;

import com.nixbyte.project.R;
import com.nixbyte.project.activities.start.StartAdapter;
import com.nixbyte.project.modules.activity.Presenter;

/**
 * Created by nixbyte on 26.01.17.
 */

public class AutorizationPresenter implements Presenter<AutorizationView> {

    private AutorizationView view;

    @Override
    public void onViewAttached(AutorizationView view) {


        this.view = view;
        this.view.onAttach(null);// Added authorization token object
    }

    @Override
    public void onViewDetached() {
        this.view = null;
    }

    @Override
    public void onDestroyed() {

    }

    @Override
    public void startActivityForResult(Intent intent, int requestCode) {

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

    }
}
