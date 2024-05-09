package com.example.app_01.LoginPropeties;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.GET;
import okhttp3.ResponseBody;

public interface ApiService {
    @GET("https://ctt-sis.hust.edu.vn/Account/Login.aspx")
    Call<ResponseBody> login(
            @Field("username") String username,
            @Field("password") String password,
            @Field("captcha") String captcha
    );
}
