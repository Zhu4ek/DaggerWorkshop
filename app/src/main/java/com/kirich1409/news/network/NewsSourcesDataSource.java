package com.kirich1409.news.network;

import android.support.annotation.NonNull;

import com.kirich1409.news.network.data.NewsSourcesResponseDto;
import com.kirich1409.news.util.RxUtils;

import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.SingleObserver;
import io.reactivex.disposables.Disposable;
import io.reactivex.subjects.BehaviorSubject;
import io.reactivex.subjects.Subject;

/**
 * @author kirylrozau
 */
public final class NewsSourcesDataSource {

    private NewsSourcesRestService mService;
    private Scheduler mNetworkScheduler;
    final Subject<NewsSourcesResponseDto> mSubject = BehaviorSubject.create();
    Disposable mDisposable;

    public Observable<NewsSourcesResponseDto> getSubscription() {
        return mSubject.serialize();
    }

    public void load() {
        if (!RxUtils.isDisposedOrNull(mDisposable)) {
            return;
        }

        mService.sources()
                .subscribeOn(mNetworkScheduler)
                .subscribe(new NewsSourcesObserver());
    }

    public void unsubscribe() {
        RxUtils.dispose(mDisposable);
        mDisposable = null;
    }

    private class NewsSourcesObserver implements SingleObserver<NewsSourcesResponseDto> {

        @Override
        public void onSubscribe(@NonNull Disposable disposable) {
            mDisposable = disposable;
        }

        @Override
        public void onSuccess(@NonNull NewsSourcesResponseDto newsSourcesResponseDto) {
            mSubject.onNext(newsSourcesResponseDto);
        }

        @Override
        public void onError(@NonNull Throwable error) {
            mSubject.onError(error);
        }
    }
}
