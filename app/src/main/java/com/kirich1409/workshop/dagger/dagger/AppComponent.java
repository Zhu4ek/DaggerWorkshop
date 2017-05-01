package com.kirich1409.workshop.dagger.dagger;

import android.support.annotation.NonNull;

import com.kirich1409.workshop.dagger.network.NewsApiBaseUrl;
import com.kirich1409.workshop.dagger.ui.newssource.NewsSourceListFragment;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;

/**
 * @authror Kirill Rozov
 * @date 1/5/17.
 */
@Singleton
@Component(modules = {AppModule.class})
public interface AppComponent {

    void inject(NewsSourceListFragment fragment);

    @Component.Builder
    interface Builder {

        @BindsInstance
        Builder newsApiBaseUrl(@NewsApiBaseUrl String baseUrl);

        Builder appModule(@NonNull AppModule appModule);

        AppComponent build();
    }
}
