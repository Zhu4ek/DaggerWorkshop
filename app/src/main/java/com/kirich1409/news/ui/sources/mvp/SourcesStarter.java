package com.kirich1409.news.ui.sources.mvp;

import android.content.Intent;
import android.support.annotation.NonNull;

import com.kirich1409.news.mvp.AndroidComponent;
import com.kirich1409.news.network.data.NewsSourceDto;
import com.kirich1409.news.ui.articles.ArticlesActivity;

import javax.inject.Inject;

/**
 * Created by Kirill Rozov on 5/3/17.
 */
class SourcesStarter implements SourcesContract.Starter {

    @NonNull
    private final AndroidComponent mComponent;

    @Inject
    SourcesStarter(@NonNull AndroidComponent component) {
        mComponent = component;
    }

    @Override
    public void openArticles(@NonNull NewsSourceDto source) {
        Intent intent = ArticlesActivity.newIntent(mComponent.getContext(), source);
        mComponent.startActivity(intent);
    }
}
