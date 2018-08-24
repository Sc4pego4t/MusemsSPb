package com.nixbyte.project.activities.start;

import android.content.Intent;
import androidx.recyclerview.widget.LinearLayoutManager;
import android.widget.TextView;

import com.nixbyte.project.R;
import com.nixbyte.project.modules.activity.Presenter;

/**
 * Created by nixbyte on 26.01.17.
 */

public class StartPresenter implements Presenter<StartView> {

    private StartView view;

    @Override
    public void onViewAttached(StartView view) {
        view.recyclerView.setLayoutManager(view.manager);

        view.adapter=new StartAdapter();
        view.recyclerView.setAdapter(view.adapter);


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
