package com.telran.a23_03_20.data.auth;

import io.reactivex.Completable;

public interface AuthRepository {
    Completable onLogin(String email, String password);
    Completable onRegistration(String email, String password);
}
