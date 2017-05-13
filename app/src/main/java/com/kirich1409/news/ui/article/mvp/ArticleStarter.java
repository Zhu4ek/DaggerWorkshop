package com.kirich1409.news.ui.article.mvp;

import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.customtabs.CustomTabsIntent;

import com.kirich1409.news.dagger.FragmentScope;
import com.kirich1409.news.mvp.AndroidComponent;

import javax.inject.Inject;

@FragmentScope
public class ArticleStarter implements ArticleContract.Starter {

    private final AndroidComponent mAndroidComponent;

    @Inject
    ArticleStarter(AndroidComponent androidComponent) {
        mAndroidComponent = androidComponent;
    }

    @Override
    public void openWebPage(@NonNull String url) {
        new CustomTabsIntent.Builder()
                .build()
                .launchUrl(mAndroidComponent.getContext(), Uri.parse(url));
    }
}
