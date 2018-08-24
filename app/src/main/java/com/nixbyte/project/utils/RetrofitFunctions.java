package com.nixbyte.project.utils;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.nixbyte.project.modules.activity.AbstractActivity;
import com.nixbyte.project.utils.responseType.DoubleIntResponse;
import com.nixbyte.project.utils.responseType.DoubleStringResponse;
import com.nixbyte.project.utils.responseType.IntAndArrayResponse;
import com.nixbyte.project.utils.responseType.PhotoReferenceResponse;
import com.nixbyte.project.utils.responseType.RouteResponse;
import com.nixbyte.project.utils.responseType.Row;
import com.nixbyte.project.utils.responseType.SingleIntResponse;
import com.nixbyte.project.utils.responseType.SpbGovSiteResponse;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitFunctions {

    public static Observable<List<String>> intAndArrayResponse(Call<IntAndArrayResponse> call){
        return Observable.create(e -> {
            call.enqueue(new Callback<IntAndArrayResponse>() {

                @Override
                public void onResponse(Call<IntAndArrayResponse> call1, Response<IntAndArrayResponse> response) {
                    if (response.isSuccessful()) {
                        ArrayList<String> list=new ArrayList<>();
                        Collections.addAll(list, response.body().getReviews());
                        list.add(response.body().getRate());
                        list.add(response.body().getLiked());
                        e.onNext(list);
                        e.onComplete();

                    } else {
                        e.onError(new Throwable("BEDA"));
                    }
                }
                @Override
                public void onFailure(Call<IntAndArrayResponse> call1, Throwable t) {
                    e.onError(t);
                }
            });
        });
    }


    public static Observable<Row> spbGovSiteResponse(Call<List<SpbGovSiteResponse>> call){
        Log.e("gg",call.request().url().toString());
        return Observable.create(e -> {
            call.enqueue(new Callback<List<SpbGovSiteResponse>>() {

                @Override
                public void onResponse(Call<List<SpbGovSiteResponse>> call1, Response<List<SpbGovSiteResponse>> response) {
                    for(SpbGovSiteResponse item: response.body()){
                        e.onNext(item.getRow());
                    }
                    e.onComplete();
                }
                @Override
                public void onFailure(Call<List<SpbGovSiteResponse>> call1, Throwable t) {
                    e.onError(t);
                }
            });
        });
    }




    public static Observable<List<String>> doubleStringResponse(Call<DoubleStringResponse> call){
        return Observable.create(e -> {
            call.enqueue(new Callback<DoubleStringResponse>() {

                @Override
                public void onResponse(Call<DoubleStringResponse> call1, Response<DoubleStringResponse> response) {
                    if (response.isSuccessful()) {
                        ArrayList<String> list=new ArrayList<>();
                        list.add(response.body().getEmail());
                        list.add(response.body().getPassword());
                        e.onNext(list);
                        e.onComplete();
                    } else {
                        e.onError(new Throwable("BEDA"));
                    }
                }
                @Override
                public void onFailure(Call<DoubleStringResponse> call1, Throwable t) {
                    e.onError(t);
                }
            });
        });
    }
    public static Observable<Integer> singleIntResponse(Call<SingleIntResponse> call) {
        return Observable.create(e -> {
            call.enqueue(new Callback<SingleIntResponse>() {
                @Override
                public void onResponse(Call<SingleIntResponse> call1, Response<SingleIntResponse> response) {
                    if (response.isSuccessful() && response.body().getResult() != -1) {
                        e.onNext(response.body().getResult());
                        e.onComplete();
                    } else {
                        e.onError(new Throwable("BEDA"));
                    }
                }

                @Override
                public void onFailure(Call<SingleIntResponse> call1, Throwable t) {
                    e.onError(t);
                }
            });
        });

    }

    public static Observable<Integer> doubleIntResponse(Call<DoubleIntResponse> call) {
            return Observable.create(e -> {
                call.enqueue(new Callback<DoubleIntResponse>() {
                    @Override
                    public void onResponse(Call<DoubleIntResponse> call1, Response<DoubleIntResponse> response) {
                        if (response.isSuccessful() && response.body().getResult() != -1) {
                            if(response.body().getResult()==1) {
                                e.onNext(response.body().getRate());
                            } else {
                                e.onError(new Throwable("GOLOSOVAL"));
                            }
                            Log.e("GG", response.body().getResult() + "");
                            e.onComplete();
                        } else {
                            e.onError(new Throwable("BEDA"));
                        }
                    }

                    @Override
                    public void onFailure(Call<DoubleIntResponse> call1, Throwable t) {
                        e.onError(t);
                    }
                });
            });
    }

    public static Observable<String> makeRoute(Call<RouteResponse> call) {
        Log.e("qwe",call.request().url().toString());
        return Observable.create(e -> {
            call.enqueue(new Callback<RouteResponse>() {
                @Override
                public void onResponse(Call<RouteResponse> call1, Response<RouteResponse> response) {
                    e.onNext(response.body().getPoints());
                    e.onComplete();
                }

                @Override
                public void onFailure(Call<RouteResponse> call1, Throwable t) {
                    e.onError(t);
                }
            });
        });
    }

    public static Observable<String> getPhotoReference(Call<PhotoReferenceResponse> call) {
        Log.e("D:::",call.request().url().toString());
        return Observable.create(e -> {
            Log.e("gg","almost");
            call.enqueue(new Callback<PhotoReferenceResponse>() {
                @Override
                public void onResponse(Call<PhotoReferenceResponse> call1, Response<PhotoReferenceResponse> response) {
                    Log.e("gg","fine");
                    try {
                        e.onNext(response.body().getPhotoReference());
                    } catch (Exception e1) {
                        Log.e("gg","Googlegovno");
                        e.onNext("gg");
                    }
                    e.onComplete();
                }

                @Override
                public void onFailure(Call<PhotoReferenceResponse> call1, Throwable t) {
                    Log.e("gg","err");
                    e.onError(t);
                }
            });
        });
    }

}