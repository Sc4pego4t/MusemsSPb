package com.nixbyte.project.activities.map;

import androidx.fragment.app.FragmentManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.appolica.interactiveinfowindow.InfoWindow;
import com.appolica.interactiveinfowindow.InfoWindowManager;
import com.appolica.interactiveinfowindow.fragment.MapInfoWindowFragment;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.nixbyte.project.R;
import com.nixbyte.project.modules.actionbar.ActionBarConstructor;
import com.nixbyte.project.modules.activity.AbstractActivity;
import com.nixbyte.project.modules.activity.Viewable;
import com.nixbyte.project.utils.ApiFactory;
import com.nixbyte.project.utils.App;
import com.nixbyte.project.utils.store.TemporarilyStored;
import com.nixbyte.project.utils.creatingObservers.CreatingObservers;


/**
 * Created by scapegoat on 18/04/2018.
 */

public class MapView implements Viewable<String>, OnMapReadyCallback, GoogleMap.OnMarkerClickListener {

    ActionBarConstructor.Builder builder;
    ActionBarConstructor actionBar;

    public Marker mr;
    private GoogleMap mMap;
    private com.google.android.gms.maps.MapView map;
    InfoWindowManager infoWindowManager;

    public String likeOrDis="";
    public TextView rate;
    public Button like,dislike;


    public MapView(AbstractActivity activity, View rootView, FragmentManager manager) {
        builder = new ActionBarConstructor.Builder(activity)
                .titleFromString(TemporarilyStored.title);

        MapInfoWindowFragment mapInfoWindowFragment = (MapInfoWindowFragment) activity.
                getSupportFragmentManager().findFragmentById(R.id.infoWindowMap);

        infoWindowManager = mapInfoWindowFragment.infoWindowManager();
        infoWindowManager.setHideOnFling(true);
        mapInfoWindowFragment.getMapAsync(this);
        infoWindowManager.setHideOnFling(true);
    }

    @Override
    public void onAttach(String data) {
        this.actionBar=builder.build();
        this.actionBar.setActionBar();
    }

    @Override
    public boolean onMarkerClick(Marker marker) {
        InfoWindow infoWindow;
        InfoWindow.MarkerSpecification spec=new InfoWindow.MarkerSpecification(0,0);
        mr=marker;
        if(!marker.getTitle().equals(App.getContext().getString(R.string.currentLocation))){
            infoWindow = new InfoWindow(marker,spec,new MarkerFragment());
            infoWindowManager.toggle(infoWindow,true);
        }

        return true;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        if(TemporarilyStored.nameObjectForRoute!=null){
            TemporarilyStored.nameObjectForRoute=null;
            Log.e("default","------------------");
            Log.e("default",TemporarilyStored.currentLocation.latitude+"");
            Log.e("default",TemporarilyStored.currentLocation.longitude+"");
            Log.e("default",TemporarilyStored.row.getCoord_shirota()+"");
            Log.e("default",TemporarilyStored.row.getCoord_dolgota()+"");

            ApiFactory.getRoute(TemporarilyStored.currentLocation.latitude+","+
                            TemporarilyStored.currentLocation.longitude,
                            TemporarilyStored.row.getCoord_shirota()+","+
                            TemporarilyStored.row.getCoord_dolgota()).subscribe(
                    CreatingObservers.createRouteOnMap(mMap)
            );
        } else {
            switch (TemporarilyStored.title) {
                case "Музеи":
                    ApiFactory.getInfo().subscribe(CreatingObservers.mapCreatingObserver(mMap));
                    break;
                case "Выставочные залы":
                    ApiFactory.getZals().subscribe(CreatingObservers.mapCreatingObserver(mMap));
                    break;
                case "Театры":
                    ApiFactory.getTeatrs().subscribe(CreatingObservers.mapCreatingObserver(mMap));
                    break;

            }
        }
        // Add a marker in Sydney and move the camera
        LatLng spb = new LatLng(59.9342802,30.3350986);
        CameraPosition position=new CameraPosition.Builder().target(spb).tilt(30).zoom(10).build();

        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(position));
        mMap.setOnMarkerClickListener(this);
    }
}
