package com.nixbyte.project.activities.details;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.IntentSender;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;
import androidx.core.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.ResolvableApiException;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationAvailability;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResponse;
import com.google.android.gms.location.LocationSettingsResult;
import com.google.android.gms.location.LocationSettingsStates;
import com.google.android.gms.location.LocationSettingsStatusCodes;
import com.google.android.gms.location.SettingsClient;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.nixbyte.project.activities.start.StartActivity;
import com.nixbyte.project.activities.start.StartAdapter;
import com.nixbyte.project.modules.actionbar.controller.buttons_behavior.OpenMap;
import com.nixbyte.project.modules.actionbar.controller.buttons_behavior.StartMain;
import com.nixbyte.project.modules.activity.AbstractActivity;
import com.nixbyte.project.modules.activity.Presenter;
import com.nixbyte.project.utils.ApiFactory;
import com.nixbyte.project.utils.creatingObservers.CreatingObservers;
import com.nixbyte.project.utils.store.TemporarilyStored;
import com.squareup.picasso.Picasso;

import java.lang.reflect.Array;
import java.util.concurrent.Executor;

import static android.content.Context.LOCATION_SERVICE;

/**
 * Created by nixbyte on 26.01.17.
 */

public class DetailsPresenter implements Presenter<DetailsView>{

    private final String OBJECT_SEARCH_RADIUS="50";
    private final String OBJECT_TYPE_MUSEUM="museum";
    private DetailsView view;
    private LocationManager locationManager;

    @Override
    public void onViewAttached(DetailsView view) {
        view.recyclerView.setLayoutManager(view.manager);
        ApiFactory.getPhoto(TemporarilyStored.row.getCoord_shirota()
                +","
                +TemporarilyStored.row.getCoord_dolgota()
                ,OBJECT_SEARCH_RADIUS
                ,OBJECT_TYPE_MUSEUM
                ).subscribe(
                        CreatingObservers.setPhoto(view)
        );

        view.adapter = new DetailsAdapter();
        view.recyclerView.setAdapter(view.adapter);

        locationManager = (LocationManager) view.activity.getSystemService(LOCATION_SERVICE);
        view.fab.setOnClickListener(click());
        this.view = view;
        this.view.onAttach(null);// Added authorization token object
    }

    View.OnClickListener click(){
        return e-> {
            if (ActivityCompat.checkSelfPermission(view.context, Manifest.permission.ACCESS_FINE_LOCATION)
                    != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(view.activity
                        , new String[]{Manifest.permission.ACCESS_FINE_LOCATION}
                        , 666);
                return;
            }
            createLocationRequest();
        };
    }

    @SuppressLint("MissingPermission")
    private void getLocationAndStartMapActivity(){
        FusedLocationProviderClient mFusedLocationClient = LocationServices.getFusedLocationProviderClient(view.activity);
        mFusedLocationClient.getLastLocation()
                .addOnSuccessListener((location)-> {
                    // Got last known location. In some rare situations this can be null.
                    if(location==null) {
                        Log.e("er","restarrt");
                        //пока не обновятся настройки перезапускаем функцию
                        getLocationAndStartMapActivity();
                    } else {
                        AbstractActivity.hideProgress();
                        Toast.makeText(view.context,location.getLongitude()+"/"+location.getLatitude(),Toast.LENGTH_LONG).show();
                        Log.e("gg",location.getLongitude()+"/"+location.getLatitude());
                        TemporarilyStored.currentLocation=new LatLng(location.getLatitude(),
                                location.getLongitude());
                        TemporarilyStored.nameObjectForRoute=TemporarilyStored.row.getName();
                        new OpenMap(view.activity).perform();
                    }
                });
    }

    private void createLocationRequest() {

        SettingsClient client = LocationServices.getSettingsClient(view.activity);

        LocationRequest request=LocationRequest.create().setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);

        LocationSettingsRequest.Builder builder = new LocationSettingsRequest.Builder()
                .addLocationRequest(request);

        Task<LocationSettingsResponse> task = client.checkLocationSettings(builder.build());

        task.addOnSuccessListener(view.activity, locationSettingsResponse-> {
            getLocationAndStartMapActivity();

            Log.e("gg",locationSettingsResponse.getLocationSettingsStates().toString());
        });

        task.addOnFailureListener( exception-> {
                if (exception instanceof ResolvableApiException) {
                    // Location settings are not satisfied, but this can be fixed
                    // by showing the user a dialog.
                    try {
                        // Show the dialog by calling startResolutionForResult(),
                        // and check the result in onActivityResult().
                        ResolvableApiException resolvable = (ResolvableApiException) exception;
                        resolvable.startResolutionForResult(view.activity,
                                DetailsActivity.REQUEST_CHECK_SETTINGS);
                    } catch (IntentSender.SendIntentException sendEx) {
                        // Ignore the error.
                    }
                }
        });
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
        switch (requestCode) {
            case DetailsActivity.REQUEST_CHECK_SETTINGS:
                switch (resultCode) {
                    case Activity.RESULT_OK:
                        AbstractActivity.showProgress();
                        getLocationAndStartMapActivity();
                        Log.e("gg","YRA");
                        break;
                    case Activity.RESULT_CANCELED:
                        Toast.makeText(view.context,":(",Toast.LENGTH_LONG).show();
                        break;
                    default:
                        break;
                }
                break;
        }
    }
}
