package com.kirich1409.news.ui.sources;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.kirich1409.news.R;
import com.kirich1409.news.mvp.MVPSupportFragment;
import com.kirich1409.news.network.data.NewsSourceDto;
import com.kirich1409.news.ui.sources.mvp.SourcesContract;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import dagger.android.support.AndroidSupportInjection;

/**
 * @authror Kirill Rozov
 * @date 1/5/17.
 */

public class SourcesFragment extends MVPSupportFragment<SourcesContract.View>
        implements SourcesContract.View, SourceAdapter.Listener<NewsSourceDto, SourceAdapter.ViewHolder> {

    @Inject
    SourcesContract.Presenter mPresenter;

    @BindView(R.id.list)
    RecyclerView mRecyclerView;

    @BindView(R.id.progress)
    View mProgressView;

    @Nullable
    private Unbinder mUnbinder;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        AndroidSupportInjection.inject(this);
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull final LayoutInflater inflater,
                                          @Nullable final ViewGroup container,
                                          @Nullable final Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_sources, container, false);
    }

    @Override
    public void onViewCreated(@NonNull final android.view.View view,
                              @Nullable final Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mUnbinder = ButterKnife.bind(this, view);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.addItemDecoration(
                new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (mUnbinder != null) {
            mUnbinder.unbind();
            mUnbinder = null;
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mPresenter = null;
    }

    @Override
    public SourcesContract.Presenter getPresenter() {
        return mPresenter;
    }

    @Override
    public void showError(@NonNull String errorMessage) {
        Toast.makeText(getContext(), errorMessage, Toast.LENGTH_LONG).show();
    }

    @Override
    public void setSources(@NonNull final List<NewsSourceDto> sources) {
        mRecyclerView.setAdapter(new SourceAdapter(getContext(), sources, this));
    }

    @Override
    public void setProgressVisible(final boolean visible) {
        mProgressView.setVisibility(visible ? View.VISIBLE : View.GONE);
    }

    @Override
    public void onItemSelected(@NonNull SourceAdapter.ViewHolder viewHolder,
                               @NonNull NewsSourceDto source) {
        mPresenter.openArticles(source);
    }
}
