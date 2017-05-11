package com.kirich1409.news.ui.sources.mvp;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.kirich1409.news.dagger.RxModule;
import com.kirich1409.news.mvp.BasePresenter;
import com.kirich1409.news.network.NewsSourcesDataSource;
import com.kirich1409.news.network.data.NewsSourceDto;
import com.kirich1409.news.network.data.NewsSourcesResponseDto;
import com.kirich1409.news.util.Exceptions;
import com.kirich1409.news.util.RxUtils;

import javax.inject.Inject;
import javax.inject.Named;

import dagger.Lazy;
import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.disposables.Disposable;

/**
 * @authror Kirill Rozov
 * @date 1/5/17.
 */
final class SourcesPresenter extends BasePresenter<SourcesContract.View>
        implements SourcesContract.Presenter {

    @NonNull
    private final Lazy<SourcesContract.Starter> mStarter;

    @NonNull
    private final NewsSourcesDataSource mDataSource;

    @Nullable
    private Disposable mNewsSourcesDisposable;

    private final Scheduler mComputationScheduler;
    private final Scheduler mObserverScheduler;

    private boolean mDataLoaded;
    private boolean mLoadingData;

    @Inject
    SourcesPresenter(@NonNull Lazy<SourcesContract.Starter> starter,
                     @NonNull NewsSourcesDataSource dataSource,
                     @NonNull @Named(RxModule.COMPUTATION) Scheduler computationScheduler,
                     @NonNull @Named(RxModule.MAIN) Scheduler observerScheduler) {
        mStarter = starter;
        mDataSource = dataSource;
        mComputationScheduler = computationScheduler;
        mObserverScheduler = observerScheduler;
    }

    @Override
    protected void onAttachView() {
        loadSources();

        if (!mDataLoaded
                && !mLoadingData) {
            mLoadingData = true;
            getView().setProgressVisible(true);
            mDataSource.loadNewsSources();
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
        mStarter.get().openArticles(source);
    }

    private void loadSources() {
        if (mNewsSourcesDisposable != null
                && !mNewsSourcesDisposable.isDisposed()) {
            return;
        }

        mDataSource.getNewsSourcesSubscription()
                .subscribeOn(mComputationScheduler)
                .observeOn(mObserverScheduler)
                .doFinally(() -> mLoadingData = false)
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
                mDataLoaded = true;

            } else if (NewsSourcesResponseDto.STATUS_ERROR.equals(response.getStatus())) {
                showError(response);

            } else {
                throw Exceptions.newUnsupportedOperation(
                        "Unknown status='%s'", response.getStatus());
            }
        }

        @Override
        public void onError(Throwable error) {
            SourcesContract.View view = getView();
            view.setProgressVisible(false);
            view.showError(error.getMessage());
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
