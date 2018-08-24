package com.nixbyte.project.utils.creatingObservers;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.util.Log;
import android.view.View;
import android.view.ViewDebug;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.maps.android.PolyUtil;
import com.nixbyte.project.R;
import com.nixbyte.project.activities.autorization.AutorizationActivity;
import com.nixbyte.project.activities.changeInfo.ChangeInfoActivity;
import com.nixbyte.project.activities.changeInfo.ChangeInfoFragment;
import com.nixbyte.project.activities.details.DetailsView;
import com.nixbyte.project.activities.list.ListAdapter;
import com.nixbyte.project.activities.list.MyListView;
import com.nixbyte.project.activities.map.CommentsAdapter;
import com.nixbyte.project.activities.map.MapActivity;
import com.nixbyte.project.activities.map.MapView;
import com.nixbyte.project.activities.registration.RegistrationActivity;
import com.nixbyte.project.modules.activity.AbstractActivity;
import com.nixbyte.project.utils.ApiFactory;
import com.nixbyte.project.utils.App;
import com.nixbyte.project.utils.Keywords;
import com.nixbyte.project.utils.responseType.Row;
import com.nixbyte.project.utils.store.TemporarilyStored;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import androidx.recyclerview.widget.RecyclerView;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;


/**
 * Created by scapegoat on 04/04/2018.
 */

