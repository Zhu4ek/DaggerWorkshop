package com.kirich1409.news.ui.sources.mvp;

import android.support.annotation.NonNull;

import com.kirich1409.news.mvp.Presenter;
import com.kirich1409.news.network.data.NewsSourceDto;

/**
 * @authror Kirill Rozov
 * @date 1/5/17.
 */

public interface SourcesPresenter extends Presenter<SourcesView> {

    void openArticles(final @NonNull NewsSourceDto source);
}
