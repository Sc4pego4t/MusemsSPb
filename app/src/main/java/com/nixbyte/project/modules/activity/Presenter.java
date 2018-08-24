package com.nixbyte.project.modules.activity;

import android.content.Intent;

/**
 * Created by nixbyte on 26.01.17.
 */

public interface Presenter<V> {
    void onViewAttached(V view);
    void onViewDetached();
    void onDestroyed();
    void startActivityForResult(Intent intent, int resposeCode);
    void onActivityResult(int requestCode, int resultCode, Intent data);
}
