package com.kirich1409.news.ui.sources.mvp;

import android.support.annotation.NonNull;

import com.kirich1409.news.network.data.NewsSourceDto;

/**
 * Created by Kirill Rozov on 5/3/17.
 */

public interface SourcesLauncher {

    void openArticles(final @NonNull NewsSourceDto source);
}
