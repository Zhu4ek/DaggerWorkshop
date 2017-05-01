package com.kirich1409.workshop.dagger.dagger;

import com.kirich1409.workshop.dagger.NewsApp;
import com.kirich1409.workshop.dagger.network.NewsApiBaseUrl;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;

/**
 * @authror Kirill Rozov
 * @date 1/5/17.
 */
@Singleton
@Component(modules = AppModule.class)
public interface AppComponent {

    void inject(NewsApp app);

    @Component.Builder
    interface Builder {

        @BindsInstance
        Builder newsApiBaseUrl(@NewsApiBaseUrl String baseUrl);

        Builder appModule(AppModule appModule);

        AppComponent build();
    }
}
