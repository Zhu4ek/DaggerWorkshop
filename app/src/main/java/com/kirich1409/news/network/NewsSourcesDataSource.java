package com.kirich1409.news.network;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.kirich1409.news.dagger.RxModule;
import com.kirich1409.news.network.data.NewsSourcesResponseDto;
import com.kirich1409.news.util.RxUtils;

import javax.inject.Inject;
import javax.inject.Named;

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

    private final NewsSourcesRestService mService;
    private final Scheduler mNetworkScheduler;
    final Subject<NewsSourcesResponseDto> mSubject = BehaviorSubject.create();

    @Nullable
    Disposable mDisposable;

    @Inject
    NewsSourcesDataSource(@NonNull NewsSourcesRestService service,
                          @NonNull @Named(RxModule.NETWORK) Scheduler networkScheduler) {
        mService = service;
        mNetworkScheduler = networkScheduler;
    }

    public Observable<NewsSourcesResponseDto> getSubscription() {
        return mSubject.serialize();
    }

    public void load() {
        if (!RxUtils.isDisposedOrNull(mDisposable)) {
            return;
        }

        mService.sources()
                .subscribeOn(mNetworkScheduler)
                .observeOn(mNetworkScheduler)
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
