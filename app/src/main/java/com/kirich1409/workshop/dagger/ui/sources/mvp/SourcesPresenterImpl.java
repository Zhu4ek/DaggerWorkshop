package com.kirich1409.workshop.dagger.ui.sources.mvp;

import android.content.Intent;

import com.kirich1409.workshop.dagger.mvp.AndroidComponent;
import com.kirich1409.workshop.dagger.mvp.BasePresenter;
import com.kirich1409.workshop.dagger.network.data.NewsSourceDto;
import com.kirich1409.workshop.dagger.ui.articles.ArticlesActivity;

import javax.inject.Inject;

import dagger.Lazy;

/**
 * @authror Kirill Rozov
 * @date 1/5/17.
 */

final class SourcesPresenterImpl extends BasePresenter<SourcesView>
        implements SourcesPresenter {

    private Lazy<AndroidComponent> mComponent;

    @Inject
    SourcesPresenterImpl(final Lazy<AndroidComponent> component) {
        mComponent = component;
    }

    @Override
    public void openArticles(final NewsSourceDto source) {
        AndroidComponent component = mComponent.get();
        Intent intent = ArticlesActivity.newIntent(component.getContext(), source.getId());
        component.startActivity(intent);
    }
}
