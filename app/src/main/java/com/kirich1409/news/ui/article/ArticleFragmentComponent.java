package com.kirich1409.news.ui.article;

import com.kirich1409.news.dagger.FragmentScope;

import dagger.Subcomponent;

/**
 * @author Kirill Rozov
 */
@FragmentScope
@Subcomponent(modules = ArticleFragmentModule.class)
public interface ArticleFragmentComponent {

    void inject(ArticleFragment fragment);

    @Subcomponent.Builder
    interface Builder {

        Builder articleFragmentModule(ArticleFragmentModule module);

        ArticleFragmentComponent build();
    }
}
