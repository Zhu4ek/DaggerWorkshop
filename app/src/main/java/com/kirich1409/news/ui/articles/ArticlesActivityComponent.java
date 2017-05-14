package com.kirich1409.news.ui.articles;

import com.kirich1409.news.dagger.ActivityScope;
import com.kirich1409.news.network.data.NewsSourceDto;

import dagger.BindsInstance;
import dagger.Subcomponent;

/**
 * @author Kirill Rozov
 */
@ActivityScope
@Subcomponent(modules = ArticlesActivityModule.class)
public interface ArticlesActivityComponent {

    void inject(ArticlesActivity activity);

    ArticlesFragmentComponent.Builder articlesFragmentComponent();

    @Subcomponent.Builder
    interface Builder {

        Builder articlesActivityModule(ArticlesActivityModule module);

        @BindsInstance
        Builder newsSource(NewsSourceDto source);

        ArticlesActivityComponent build();
    }
}
