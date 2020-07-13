package com.telran.a23_03_20.business.auth;


import com.telran.a23_03_20.data.auth.AuthRepository;

import io.reactivex.Completable;

public class AuthInteractorImpl implements AuthInteractor {
    AuthRepository repository;

    public AuthInteractorImpl(AuthRepository repository) {
        this.repository = repository;
    }

    @Override
    public Completable onLogin(String email, String password) {
        try {
            isEmailValid(email);
            isPasswordValid(password);
            return repository.onLogin(email, password);
        } catch (Throwable throwable) {
            return Completable.error(throwable);
        }
    }

    @Override
    public Completable onRegistration(String email, String password) {
        try {
            isEmailValid(email);
            isPasswordValid(password);
            return repository.onRegistration(email, password);
        } catch (Throwable throwable) {
            return Completable.error(throwable);
        }
    }

    private void isEmailValid(String email) {
        if (!email.contains("@")) {
            throw new EmailValidException("Email must contains @");
        }
        if (email.length() - email.lastIndexOf(".") < 2) {
            throw new EmailValidException("Email must contains 2 symbols after last dot");
        }
    }

    private void isPasswordValid(String password) {
        if (password.length() < 4) {
            throw new PasswordValidException("Password length must be bigger then 4 symbols");
        }
    }
}
