package com.kirich1409.news.ui.sources;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kirich1409.news.R;
import com.kirich1409.news.network.data.NewsSourceDto;
import com.kirich1409.news.ui.sources.mvp.SourcesPresenter;
import com.kirich1409.news.ui.sources.mvp.SourcesView;

import java.util.List;

import javax.inject.Inject;

import dagger.android.support.AndroidSupportInjection;

/**
 * @authror Kirill Rozov
 * @date 1/5/17.
 */

public class SourcesFragment extends Fragment implements SourcesView {

    @Inject
    SourcesPresenter mPresenter;

    @Override
    public void onAttach(@NonNull final Context context) {
        AndroidSupportInjection.inject(this);
        super.onAttach(context);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull final LayoutInflater inflater,
                             @Nullable final ViewGroup container,
                             @Nullable final Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_sources, container, false);
    }

    @Override
    public void onViewCreated(@NonNull final View view,
                              @Nullable final Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mPresenter.onAttachView(this);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mPresenter.onDetachView();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mPresenter = null;
    }

    @Override
    public void setSources(@NonNull final List<NewsSourceDto> sources) {
    }

    @Override
    public void setProgressVisible(final boolean visible) {
    }
}
