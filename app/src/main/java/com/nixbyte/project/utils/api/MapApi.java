package com.nixbyte.project.utils.api;

import com.nixbyte.project.utils.responseType.PhotoReferenceResponse;
import com.nixbyte.project.utils.responseType.RouteResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by scapegoat on 18/05/2018.
 */

public interface MapApi {
    @GET("directions/json")
    Call<RouteResponse> getRoute(
            @Query(value = "origin") String position,
            @Query(value = "destination") String destination
    );

    @GET("place/nearbysearch/json")
    Call<PhotoReferenceResponse> getPhotoReference(
            @Query(value = "location") String location,
            @Query(value = "radius") String radius,
            @Query(value = "types") String types,
            @Query(value = "key") String key
    );
}
