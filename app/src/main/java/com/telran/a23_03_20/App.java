package com.telran.a23_03_20;

import android.app.Application;

import com.telran.a23_03_20.di.app.AppComponent;
import com.telran.a23_03_20.di.app.AppModule;
import com.telran.a23_03_20.di.app.DaggerAppComponent;
import com.telran.a23_03_20.di.auth.AuthComponent;
import com.telran.a23_03_20.di.auth.AuthModule;

public class App extends Application {
    private static App app;
    private AppComponent appComponent;
    private AuthComponent authComponent;

    public App(){
        app = this;
    }

    public static App get(){
        return app;
    }

    @Override
    public void onCreate() {
        appComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .build();
        super.onCreate();
    }

    public AuthComponent plus(AuthModule module){
        if(authComponent == null){
            authComponent = appComponent.plus(module);
        }
        return authComponent;
    }

    public void clearAuthComponent(){
        authComponent = null;
    }
}
