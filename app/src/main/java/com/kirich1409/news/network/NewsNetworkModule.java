package com.kirich1409.news.network;

import android.content.Context;
import android.support.annotation.NonNull;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

/**
 * @authror Kirill Rozov
 * @date 1/5/17.
 */
@Module
public abstract class NewsNetworkModule {

    public static final long CACHE_SIZE = 10 * 1024;
    private static final String CACHE_DIR = "network";

    @Singleton
    @Provides
    static Retrofit provideRetrofit(@NonNull @NewsApiBaseUrl String baseUrl,
                                    @NonNull OkHttpClient client,
                                    @NonNull ObjectMapper mapper) {
        return new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(JacksonConverterFactory.create(mapper))
                .client(client)
                .build();
    }

    @Singleton
    @Provides
    static ObjectMapper provideObjectMapper() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(JsonGenerator.Feature.IGNORE_UNKNOWN, true);
        return mapper;
    }

    @Singleton
    @Provides
    static Cache provideCache(@NonNull Context context) {
        File cacheDir = new File(context.getCacheDir(), CACHE_DIR);
        return new Cache(cacheDir, CACHE_SIZE);
    }

    @Singleton
    @Provides
    static OkHttpClient provideOkHttpClient(@NonNull Cache cache) {
        return new OkHttpClient.Builder()
                .cache(cache)
                .addInterceptor(new AddAuthNewsApiInterceptor())
                .build();
    }
}
