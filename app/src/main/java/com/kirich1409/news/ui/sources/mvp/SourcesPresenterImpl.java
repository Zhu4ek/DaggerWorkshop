package com.kirich1409.news.ui.sources.mvp;

import android.support.annotation.NonNull;

import com.kirich1409.news.mvp.BasePresenter;
import com.kirich1409.news.network.data.NewsSourceDto;

import javax.inject.Inject;

import dagger.Lazy;

/**
 * @authror Kirill Rozov
 * @date 1/5/17.
 */

final class SourcesPresenterImpl extends BasePresenter<SourcesView>
        implements SourcesPresenter {

    @NonNull
    private final Lazy<SourcesLauncher> mLauncher;

    @Inject
    SourcesPresenterImpl(@NonNull Lazy<SourcesLauncher> launcher) {
        mLauncher = launcher;
    }

    @Override
    public void openArticles(@NonNull NewsSourceDto source) {
        mLauncher.get().openArticles(source);
    }
}
