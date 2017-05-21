package ru.s7.android.io.rest;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * intercept headers in request
 *
 * @author celikindv
 */
public class MainIntercepter implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request originRequest = chain.request();
        Request.Builder builder = originRequest.newBuilder();
        builder.addHeader("Authorization", "Token 222d53faafe7f0f11178b1e9f2aa4f5d772d96eb");
        return chain.proceed(builder.build());
    }
}