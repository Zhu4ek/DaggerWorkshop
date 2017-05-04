package com.kirich1409.news.network;

import com.kirich1409.news.BuildConfig;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * @authror Kirill Rozov
 * @date 1/5/17.
 */

public class AddAuthNewsApiInterceptor implements Interceptor {

    public static final String HEADER_API_KEY = "X-Api-Key";
    public static final String QUERY_PARAM_API_KEY = "apiKey";

    @Override
    public Response intercept(final Chain chain) throws IOException {
        Request originalRequest = chain.request();
        if (originalRequest.header(HEADER_API_KEY) != null
                || originalRequest.url().queryParameter(QUERY_PARAM_API_KEY) != null) {
            return chain.proceed(originalRequest);
        } else {
            Request request = originalRequest.newBuilder()
                    .addHeader(HEADER_API_KEY, BuildConfig.NEWS_API_KEY)
                    .build();
            return chain.proceed(request);
        }
    }
}
