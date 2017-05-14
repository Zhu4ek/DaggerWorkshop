package com.kirich1409.news.ui.articles;

import android.support.annotation.NonNull;

import com.kirich1409.news.dagger.ActivityScope;

import dagger.Module;
import dagger.Provides;

/**
 * @author Kirill Rozov
 */

@Module(subcomponents = ArticlesFragmentComponent.class)
public class ArticlesActivityModule {

    @NonNull
    private final ArticlesActivity mActivity;

    ArticlesActivityModule(@NonNull ArticlesActivity activity) {
        mActivity = activity;
    }

    @ActivityScope
    @Provides
    final ArticlesActivity provideActivity() {
        return mActivity;
    }
}
