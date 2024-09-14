package com.auth.test.authtest.service;


public interface TokenGenerate {
    String generateAccessToken(String clientId);
    String generateRefreshToken(String clientId);
    public void sendEmail();

}
