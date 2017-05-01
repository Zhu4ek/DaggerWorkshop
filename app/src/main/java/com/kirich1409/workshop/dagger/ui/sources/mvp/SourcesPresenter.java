package com.kirich1409.workshop.dagger.ui.sources.mvp;

import com.kirich1409.workshop.dagger.mvp.Presenter;
import com.kirich1409.workshop.dagger.network.data.NewsSourceDto;

/**
 * @authror Kirill Rozov
 * @date 1/5/17.
 */

public interface SourcesPresenter extends Presenter<SourcesView> {

    void openArticles(final NewsSourceDto source);
}
