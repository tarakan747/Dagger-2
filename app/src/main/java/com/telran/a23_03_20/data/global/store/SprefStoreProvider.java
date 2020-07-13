package com.telran.a23_03_20.data.global.store;

import android.content.Context;

public class SprefStoreProvider implements StoreProvider{
    private static final String SP_AUTH = "AUTH";
    private static final String TOKEN_KEY = "TOKEN";
    private Context context;

    public SprefStoreProvider(Context context) {
        this.context = context;
    }

    @Override
    public boolean saveToken(String token) {
        return context.getSharedPreferences(SP_AUTH,Context.MODE_PRIVATE)
                .edit()
                .putString(TOKEN_KEY,token)
                .commit();
    }

    @Override
    public boolean clearToken() {
        return context.getSharedPreferences(SP_AUTH,Context.MODE_PRIVATE)
                .edit()
                .clear()
                .commit();
    }

    @Override
    public String getToken() {
        return context.getSharedPreferences(SP_AUTH,Context.MODE_PRIVATE)
                .getString(TOKEN_KEY,null);
    }
}
