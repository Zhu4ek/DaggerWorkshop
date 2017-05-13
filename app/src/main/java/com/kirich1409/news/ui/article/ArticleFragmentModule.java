package com.kirich1409.news.ui.article;

import android.support.annotation.NonNull;

import com.kirich1409.news.dagger.FragmentScope;
import com.kirich1409.news.mvp.AndroidComponent;
import com.kirich1409.news.mvp.FragmentComponent;
import com.kirich1409.news.ui.article.mvp.ArticleMVPModule;

import dagger.Module;
import dagger.Provides;

/**
 * @author Kirill Rozov
 */

@Module(includes = ArticleMVPModule.class)
public final class ArticleFragmentModule {

    @NonNull
    private final ArticleFragment mFragment;

    ArticleFragmentModule(@NonNull ArticleFragment fragment) {
        mFragment = fragment;
    }

    @FragmentScope
    @Provides
    final ArticleFragment provideFragment() {
        return mFragment;
    }

    @FragmentScope
    @Provides
    final AndroidComponent provideFragmentComponent() {
        return new FragmentComponent(mFragment);
    }
}
