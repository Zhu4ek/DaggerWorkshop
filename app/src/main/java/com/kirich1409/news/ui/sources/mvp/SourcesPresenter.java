package com.kirich1409.news.ui.sources.mvp;

import android.support.annotation.NonNull;

import com.kirich1409.news.mvp.BasePresenter;
import com.kirich1409.news.network.NewsSourcesDataSource;
import com.kirich1409.news.network.data.NewsSourceDto;
import com.kirich1409.news.network.data.NewsSourcesResponseDto;
import com.kirich1409.news.util.Exceptions;
import com.kirich1409.news.util.RxUtils;

import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.disposables.Disposable;

/**
 * @authror Kirill Rozov
 * @date 1/5/17.
 */
final class SourcesPresenter extends BasePresenter<SourcesContract.View>
        implements SourcesContract.Presenter {

    private NewsSourcesDataSource mDataSource;
    private Disposable mNewsSourcesDisposable;
    private Scheduler mComputationScheduler;
    private Scheduler mObserverScheduler;
    private boolean mLoadingData;

    @Override
    protected void onAttachView() {
        subscribeToNewsSourcesUpdates();

        if (!mLoadingData) {
            mLoadingData = true;
            getView().setProgressVisible(true);
            mDataSource.load();
        }
    }

    @Override
    protected void onDetachView() {
        RxUtils.dispose(mNewsSourcesDisposable);
        mNewsSourcesDisposable = null;
    }

    @Override
    public void onDestroyView() {
        mDataSource.unsubscribe();
    }

    @Override
    public void openArticles(@NonNull NewsSourceDto source) {
        throw new UnsupportedOperationException("Not implemented");
    }

    private void subscribeToNewsSourcesUpdates() {
        if (!RxUtils.isDisposedOrNull(mNewsSourcesDisposable)) {
            return;
        }

        mDataSource.getSubscription()
                .subscribeOn(mComputationScheduler)
                .observeOn(mObserverScheduler)
                .subscribe(new NewsSourcesObserver());
    }

    private class NewsSourcesObserver implements Observer<NewsSourcesResponseDto> {

        @Override
        public void onSubscribe(@NonNull Disposable disposable) {
            mNewsSourcesDisposable = disposable;
        }

        @Override
        public void onNext(@NonNull NewsSourcesResponseDto response) {
            if (NewsSourcesResponseDto.STATUS_OK.equals(response.getStatus())) {
                SourcesContract.View view = getView();
                view.setProgressVisible(false);
                view.setSources(response.getSources());

            } else if (NewsSourcesResponseDto.STATUS_ERROR.equals(response.getStatus())) {
                showError(response);

            } else {
                throw Exceptions.newUnsupportedOperation(
                        "Unknown status='%s'", response.getStatus());
            }

            mLoadingData = false;
        }

        @Override
        public void onError(Throwable error) {
            SourcesContract.View view = getView();
            view.setProgressVisible(false);
            view.showError(error.getMessage());
            mLoadingData = false;
        }

        @Override
        public void onComplete() {
        }

        private void showError(@NonNull NewsSourcesResponseDto response) {
            String errorMessage = response.getErrorMessage();
            if (errorMessage == null) {
                errorMessage = "Unknown error";
            }
            getView().showError(errorMessage);
        }
    }
}
