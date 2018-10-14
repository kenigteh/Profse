package com.ivlup.profse.backend;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import retrofit2.http.Path;
public interface DB {
    @FormUrlEncoded
    @POST("sql/login.php")
    Call<Answer> login(@Field("google_id") String google_id,
                       @Field ("name") String name,
                       @Field ("surname") String surname,
                       @Field ("email") String email,
                       @Field ("photo") String photo);

    @POST("sql/update.php")
    Call<Answer> update();
}