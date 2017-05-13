package com.kirich1409.news.mvp;

import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

/**
 * @author kirylrozau
 */

public abstract class MVPSupportFragment<V extends MVPView> extends Fragment {

    private static final String STATE_PRESENTER = "presenter";

    public MVPSupportFragment() {
        setRetainInstance(true);
    }

    @CallSuper
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null
                && savedInstanceState.containsKey(STATE_PRESENTER)) {
            Bundle presenterState = savedInstanceState.getBundle(STATE_PRESENTER);
            if (presenterState == null) {
                throw new NullPointerException("Presenter state is null");
            }
            getPresenter().onRestoreInstanceState(presenterState);
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
        getPresenter().attachView((V) this);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        getPresenter().detachView();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        getPresenter().onDestroyView();
    }

    @CallSuper
    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        Bundle presenterState = getPresenter().saveInstanceState();
        if (presenterState != null) {
            outState.putBundle(STATE_PRESENTER, presenterState);
        }
    }

    protected abstract MVPPresenter<V> getPresenter();
}
