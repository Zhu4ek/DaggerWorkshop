package com.kirich1409.news.ui.sources;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Toast;

import com.kirich1409.news.R;
import com.kirich1409.news.mvp.MVPSupportFragment;
import com.kirich1409.news.network.data.NewsSourceDto;
import com.kirich1409.news.ui.sources.mvp.SourcesContract;

import java.util.List;

import javax.inject.Inject;

import dagger.android.support.AndroidSupportInjection;

/**
 * @authror Kirill Rozov
 * @date 1/5/17.
 */

public class SourcesFragment extends MVPSupportFragment<SourcesContract.View>
        implements SourcesContract.View {

    @Inject
    SourcesContract.Presenter mPresenter;

    @Override
    public void onAttach(@NonNull final Context context) {
        AndroidSupportInjection.inject(this);
        super.onAttach(context);
    }

    @Nullable
    @Override
    public android.view.View onCreateView(@NonNull final LayoutInflater inflater,
                                          @Nullable final ViewGroup container,
                                          @Nullable final Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_sources, container, false);
    }

    @Override
    public void onViewCreated(@NonNull final android.view.View view,
                              @Nullable final Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mPresenter.attachView(this);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mPresenter.detachView();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mPresenter = null;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mPresenter.onDestroyView();
    }

    @Override
    public SourcesContract.Presenter getPresenter() {
        return mPresenter;
    }

    @Override
    public void showError(@NonNull String errorMessage) {
        Toast.makeText(getContext(), errorMessage, Toast.LENGTH_LONG)
                .show();
    }

    @Override
    public void setSources(@NonNull final List<NewsSourceDto> sources) {
    }

    @Override
    public void setProgressVisible(final boolean visible) {
    }
}
