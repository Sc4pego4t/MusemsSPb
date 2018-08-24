package com.nixbyte.project.utils;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.nixbyte.project.R;
import com.nixbyte.project.modules.activity.AbstractActivity;
import com.nixbyte.project.utils.api.AutorizationApi;
import com.nixbyte.project.utils.api.MapApi;
import com.nixbyte.project.utils.api.RateReviewApi;
import com.nixbyte.project.utils.api.SiteInteractionApi;
import com.nixbyte.project.utils.responseType.Row;

import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.nixbyte.project.utils.RetrofitFunctions.doubleIntResponse;
import static com.nixbyte.project.utils.RetrofitFunctions.doubleStringResponse;
import static com.nixbyte.project.utils.RetrofitFunctions.getPhotoReference;
import static com.nixbyte.project.utils.RetrofitFunctions.intAndArrayResponse;
import static com.nixbyte.project.utils.RetrofitFunctions.makeRoute;
import static com.nixbyte.project.utils.RetrofitFunctions.singleIntResponse;
import static com.nixbyte.project.utils.RetrofitFunctions.spbGovSiteResponse;

public class ApiFactory {

    private static OkHttpClient CLIENT;
    private static Gson gson;
    public final static String govsite="http://data.gov.spb.ru/api/v1/datasets/";
    public final static String mysite="https://lucullean-movements.000webhostapp.com/android/";

    static {
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        CLIENT = new OkHttpClient.Builder()
                .addInterceptor(httpLoggingInterceptor)
                .build();

         gson = new GsonBuilder()
                .setLenient()
                .create();

    }

    private static Retrofit getRetrofit(String baseUrl) {
        AbstractActivity.showProgress();

        return new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(CLIENT).build();
    }
    static public Observable<Integer> autorize(String email, String password){
        return singleIntResponse(getRetrofit("https://lucullean-movements.000webhostapp.com/android/").
                create(AutorizationApi.class).autorizeUser(password,email));
    }

    static public Observable<Integer> socialAutorize(String id, String which){
        return singleIntResponse(getRetrofit("https://lucullean-movements.000webhostapp.com/android/")
                .create(AutorizationApi.class).autorizeUserBySocial(id, which));
    }

    static public Observable<Integer>  register(String email, String password){
        return singleIntResponse(getRetrofit("https://lucullean-movements.000webhostapp.com/android/").
                create(AutorizationApi.class).registerUser(password, email));
    }

    static public Observable<Integer> socialsConnect(String id, String which, String userid){
        Log.e("GG","connect"+id+" "+which +" "+ userid);
        return singleIntResponse(getRetrofit("https://lucullean-movements.000webhostapp.com/android/").
                create(AutorizationApi.class).connectSocial(id,which,userid));
    }

    static public Observable<List<String>> returnInfo(String token){
        return doubleStringResponse(getRetrofit("https://lucullean-movements.000webhostapp.com/android/").
                create(AutorizationApi.class).returnInfo(token));
    }

    static public Observable<Integer> changeInfo(String token,String email,String password){
        return singleIntResponse(getRetrofit("https://lucullean-movements.000webhostapp.com/android/").
                create(AutorizationApi.class).changeInfo(token, email, password));
    }

    static public Observable<Integer> addReview(String text, String user, String marker){
        return singleIntResponse(getRetrofit("https://lucullean-movements.000webhostapp.com/android/").
                create(RateReviewApi.class).addReview(text, marker, user));
    }
    static public Observable<Integer> setRate(String like, String user, String marker){
        return doubleIntResponse(getRetrofit("https://lucullean-movements.000webhostapp.com/android/").
                create(RateReviewApi.class).setRate(like, marker, user));
    }

    static public Observable<List<String>> getReviewsAndRate(String marker, String token){
        return intAndArrayResponse(getRetrofit("https://lucullean-movements.000webhostapp.com/android/").
                create(RateReviewApi.class).returnRateAndReview(marker,token));
    }
    static public Observable<Row> getInfo(){
        return spbGovSiteResponse(getRetrofit("http://data.gov.spb.ru/api/v1/datasets/").
                create(SiteInteractionApi.class).getInfo());
    }
    static public Observable<Row> getZals(){
        return spbGovSiteResponse(getRetrofit("http://data.gov.spb.ru/api/v1/datasets/").
                create(SiteInteractionApi.class).getZals());
    }
    static public Observable<Row> getTeatrs(){
        return spbGovSiteResponse(getRetrofit("http://data.gov.spb.ru/api/v1/datasets/").
                create(SiteInteractionApi.class).getTeatrs());
    }

    static public Observable<String> getRoute(String position, String destination){
        return makeRoute(getRetrofit("https://maps.googleapis.com/maps/api/").
                create(MapApi.class).getRoute(position,destination));
    }

    static public Observable<String> getPhoto(String location, String radius,String types){
        return getPhotoReference(getRetrofit("https://maps.googleapis.com/maps/api/").
                create(MapApi.class).getPhotoReference(location
                ,radius
                ,types
                ,App.getContext().getString(R.string.google_maps_key)));
    }


}
