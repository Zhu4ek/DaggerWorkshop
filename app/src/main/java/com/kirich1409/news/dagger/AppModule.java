package com.kirich1409.news.dagger;

import android.app.Application;
import android.support.annotation.NonNull;

import com.kirich1409.news.network.NewsApiModule;
import com.kirich1409.news.ui.articles.ArticlesActivityComponent;

import dagger.Module;
import dagger.Provides;

/**
 * @authror Kirill Rozov
 * @date 1/5/17.
 */
@Module(
        includes = {
                NewsApiModule.class,
                ActivitiesModule.class,
                RxModule.class,
                AppBinds.class,
        },
        subcomponents = {ArticlesActivityComponent.class}
        )
public class AppModule {

    @NonNull
    private final Application mApplication;

    public AppModule(@NonNull final Application application) {
        mApplication = application;
    }

    @Provides
    Application provideApplication() {
        return mApplication;
    }
}
