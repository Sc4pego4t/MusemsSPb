package com.nixbyte.project.activities.changeInfo;

import android.content.Intent;

import com.nixbyte.project.modules.activity.Presenter;

/**
 * Created by nixbyte on 26.01.17.
 */

public class ChangeInfoPresenter implements Presenter<ChangeInfoView> {

    private ChangeInfoView view;

    @Override
    public void onViewAttached(ChangeInfoView view) {

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
