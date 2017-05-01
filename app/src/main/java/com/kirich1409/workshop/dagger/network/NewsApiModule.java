package com.kirich1409.workshop.dagger.network;

import android.support.annotation.NonNull;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

/**
 * @authror Kirill Rozov
 * @date 1/5/17.
 */
@Module(includes = NewsNetworkModule.class)
public abstract class NewsApiModule {

    @Singleton
    @Provides
    static ArticlesRestService provideArticlesRestService(@NonNull Retrofit retrofit) {
        return retrofit.create(ArticlesRestService.class);
    }

    @Singleton
    @Provides
    static NewsSourcesRestService provideNewsSourcesRestService(@NonNull Retrofit retrofit) {
        return retrofit.create(NewsSourcesRestService.class);
    }
}
