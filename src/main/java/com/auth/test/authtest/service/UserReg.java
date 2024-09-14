package com.auth.test.authtest.service;

public interface UserReg {
    void register(String clientId, String clientSecret);
    void checkCredentials(String clientId, String clientSecret);
}
