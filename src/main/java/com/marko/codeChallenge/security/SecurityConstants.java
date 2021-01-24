package com.marko.codeChallenge.security;

public class SecurityConstants {
    public static final String SIGN_UP_URSL = "/api/user/**";
    public static final String SIGN_UP_WITH_CERTIFICATE_URL = "/api/user/findByIdentificationNumber/**";
    public static final String SECRET = "SecretKeyToGenJWTs";
    public static final String TOKEN_PREFIX = "Core ";
    public static final String HEADER_STRING = "Authorization";
    public static final long EXPIRATION_TIME = 700000;
    public static final long EXPIRATION_TIME_REFRESH_TOKEN = 300000;

}
