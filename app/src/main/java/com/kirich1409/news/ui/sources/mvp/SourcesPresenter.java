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

final class SourcesPresenter extends BasePresenter<SourcesContract.View>
        implements SourcesContract.Presenter {

    @NonNull
    private final Lazy<SourcesContract.Starter> mStarter;

    @Inject
    SourcesPresenter(@NonNull Lazy<SourcesContract.Starter> starter) {
        mStarter = starter;
    }

    @Override
    public void openArticles(@NonNull NewsSourceDto source) {
        mStarter.get().openArticles(source);
    }
}
