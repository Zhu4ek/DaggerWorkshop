package com.kirich1409.news.ui.articles.mvp;

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.customtabs.CustomTabsIntent;
import android.text.TextUtils;

import com.kirich1409.news.mvp.AndroidComponent;
import com.kirich1409.news.network.data.ArticleDto;
import com.kirich1409.news.ui.article.ArticleActivity;

import javax.inject.Inject;

public class ArticlesStarter implements ArticlesContract.Starter {

    private final AndroidComponent mAndroidComponent;

    @Inject
    ArticlesStarter(AndroidComponent androidComponent) {
        mAndroidComponent = androidComponent;
    }

    @Override
    public void openSourceWebPage(@NonNull String url) {
        openUrl(url);
    }

    private void openUrl(@NonNull String url) {
        new CustomTabsIntent.Builder()
                .build()
                .launchUrl(mAndroidComponent.getContext(), Uri.parse(url));
    }

    @Override
    public void openArticle(@NonNull ArticleDto article) {
        if (TextUtils.isEmpty(article.getDescription())) {
            if (TextUtils.isEmpty(article.getUrl())) {
                throw new IllegalArgumentException("Article doesn't contains url or description");
            }

            openUrl(article.getUrl());
        } else {
            Intent intent = ArticleActivity.newIntent(mAndroidComponent.getContext(), article);
            mAndroidComponent.startActivity(intent);
        }
    }
}
