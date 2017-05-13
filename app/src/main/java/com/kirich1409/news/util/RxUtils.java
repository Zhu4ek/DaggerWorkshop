package com.kirich1409.news.util;

import android.support.annotation.Nullable;

import io.reactivex.disposables.Disposable;

/**
 * @author kirylrozau
 */

public final class RxUtils {

    public static void dispose(@Nullable Disposable disposable) {
        if (disposable != null
                && !disposable.isDisposed()) {
            disposable.dispose();
        }
    }

    public static boolean isDisposedOrNull(@Nullable Disposable disposable) {
        return disposable == null || disposable.isDisposed();
    }

    private RxUtils() {
        throw new UnsupportedOperationException();
    }
}
