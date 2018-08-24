package com.nixbyte.project.utils.api;

import com.nixbyte.project.utils.responseType.DoubleStringResponse;
import com.nixbyte.project.utils.responseType.SingleIntResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by scapegoat on 06/04/2018.
 */

public interface AutorizationApi {
    @FormUrlEncoded
    @POST("is_autorized.php")
    Call<SingleIntResponse> autorizeUser(@Field("pas") String password,
                                         @Field("email") String email);

    @FormUrlEncoded
    @POST("change_autorize_info.php")
    Call<SingleIntResponse> changeInfo(@Field("token") String token,
                                       @Field("email") String email,
                                       @Field("password") String password);

    @FormUrlEncoded
    @POST("register.php")
    Call<SingleIntResponse> registerUser(@Field("pas") String password,
                                         @Field("email") String email);

    @FormUrlEncoded
    @POST("return_autorized_user_by_token.php")
    Call<DoubleStringResponse> returnInfo(@Field("token") String token);

    @FormUrlEncoded
    @POST("autorize_socials.php")
    Call<SingleIntResponse> autorizeUserBySocial(@Field("id") String id,
                                                 @Field("which") String which);

    @FormUrlEncoded
    @POST("connect_socials.php")
    Call<SingleIntResponse> connectSocial(@Field("id") String id,
                                          @Field("which") String which,
                                          @Field("userid") String userid);

}
