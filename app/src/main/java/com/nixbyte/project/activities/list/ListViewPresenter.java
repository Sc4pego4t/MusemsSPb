package com.nixbyte.project.activities.list;

import android.content.Intent;

import com.nixbyte.project.modules.activity.Presenter;
import com.nixbyte.project.utils.ApiFactory;
import com.nixbyte.project.utils.store.TemporarilyStored;
import com.nixbyte.project.utils.creatingObservers.CreatingObservers;

/**
 * Created by scapegoat on 18/04/2018.
 */

public class ListViewPresenter implements Presenter<MyListView> {
    MyListView view;
    @Override
    public void onViewAttached(MyListView view) {
        MyListView.recyclerView.setLayoutManager(view.manager);

        switch (TemporarilyStored.title){
            case "Музеи":
                ApiFactory.getInfo().subscribe(CreatingObservers.mapCreatingObserver(null));
                break;
            case "Выставочные залы":
                ApiFactory.getZals().subscribe(CreatingObservers.mapCreatingObserver(null));
                break;
            case "Театры":
                ApiFactory.getTeatrs().subscribe(CreatingObservers.mapCreatingObserver(null));
                break;
        }

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
