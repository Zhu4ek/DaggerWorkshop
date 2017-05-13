package com.kirich1409.news.ui.articles;

import com.kirich1409.news.dagger.FragmentScope;

import dagger.Subcomponent;

/**
 * @author Kirill Rozov
 */
@FragmentScope
@Subcomponent(modules = ArticlesFragmentModule.class)
public interface ArticlesFragmentComponent {

    void inject(ArticlesFragment fragment);

    @Subcomponent.Builder
    interface Builder {

        Builder articlesFragmentModule(ArticlesFragmentModule module);

        ArticlesFragmentComponent build();
    }
}
