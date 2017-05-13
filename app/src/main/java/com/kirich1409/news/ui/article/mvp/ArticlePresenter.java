package com.kirich1409.news.ui.article.mvp;

import android.support.annotation.NonNull;
import android.text.TextUtils;

import com.kirich1409.news.dagger.FragmentScope;
import com.kirich1409.news.mvp.BasePresenter;
import com.kirich1409.news.network.data.ArticleDto;

import java.util.Objects;

import javax.inject.Inject;

import dagger.Lazy;

/**
 * @author Kirill Rozov
 */
@FragmentScope
public class ArticlePresenter extends BasePresenter<ArticleContract.View>
        implements ArticleContract.Presenter {

    private final ArticleDto mArticle;

    private final Lazy<ArticleContract.Starter> mStarter;

    @Inject
    ArticlePresenter(@NonNull ArticleDto article,
                     @NonNull Lazy<ArticleContract.Starter> starter) {
        mArticle = article;
        mStarter = starter;
    }

    @Override
    protected void onAttachView() {
        ArticleContract.View view = getView();
        view.setArticle(mArticle);
        view.setOpenWebPageEnable(!TextUtils.isEmpty(mArticle.getUrl()));
    }

    @Override
    public void openWebPage() {
        mStarter.get().openWebPage(Objects.requireNonNull(mArticle.getUrl()));
    }
}
