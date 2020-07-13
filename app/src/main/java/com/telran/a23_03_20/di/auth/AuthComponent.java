package com.telran.a23_03_20.di.auth;

import com.telran.a23_03_20.presentation.auth.presenter.AuthPresenter;

import dagger.Subcomponent;

@Subcomponent(modules = {AuthModule.class})
@AuthScope
public interface AuthComponent {
    void inject(AuthPresenter presenter);
}
