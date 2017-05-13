package com.kirich1409.news.ui.article;

import com.kirich1409.news.dagger.ActivityScope;
import com.kirich1409.news.network.data.ArticleDto;
import com.kirich1409.news.ui.articles.ArticlesActivity;

import dagger.BindsInstance;
import dagger.Subcomponent;

/**
 * @author Kirill Rozov
 */
@ActivityScope
@Subcomponent(modules = ArticleActivityModule.class)
public interface ArticleActivityComponent {

    void inject(ArticlesActivity activity);

    ArticleFragmentComponent.Builder articleFragmentComponent();

    @Subcomponent.Builder
    interface Builder {

        Builder articleActivityModule(ArticleActivityModule module);

        @BindsInstance
        Builder article(ArticleDto article);

        ArticleActivityComponent build();
    }
}
