package com.telran.a23_03_20.presentation.auth.view;

import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.telran.a23_03_20.R;
import com.telran.a23_03_20.presentation.auth.presenter.AuthPresenter;
import com.telran.a23_03_20.presentation.contactList.view.ContactListFragment;

import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class AuthFragment extends MvpAppCompatFragment implements AuthView {

    @InjectPresenter
    AuthPresenter presenter;

    @BindView(R.id.inputEmail)
    EditText inputEmail;
    @BindView(R.id.inputPassword)
    EditText inputPassword;
    @BindView(R.id.regBtn)
    Button regBtn;
    @BindView(R.id.loginBtn)
    Button loginBtn;
    @BindView(R.id.myProgress)
    ProgressBar myProgress;

    private Unbinder unbinder;

    private AlertDialog errorDialog;

    public AuthFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_auth, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick(R.id.regBtn)
    void onRegClick() {
        presenter.onRegistration(inputEmail.getText().toString(), inputPassword.getText().toString());
    }

    @OnClick(R.id.loginBtn)
    void onLoginClick() {
        presenter.onLogin(inputEmail.getText().toString(), inputPassword.getText().toString());
    }

    @Override
    public void showProgress() {
        loginBtn.setEnabled(false);
        regBtn.setEnabled(false);
        inputEmail.setEnabled(false);
        inputPassword.setEnabled(false);
        myProgress.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        loginBtn.setEnabled(true);
        regBtn.setEnabled(true);
        inputEmail.setEnabled(true);
        inputPassword.setEnabled(true);
        myProgress.setVisibility(View.GONE);
    }

    @Override
    public void showError(String error) {
        errorDialog = new AlertDialog.Builder(requireContext())
                .setTitle("Error")
                .setMessage(error)
                .setCancelable(false)
                .setPositiveButton("Ok", ((dialog, which) -> presenter.onDialogClicked()))
                .create();
        errorDialog.show();
    }

    @Override
    public void hideError() {
        if (errorDialog != null && errorDialog.isShowing()) {
            errorDialog.dismiss();
        }
    }

    @Override
    public void showNextView() {
        Objects.requireNonNull(getFragmentManager()).beginTransaction()
                .replace(R.id.root,new ContactListFragment())
                .commit();
    }


}
