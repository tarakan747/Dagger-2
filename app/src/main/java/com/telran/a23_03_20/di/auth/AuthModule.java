package com.telran.a23_03_20.di.auth;

import com.telran.a23_03_20.business.auth.AuthInteractor;
import com.telran.a23_03_20.business.auth.AuthInteractorImpl;
import com.telran.a23_03_20.data.auth.AuthRepository;
import com.telran.a23_03_20.data.auth.AuthRepositoryImpl;
import com.telran.a23_03_20.data.global.store.StoreProvider;
import com.telran.a23_03_20.data.global.web.Api;

import dagger.Module;
import dagger.Provides;

@Module
public class AuthModule {
    @Provides
    @AuthScope
    AuthRepository provideAuthRepo(Api api, StoreProvider store){
        return new AuthRepositoryImpl(api,store);
    }

    @Provides
    @AuthScope
    AuthInteractor provideAuthInteractor(AuthRepository repo){
        return new AuthInteractorImpl(repo);
    }
}
