package com.kirich1409.news.ui.article;

import android.support.annotation.NonNull;

import com.kirich1409.news.dagger.ActivityScope;

import dagger.Module;
import dagger.Provides;

/**
 * @author Kirill Rozov
 */

@Module(subcomponents = ArticleFragmentComponent.class)
public class ArticleActivityModule {

    @NonNull
    private final ArticleActivity mActivity;

    ArticleActivityModule(@NonNull ArticleActivity activity) {
        mActivity = activity;
    }

    @ActivityScope
    @Provides
    final ArticleActivity provideActivity() {
        return mActivity;
    }
}
