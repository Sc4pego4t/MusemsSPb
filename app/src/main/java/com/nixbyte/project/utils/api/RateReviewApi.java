package com.nixbyte.project.utils.api;

import com.nixbyte.project.utils.responseType.DoubleIntResponse;
import com.nixbyte.project.utils.responseType.IntAndArrayResponse;
import com.nixbyte.project.utils.responseType.SingleIntResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by scapegoat on 06/04/2018.
 */

public interface RateReviewApi {

    @FormUrlEncoded
    @POST("return_rate_and_review.php")
    Call<IntAndArrayResponse> returnRateAndReview(@Field("marker") String marker, @Field("token") String token);

    @FormUrlEncoded
    @POST("set_rate.php")
    Call<DoubleIntResponse> setRate(@Field("like") String id,
                                    @Field("marker") String marker,
                                    @Field("user") String user);

    @FormUrlEncoded
    @POST("add_review.php")
    Call<SingleIntResponse> addReview(@Field("text") String text,
                                      @Field("marker") String marker,
                                      @Field("user") String user);

}
