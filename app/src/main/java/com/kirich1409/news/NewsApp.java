package com.kirich1409.news;

import android.support.annotation.NonNull;
import android.support.multidex.MultiDexApplication;

import com.kirich1409.news.dagger.AppComponent;
import com.kirich1409.news.dagger.AppModule;
import com.kirich1409.news.dagger.DaggerAppComponent;

/**
 * @authror Kirill Rozov
 * @date 1/5/17.
 */

public class NewsApp extends MultiDexApplication {

    private final AppComponent mAppComponent;

    {
        mAppComponent = DaggerAppComponent.builder()
                .newsApiBaseUrl("https://newsapi.org/v1/")
                .appModule(new AppModule(this))
                .build();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mAppComponent.inject(this);
    }

    @NonNull
    public final AppComponent getAppComponent() {
        if (mAppComponent == null) {
            throw new IllegalStateException();
        }
        return mAppComponent;
    }
}
