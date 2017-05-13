package com.kirich1409.news.ui.articles;

import com.kirich1409.news.dagger.FragmentModule;
import com.kirich1409.news.dagger.FragmentScope;
import com.kirich1409.news.ui.articles.mvp.ArticlesMVPModule;

import dagger.Subcomponent;

/**
 * @author Kirill Rozov
 */
@FragmentScope
@Subcomponent(modules = {FragmentModule.class, ArticlesMVPModule.class})
public interface ArticlesFragmentComponent {

    void inject(ArticlesFragment fragment);

    @Subcomponent.Builder
    interface Builder {

        Builder fragmentModule(FragmentModule module);

        ArticlesFragmentComponent build();
    }
}
