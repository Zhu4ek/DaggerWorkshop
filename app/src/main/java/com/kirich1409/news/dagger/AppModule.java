package com.kirich1409.news.dagger;

import android.app.Application;
import android.content.Context;
import android.support.annotation.NonNull;

import com.kirich1409.news.network.NewsApiModule;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * @authror Kirill Rozov
 * @date 1/5/17.
 */
@Module(includes = {
        NewsApiModule.class,
        ActivitiesModule.class,
})
public class AppModule {

    @NonNull
    private final Application mApplication;

    public AppModule(@NonNull final Application application) {
        mApplication = application;
    }

    @Singleton
    @Provides
    public Application provideApplication() {
        return mApplication;
    }

    @Singleton
    @Provides
    public Context provideAppContext() {
        return mApplication;
    }
}
