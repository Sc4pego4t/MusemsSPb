package com.nixbyte.project.utils.api;

import com.nixbyte.project.utils.responseType.SpbGovSiteResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;

/**
 * Created by scapegoat on 06/04/2018.
 */

public interface SiteInteractionApi {

    @Headers({
            "Authorization: Token 60a368fde851f786532fdb4b65c6fdb189122666"
    })
    @GET("123/versions/latest/data?per_page=1000")
    Call<List<SpbGovSiteResponse>> getInfo();

    @Headers({
            "Authorization: Token 60a368fde851f786532fdb4b65c6fdb189122666"
    })
    @GET("125/versions/latest/data?per_page=1000")
    Call<List<SpbGovSiteResponse>> getZals();
    @Headers({
            "Authorization: Token 60a368fde851f786532fdb4b65c6fdb189122666"
    })
    @GET("124/versions/latest/data?per_page=1000")
    Call<List<SpbGovSiteResponse>> getTeatrs();
}
