package com.kirich1409.news.mvp;

import android.support.annotation.CallSuper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * @authror Kirill Rozov
 * @date 1/5/17.
 */

public abstract class BasePresenter<V extends MVPView> implements Presenter<V> {

    @Nullable
    private V mView;

    @CallSuper
    @Override
    public void onAttachView(@NonNull final V view) {
        mView = view;
    }

    @CallSuper
    @Override
    public void onDetachView() {
        mView = null;
    }

    @Nullable
    protected final V getView() {
        return mView;
    }
}
