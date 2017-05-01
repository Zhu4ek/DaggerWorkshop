package com.kirich1409.workshop.dagger;

import android.app.Application;
import android.support.annotation.NonNull;

import com.kirich1409.workshop.dagger.dagger.AppComponent;
import com.kirich1409.workshop.dagger.dagger.AppModule;
import com.kirich1409.workshop.dagger.dagger.DaggerAppComponent;

/**
 * @authror Kirill Rozov
 * @date 1/5/17.
 */

public class NewsApp extends Application {

    private final AppComponent mAppComponent;

    {
        mAppComponent = DaggerAppComponent.builder()
                .newsApiBaseUrl("https://newsapi.org/v1/")
                .appModule(new AppModule(this))
                .build();
    }

    @NonNull
    public final AppComponent getAppComponent() {
        if (mAppComponent == null) {
            throw new IllegalStateException();
        }
        return mAppComponent;
    }
}
