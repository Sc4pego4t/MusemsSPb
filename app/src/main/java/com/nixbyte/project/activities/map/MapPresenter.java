package com.nixbyte.project.activities.map;

import android.content.Intent;
import android.util.Log;

import com.nixbyte.project.modules.activity.Presenter;
import com.nixbyte.project.utils.ApiFactory;
import com.nixbyte.project.utils.creatingObservers.CreatingObservers;

/**
 * Created by scapegoat on 18/04/2018.
 */

public class MapPresenter implements Presenter<MapView> {
    MapView view;
    @Override
    public void onViewAttached(MapView view) {

        this.view = view;
        this.view.onAttach(null);
    }

    @Override
    public void onViewDetached() {
        this.view=null;
    }

    @Override
    public void onDestroyed() {

    }

    @Override
    public void startActivityForResult(Intent intent, int resposeCode) {

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

    }
}
