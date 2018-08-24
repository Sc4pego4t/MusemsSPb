package com.nixbyte.project.utils;

import android.app.Activity;
import android.content.Context;
import android.util.DisplayMetrics;
import android.view.inputmethod.InputMethodManager;

/**
 * Created by nixbyte on 07.09.16.
 */
public class Configurations {
    public static void hideKeyBoard(Activity activity) {
        if (activity.getWindow().getCurrentFocus() != null) {
            InputMethodManager imm = (InputMethodManager) activity.getSystemService(
                    Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(activity.getWindow().getCurrentFocus().getWindowToken(), 0);
        }
    }

    public static int dpToPx(int dp) {
        DisplayMetrics displayMetrics = App.getContext().getResources().getDisplayMetrics();
        return Math.round(dp * (displayMetrics.densityDpi / DisplayMetrics.DENSITY_DEFAULT));
    }

    public static int pxToDp(int px) {
        DisplayMetrics displayMetrics = App.getContext().getResources().getDisplayMetrics();
        return Math.round(px / (displayMetrics.densityDpi/ DisplayMetrics.DENSITY_DEFAULT));
    }

}
