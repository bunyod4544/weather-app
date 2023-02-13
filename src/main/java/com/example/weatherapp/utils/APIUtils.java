package com.example.weatherapp.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class APIUtils {

    public static final String API = "/api";
    public static final String V1 = "/v1";
    public static final String AUTH = "/auth";
    public static final String ID = "/{id}";
    public static final String LOGIN = "/login";
    public static final String CREATE = "/create";
}
