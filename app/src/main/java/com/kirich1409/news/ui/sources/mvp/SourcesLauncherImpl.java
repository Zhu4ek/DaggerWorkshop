package com.kirich1409.news.ui.sources.mvp;

import android.content.Intent;
import android.support.annotation.NonNull;

import com.kirich1409.news.mvp.AndroidComponent;
import com.kirich1409.news.network.data.NewsSourceDto;
import com.kirich1409.news.ui.articles.ArticlesActivity;

import javax.inject.Inject;

import dagger.Lazy;

/**
 * Created by Kirill Rozov on 5/3/17.
 */
class SourcesLauncherImpl implements SourcesLauncher {

    @NonNull
    private final Lazy<AndroidComponent> mComponent;

    @Inject
    SourcesLauncherImpl(@NonNull Lazy<AndroidComponent> component) {
        mComponent = component;
    }

    @Override
    public void openArticles(@NonNull NewsSourceDto source) {
        AndroidComponent component = mComponent.get();
        Intent intent = ArticlesActivity.newIntent(component.getContext(), source.getId());
        component.startActivity(intent);
    }
}
