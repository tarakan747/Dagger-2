package com.telran.a23_03_20.data.global.store;

public interface StoreProvider {
    boolean saveToken(String token);
    boolean clearToken();
    String getToken();
}
