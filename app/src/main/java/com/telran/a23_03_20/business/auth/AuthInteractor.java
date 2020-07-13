package com.telran.a23_03_20.business.auth;

import io.reactivex.Completable;

public interface AuthInteractor {
    Completable onLogin(String email, String password);
    Completable onRegistration(String email, String password);
}