public class CreatingObservers {
    static public Observer<List<String>> userInfoObserver(ChangeInfoActivity activity, ChangeInfoFragment fragment, String onSuccess, String onFailed) {
        return new Observer<List<String>>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(List<String> result) {
                fragment.email.setText(result.get(0));
                fragment.password.setText(result.get(1));
            }

            @Override
            public void onError(Throwable e) {
                AbstractActivity.hideProgress();
            }

            @Override
            public void onComplete() {
                AbstractActivity.hideProgress();
            }
        };
    }
    static public Observer<Integer> rateObserver(MapActivity activity, MapView view, String onSuccess, String onFailed, boolean showToastOnSuccess) {
        return new Observer<Integer>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Integer result) {
                try {
                    if(!view.likeOrDis.equals("")){
                        view.rate.setText(result+"");
                        if(view.likeOrDis.equals("1")){
                            view.like.setBackground(activity.getResources().getDrawable(R.drawable.like_pressed));
                            view.dislike.setBackground(activity.getResources().getDrawable(R.drawable.dislike));
                        } else {
                            view.dislike.setBackground(activity.getResources().getDrawable(R.drawable.dislike_pressed));
                            view.like.setBackground(activity.getResources().getDrawable(R.drawable.like));
                        }
                    }

                    view.likeOrDis="";

                } catch (Exception e){ Log.e("GG",e.toString());}
                    //если лайкаем или дизлайкаем

            }

            @Override
            public void onError(Throwable e) {
                Toast.makeText(activity, onFailed, Toast.LENGTH_LONG).show();
                AbstractActivity.hideProgress();
            }

            @Override
            public void onComplete() {
                if(showToastOnSuccess)
                    Toast.makeText(activity, onSuccess, Toast.LENGTH_LONG).show();
                AbstractActivity.hideProgress();

            }
        };
    }
    static public Observer<Integer> loginSuccessObserver(AutorizationActivity activity, String onSuccess, String onFailed, boolean showToastOnSuccess) {
        return new Observer<Integer>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Integer result) {
                Log.e("gg","mde"+activity);
                SharedPreferences preferences=activity.getSharedPreferences(activity.getString(R.string.pref),0);
                SharedPreferences.Editor editor=preferences.edit();
                editor.putString(activity.getString(R.string.keyid),result+"");
                editor.apply();
                Log.e("gg","mde");
                activity.startActivity(new Intent(activity, TemporarilyStored.getBackClass()));
            }

            @Override
            public void onError(Throwable e) {
                Toast.makeText(activity, onFailed, Toast.LENGTH_LONG).show();
                e.printStackTrace();
                AbstractActivity.hideProgress();
            }

            @Override
            public void onComplete() {
                if(showToastOnSuccess)
                    Toast.makeText(activity, onSuccess, Toast.LENGTH_LONG).show();
                AbstractActivity.hideProgress();
                Log.e("gg","mde");
            }
        };
    }

    static public Observer<Integer> registrationSuccessObserver(AbstractActivity activity, String onSuccess, String onFailed, boolean showToastOnSuccess) {
        return new Observer<Integer>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Integer result) {
                Intent intent=new Intent(activity,AutorizationActivity.class);
                intent.putExtra(Keywords.whereToBack,RegistrationActivity.class);
                activity.startActivity(intent);
            }

            @Override
            public void onError(Throwable e) {
                Toast.makeText(activity, onFailed, Toast.LENGTH_LONG).show();
                e.printStackTrace();
                AbstractActivity.hideProgress();
                Log.e("((","))");
            }

            @Override
            public void onComplete() {
                if(showToastOnSuccess)
                    Toast.makeText(activity, onSuccess, Toast.LENGTH_LONG).show();
                AbstractActivity.hideProgress();
                Log.e("gg","mde");
            }
        };
    }
    static public Observer<Integer> changeUserInfoObserver(ChangeInfoActivity activity,String onSuccess, String onFailed, boolean showToastOnSuccess) {
        return new Observer<Integer>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Integer result) {
                //
            }

            @Override
            public void onError(Throwable e) {
                Toast.makeText(activity, onFailed, Toast.LENGTH_LONG).show();
                e.printStackTrace();
                AbstractActivity.hideProgress();
                Log.e("((","))");
            }

            @Override
            public void onComplete() {
                if(showToastOnSuccess)
                    Toast.makeText(activity, onSuccess, Toast.LENGTH_LONG).show();
                AbstractActivity.hideProgress();
                Log.e("gg","mde");
            }
        };
    }
    static public Observer<List<String>> arrayObserver(MapView view, String onSuccess, String onFailed, boolean showToastOnSuccess, RecyclerView recyclerView){
        return new Observer<List<String>>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(List<String> strings) {

                String liked=strings.get(strings.size()-1);
                String rate=strings.get(strings.size()-2);
                Log.e("gg",rate+"/"+liked);
                strings.remove(rate);
                strings.remove(liked);
                if(strings.get(0).equals("0"))
                    strings.remove(0);
                CommentsAdapter adapter=new CommentsAdapter(strings);
                recyclerView.setAdapter(adapter);
                view.rate.setText(rate);
                if(liked!=null) {
                    if (liked.equals("0")) {
                        view.dislike.setBackground(App.getContext().getResources().getDrawable(R.drawable.dislike_pressed));
                    }
                    if (liked.equals("1")) {
                        view.like.setBackground(App.getContext().getResources().getDrawable(R.drawable.like_pressed));
                    }
                }


            }

            @Override
            public void onError(Throwable e) {
                MapActivity.hideProgress();
            }

            @Override
            public void onComplete() {
                MapActivity.hideProgress();
            }
        };
    }

    static String equal(String s){
        if(s.equals(""))
            return "0"+Keywords.coupleDivider +s+" ";
        else
            return "1"+Keywords.coupleDivider+ s;
    }
    static public Observer<Row> mapCreatingObserver(GoogleMap googleMap){
        ArrayList<Row> forList=new ArrayList<>();
        return new Observer<Row>(){
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Row row) {
                try {
                    float latitude = Float.parseFloat(row.getCoord_shirota());
                    float longitude = Float.parseFloat(row.getCoord_dolgota());
                    forList.add(row);

                    try{
                        StringBuilder sb=new StringBuilder();

                        sb.append(equal(row.getDescription()));
                        sb.append(Keywords.divider);
                        sb.append(equal(row.getAddress_manual()));
                        sb.append(Keywords.divider);
                        sb.append(equal(row.getWww()));

                        Log.e("STRING",sb+"");
                        googleMap.addMarker(new MarkerOptions().position(new LatLng(latitude,longitude)).
                                title(row.getName()).snippet(
                                sb.toString()
                        ));
                    } catch (Exception e){
                        e.printStackTrace();
                    }
                }
                catch (Exception e){
                    e.printStackTrace();
                }

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {
                AbstractActivity.hideProgress();
                try {
                    MyListView.adapter=new ListAdapter(forList);
                    MyListView.recyclerView.setAdapter(MyListView.adapter);
                } catch (Exception e){
                    e.printStackTrace();
                }

            }

        };
    }

    static public Observer<String> createRouteOnMap(GoogleMap googleMap){

        return new Observer<String>(){
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(String points) {
                Log.e("gg",points);
                List<LatLng> arrayPoints;
                arrayPoints = PolyUtil.decode(points);

                LatLngBounds.Builder latLngBuilder = new LatLngBounds.Builder();

                Row row=TemporarilyStored.row;
                googleMap.addMarker(new MarkerOptions().position(arrayPoints.get(0)).title(
                        App.getContext().getString(R.string.currentLocation)));
                googleMap.addMarker(new MarkerOptions().position(arrayPoints.get(arrayPoints.size()-1))
                .title(row.getName()).snippet(row.getDescription()+"&&"+row.getAddress_manual()+"&&"+row.getWww()));

                PolylineOptions line= new PolylineOptions();
                for (LatLng latLng :arrayPoints){
                    Log.e("gg",latLng.latitude+"/"+latLng.longitude);
                    line.add(latLng);
                    latLngBuilder.include(latLng);
                }

                line.width(10f).color(App.getContext().getResources().getColor(R.color.blood));
                Log.e("color",line.getColor()+"");
                googleMap.addPolyline(line);

                int size = App.getContext().getResources().getDisplayMetrics().widthPixels;
                LatLngBounds latLngBounds = latLngBuilder.build();
                CameraUpdate track = CameraUpdateFactory.newLatLngBounds(latLngBounds, size, size, 25);
                googleMap.moveCamera(track);

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {
                AbstractActivity.hideProgress();

            }

        };
    }
    static public Observer<String> setPhoto(DetailsView view){

        return new Observer<String>(){
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(String photoReference) {
                CollapsingToolbarLayout.LayoutParams params;
                params=new CollapsingToolbarLayout.LayoutParams(
                        CollapsingToolbarLayout.LayoutParams.MATCH_PARENT
                        ,(int)App.getContext().getResources().getDimension(R.dimen.collapsedImageHeight));
                if(photoReference.equals("gg")) {
                    Toast.makeText(view.context, "К Сожалению гугл помойка и возвращает не все фото... " +
                            "Попробуй другие может и повезет -_-(Сомневаюсь)", Toast.LENGTH_LONG).show();
                    AbstractActivity.hideProgress();
                    switch (TemporarilyStored.title) {
                        case "Музеи":
                            view.imageView.setImageDrawable(App.getContext().getResources().getDrawable(R.drawable.mus));
                            break;
                        case "Выставочные залы":
                            view.imageView.setImageDrawable(App.getContext().getResources().getDrawable(R.drawable.zal));
                            break;
                        case "Театры":
                            view.imageView.setImageDrawable(App.getContext().getResources().getDrawable(R.drawable.teatr));
                            break;
                    }
                    view.imageView.setLayoutParams(params);
                    return;
                }
                String url=
                        "https://maps.googleapis.com/maps/api/place/photo?maxwidth=1000&photoreference="+
                        photoReference+"&key="+App.getContext().getString(R.string.google_maps_key);



                Log.e("url",url);
                Picasso.with(view.context)
                        .load(url)
                        .into(view.imageView,
                                new Callback() {
                                    @Override
                                    public void onSuccess() {
                                        view.imageView.setLayoutParams(params);
                                        AbstractActivity.hideProgress();
                                    }

                                    @Override
                                    public void onError() {

                                    }
                                });

            }

            @Override
            public void onError(Throwable e) {
                AbstractActivity.hideProgress();
            }

            @Override
            public void onComplete() {


            }

        };
    }
}
