package com.kirich1409.news.ui.articles;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.kirich1409.news.R;
import com.kirich1409.news.mvp.MVPSupportFragment;
import com.kirich1409.news.network.data.ArticleDto;
import com.kirich1409.news.ui.articles.mvp.ArticlesContract;

import java.util.List;
import java.util.Objects;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class ArticlesFragment extends MVPSupportFragment<ArticlesContract.View>
        implements ArticlesContract.View, ArticlesAdapter.Listener<ArticleDto, ArticlesAdapter.ViewHolder> {

    @Inject
    ArticlesContract.Presenter mPresenter;

    @BindView(R.id.list)
    RecyclerView mRecyclerView;

    @BindView(R.id.progress)
    View mProgressView;

    @Nullable
    private Unbinder mUnbinder;

    @Nullable
    private MenuItem mOpenSourceWebPageMenuItem;

    private boolean mOpenSourceWebPageMenuItemVisible;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        ((ArticlesActivity) getContext())
                .getActivityComponent()
                .articlesFragmentComponent()
                .articlesFragmentModule(new ArticlesFragmentModule(this))
                .build()
                .inject(this);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull final LayoutInflater inflater,
                             @Nullable final ViewGroup container,
                             @Nullable final Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_articles, container, false);
    }

    @Override
    public void onViewCreated(@NonNull final View view,
                              @Nullable final Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mUnbinder = ButterKnife.bind(this, view);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.fragment_articles, menu);
        mOpenSourceWebPageMenuItem = menu.findItem(R.id.menu_web_page);
        Objects.requireNonNull(mOpenSourceWebPageMenuItem)
                .setVisible(mOpenSourceWebPageMenuItemVisible);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_web_page:
                mPresenter.openSourceWebPage();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onDestroyView() {
        mOpenSourceWebPageMenuItem = null;
        if (mUnbinder != null) {
            mUnbinder.unbind();
            mUnbinder = null;
        }
        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mPresenter = null;
    }

    @Override
    public ArticlesContract.Presenter getPresenter() {
        return mPresenter;
    }

    @Override
    public void showError(@NonNull String errorMessage) {
        Toast.makeText(getContext(), errorMessage, Toast.LENGTH_LONG)
                .show();
    }

    @Override
    public void setTitle(@NonNull CharSequence title) {
        getActivity().setTitle(title);
    }

    @Override
    public void setOpenSourceWebPageEnable(boolean enable) {
        mOpenSourceWebPageMenuItemVisible = enable;
        if (mOpenSourceWebPageMenuItem != null) {
            mOpenSourceWebPageMenuItem.setVisible(enable);
        }
    }

    @Override
    public void setArticles(@NonNull List<ArticleDto> articles) {
        mRecyclerView.setAdapter(new ArticlesAdapter(getContext(), articles, this));
    }

    @Override
    public void setProgressVisible(final boolean visible) {
        mProgressView.setVisibility(visible ? View.VISIBLE : View.GONE);
    }

    @Override
    public void onItemSelected(@NonNull ArticlesAdapter.ViewHolder viewHolder,
                               @NonNull ArticleDto article) {
        mPresenter.openArticle(article);
    }
}
