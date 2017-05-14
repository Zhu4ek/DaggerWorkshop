package com.kirich1409.news.ui.articles.mvp;

import android.support.annotation.NonNull;
import android.text.TextUtils;

import com.kirich1409.news.mvp.BasePresenter;
import com.kirich1409.news.network.ArticlesDataSource;
import com.kirich1409.news.network.data.ArticleDto;
import com.kirich1409.news.network.data.ArticlesResponseDto;
import com.kirich1409.news.network.data.NewsSourceDto;
import com.kirich1409.news.network.data.NewsSourcesResponseDto;
import com.kirich1409.news.util.Exceptions;
import com.kirich1409.news.util.RxUtils;

import org.threeten.bp.OffsetDateTime;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.disposables.Disposable;

/**
 * @author Kirill Rozov
 */

public class ArticlesPresenter extends BasePresenter<ArticlesContract.View>
        implements ArticlesContract.Presenter {

    private ArticlesDataSource mDataSource;
    Disposable mArticlesDisposable;
    private Scheduler mComputationScheduler;
    private Scheduler mObserverScheduler;
    private boolean mLoadingData;
    private NewsSourceDto mSource;

    @Override
    protected void onAttachView() {
        ArticlesContract.View view = getView();
        view.setTitle(mSource.getName());
        view.setOpenSourceWebPageEnable(!TextUtils.isEmpty(mSource.getUrl()));

        subscribeToArticlesUpdates();

        if (!mLoadingData) {
            mLoadingData = true;
            view.setProgressVisible(true);
            mDataSource.load();
        }
    }

    private void subscribeToArticlesUpdates() {
        if (!RxUtils.isDisposedOrNull(mArticlesDisposable)) {
            return;
        }

        mDataSource.getSubscription()
                .subscribeOn(mComputationScheduler)
                .observeOn(mObserverScheduler)
                .subscribe(new ArticlesObserver());
    }

    @Override
    protected void onDetachView() {
        RxUtils.dispose(mArticlesDisposable);
        mArticlesDisposable = null;
    }

    @Override
    public void onDestroyView() {
        mDataSource.unsubscribe();
    }

    @Override
    public void openArticle(@NonNull ArticleDto article) {
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    public void openSourceWebPage() {
        throw new UnsupportedOperationException("Not implemented");
    }

    private class ArticlesObserver implements Observer<ArticlesResponseDto> {

        private final Comparator<ArticleDto> mArticleComparator =
                Collections.reverseOrder(new ArticlesDateComparator());

        @Override
        public void onSubscribe(@NonNull Disposable d) {
            mArticlesDisposable = d;
        }

        @Override
        public void onNext(@NonNull ArticlesResponseDto response) {
            if (ArticlesResponseDto.STATUS_OK.equals(response.getStatus())) {
                ArticlesContract.View view = getView();
                view.setProgressVisible(false);

                List<ArticleDto> articles = new ArrayList<>(response.getArticles());
                Collections.sort(articles, mArticleComparator);
                view.setArticles(articles);

            } else if (NewsSourcesResponseDto.STATUS_ERROR.equals(response.getStatus())) {
                showError(response);

            } else {
                throw Exceptions.newUnsupportedOperation(
                        "Unknown status='%s'", response.getStatus());
            }

            mLoadingData = false;
        }

        @Override
        public void onError(@NonNull Throwable e) {
            getView().showError(e.getMessage());
        }

        @Override
        public void onComplete() {
        }

        private void showError(@NonNull ArticlesResponseDto response) {
            String errorMessage = response.getErrorMessage();
            if (errorMessage == null) {
                errorMessage = "Unknown error";
            }
            getView().showError(errorMessage);
        }
    }

    private static final class ArticlesDateComparator implements Comparator<ArticleDto> {

        @Override
        public int compare(@NonNull ArticleDto o1, @NonNull ArticleDto o2) {
            OffsetDateTime p1 = o1.getPublished(), p2 = o2.getPublished();
            if (p1 == null && p2 == null) {
                return 0;

            } else if (p1 == null) {
                return 1;

            } else if (p2 == null) {
                return -1;

            } else {
                return p1.compareTo(p2);
            }
        }
    }
}
