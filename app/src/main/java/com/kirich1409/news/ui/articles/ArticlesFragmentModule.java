package com.kirich1409.news.ui.articles;

import android.support.annotation.NonNull;

import com.kirich1409.news.dagger.FragmentScope;
import com.kirich1409.news.mvp.AndroidComponent;
import com.kirich1409.news.mvp.FragmentComponent;
import com.kirich1409.news.ui.articles.mvp.ArticlesMVPModule;

import dagger.Module;
import dagger.Provides;

/**
 * @author Kirill Rozov
 */

@Module(includes = ArticlesMVPModule.class)
public final class ArticlesFragmentModule {

    @NonNull
    private final ArticlesFragment mFragment;

    ArticlesFragmentModule(@NonNull ArticlesFragment fragment) {
        mFragment = fragment;
    }

    @FragmentScope
    @Provides
    final ArticlesFragment provideFragment() {
        return mFragment;
    }

    @FragmentScope
    @Provides
    final AndroidComponent provideFragmentComponent() {
        return new FragmentComponent(mFragment);
    }
}
