package com.kirich1409.news.dagger;

import com.kirich1409.news.NewsApp;
import com.kirich1409.news.network.NewsApiBaseUrl;
import com.kirich1409.news.ui.article.ArticleActivityComponent;
import com.kirich1409.news.ui.articles.ArticlesActivityComponent;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjectionModule;
import dagger.android.support.AndroidSupportInjectionModule;

/**
 * @authror Kirill Rozov
 * @date 1/5/17.
 */
@Singleton
@Component(modules = {
        AppModule.class,
        AndroidInjectionModule.class,
        AndroidSupportInjectionModule.class
})
public interface AppComponent {

    void inject(NewsApp app);

    ArticlesActivityComponent.Builder articlesActivityComponent();

    ArticleActivityComponent.Builder articleActivityComponent();

    @Component.Builder
    interface Builder {

        @SuppressWarnings("SameParameterValue")
        @BindsInstance
        Builder newsApiBaseUrl(@NewsApiBaseUrl String baseUrl);

        Builder appModule(AppModule appModule);

        AppComponent build();
    }
}
