package com.telran.a23_03_20.di.app;

import com.telran.a23_03_20.di.auth.AuthComponent;
import com.telran.a23_03_20.di.auth.AuthModule;

import javax.inject.Singleton;

import dagger.Component;

@Component(modules = {AppModule.class})
@Singleton
public interface AppComponent {
    AuthComponent plus(AuthModule module);
}
