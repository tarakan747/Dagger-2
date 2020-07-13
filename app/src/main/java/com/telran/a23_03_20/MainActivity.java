package com.telran.a23_03_20;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.telran.a23_03_20.presentation.auth.view.AuthFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if(savedInstanceState == null){
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.root,new AuthFragment())
                    .commit();
        }
    }
}
