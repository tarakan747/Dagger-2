package com.telran.a23_03_20.data.auth;

import android.util.Log;

import com.telran.a23_03_20.data.dto.AuthRequestDto;
import com.telran.a23_03_20.data.dto.AuthResponseDto;
import com.telran.a23_03_20.data.global.store.StoreProvider;
import com.telran.a23_03_20.data.global.web.Api;

import java.io.IOException;

import io.reactivex.Completable;
import retrofit2.Response;

public class AuthRepositoryImpl implements AuthRepository {
    private static final String TAG = "AuthRepository";
    private Api api;
    private StoreProvider storeProvider;

    public AuthRepositoryImpl(Api api, StoreProvider storeProvider) {
        this.api = api;
        this.storeProvider = storeProvider;
    }

    @Override
    public Completable onLogin(String email, String password) {
        AuthRequestDto dto = new AuthRequestDto(email,password);
        return Completable.fromSingle(
                api.login(dto).doOnSuccess(this::onLoginSuccess)
        );
    }

    @Override
    public Completable onRegistration(String email, String password) {
        AuthRequestDto dto = new AuthRequestDto(email,password);
        return Completable.fromSingle(
                api.registration(dto).doOnSuccess(this::onRegistrationSuccess)
        );
    }

    private void onLoginSuccess(Response<AuthResponseDto> response) throws IOException {
        if(response.isSuccessful()){
            storeProvider.saveToken(response.body().getToken());
        }else if(response.code() == 400 || response.code() == 401){
            throw new RuntimeException(response.errorBody().string());
        }else{
            Log.e(TAG, "onLoginSuccess: " + response.errorBody().string());
            throw new RuntimeException("Server error! Call to Support");
        }
    }

    private void onRegistrationSuccess(Response<AuthResponseDto> response) throws IOException {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if(response.isSuccessful()){
            storeProvider.saveToken(response.body().getToken());
        }else if(response.code() == 400 || response.code() == 409){
            throw new RuntimeException(response.errorBody().string());
        }else{
            Log.e(TAG, "onRegistrationSuccess: " + response.errorBody().string());
            throw new RuntimeException("Server error! Call to Support");
        }
    }
}
