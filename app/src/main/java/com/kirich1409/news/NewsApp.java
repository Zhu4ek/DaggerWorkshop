package com.kirich1409.news;

import android.app.Activity;
import android.app.Application;
import android.support.annotation.NonNull;

import com.facebook.stetho.Stetho;
import com.kirich1409.news.dagger.AppComponent;
import com.kirich1409.news.dagger.AppModule;
import com.kirich1409.news.dagger.DaggerAppComponent;

import javax.inject.Inject;

import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;

/**
 * @authror Kirill Rozov
 * @date 1/5/17.
 */

public class NewsApp extends Application implements HasActivityInjector {

    @Inject
    DispatchingAndroidInjector<Activity> mActivityInjector;

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
        Stetho.initializeWithDefaults(this);
    }

    @NonNull
    public final AppComponent getAppComponent() {
        if (mAppComponent == null) {
            throw new IllegalStateException();
        }
        return mAppComponent;
    }

    @Override
    public DispatchingAndroidInjector<Activity> activityInjector() {
        return mActivityInjector;
    }
}
