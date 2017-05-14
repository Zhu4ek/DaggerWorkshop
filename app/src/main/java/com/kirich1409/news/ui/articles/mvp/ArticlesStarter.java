package com.kirich1409.news.ui.articles.mvp;

import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.customtabs.CustomTabsIntent;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;

import com.kirich1409.news.R;
import com.kirich1409.news.mvp.AndroidComponent;
import com.kirich1409.news.network.data.ArticleDto;
import com.kirich1409.news.util.Exceptions;

public class ArticlesStarter implements ArticlesContract.Starter {

    private AndroidComponent mAndroidComponent;

    @Override
    public void openSourceWebPage(@NonNull String url) {
        openUrl(url);
    }

    private void openUrl(@NonNull String url) {
        int primaryColor = ContextCompat.getColor(mAndroidComponent.getContext(), R.color.primary);
        new CustomTabsIntent.Builder()
                .setToolbarColor(primaryColor)
                .build()
                .launchUrl(mAndroidComponent.getContext(), Uri.parse(url));
    }

    @Override
    public void openArticle(@NonNull ArticleDto article) {
        if (TextUtils.isEmpty(article.getUrl())) {
            throw Exceptions.newIllegalArgument(
                    "Article '%s' doesn't contain link to web page", article);
        }

        openUrl(article.getUrl());
    }
}
