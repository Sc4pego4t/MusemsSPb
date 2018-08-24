package com.nixbyte.project.utils.store;

import com.google.android.gms.maps.model.LatLng;
import com.nixbyte.project.utils.responseType.Row;

import java.util.ArrayList;

/**
 * Created by scapegoat on 11/05/2018.
 */

public class TemporarilyStored {
    static {
        backClass=new ArrayList<>();
    }

    public static ArrayList<Class> backClass;
    public static String title;

    public static Class getBackClass(){
        Class returnClass=TemporarilyStored.backClass.get(TemporarilyStored.backClass.size()-1);
        TemporarilyStored.backClass.remove(TemporarilyStored.backClass.size()-1);
        return returnClass;
    }

    public static Row row;
    public static LatLng currentLocation;

    public static String nameObjectForRoute;
}
