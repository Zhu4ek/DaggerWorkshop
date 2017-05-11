package com.kirich1409.news.mvp;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.lang.ref.Reference;
import java.lang.ref.WeakReference;

/**
 * @authror Kirill Rozov
 * @date 1/5/17.
 */

public abstract class BasePresenter<V extends MVPView> implements MVPPresenter<V> {

    private Reference<V> mView;

    @Override
    public final void attachView(@NonNull final V view) {
        mView = new WeakReference<>(view);
        onAttachView();
    }

    protected void onAttachView() {
    }

    @Override
    public final void detachView() {
        mView = null;
        onDetachView();
    }

    @Nullable
    @Override
    public Bundle saveInstanceState() {
        return null;
    }

    @Override
    public void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
    }

    @Override
    public void onDestroyView() {
    }

    protected void onDetachView() {
    }

    protected void onDestory() {
    }

    protected final V getView() {
        return mView.get();
    }
}
