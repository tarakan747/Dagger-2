package com.telran.a23_03_20.data.global.web;

import com.telran.a23_03_20.data.dto.AuthRequestDto;
import com.telran.a23_03_20.data.dto.AuthResponseDto;

import io.reactivex.Single;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface Api {
    @POST("api/registration")
    Single<Response<AuthResponseDto>> registration(@Body AuthRequestDto body);

    @POST("api/login")
    Single<Response<AuthResponseDto>> login(@Body AuthRequestDto body);
}
