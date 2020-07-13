package com.telran.a23_03_20.presentation.auth.presenter;

import android.util.Log;
import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.telran.a23_03_20.App;
import com.telran.a23_03_20.business.auth.AuthInteractor;
import com.telran.a23_03_20.di.auth.AuthModule;
import com.telran.a23_03_20.presentation.auth.view.AuthView;
import javax.inject.Inject;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

@InjectViewState
public class AuthPresenter extends MvpPresenter<AuthView> {

    private static final String TAG = "AuthPresenter";
    Disposable disposable;

    @Inject
    AuthInteractor interactor;

    public AuthPresenter(){

        App.get().plus(new AuthModule()).inject(this);
    }

    public void onLogin(String email, String password){
        getViewState().showProgress();
        disposable = interactor.onLogin(email, password)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::onSuccess,throwable -> {
                    Log.e(TAG, "onLogin: ", throwable);
                    this.onError(throwable.getMessage());
                });
    }

    public void onRegistration(String email, String password){
        getViewState().showProgress();
        disposable = interactor.onRegistration(email, password)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::onSuccess, throwable -> {
                    Log.e(TAG, "onRegistration: ", throwable);
                    this.onError(throwable.getMessage());
                });
    }

    public void onDialogClicked(){
        getViewState().hideError();
    }

    private void onSuccess(){
        getViewState().hideProgress();
        getViewState().showNextView();
    }

    private void onError(String error){
        getViewState().hideProgress();
        getViewState().showError(error);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if(disposable != null){
            disposable.dispose();
        }
        App.get().clearAuthComponent();
    }
}
