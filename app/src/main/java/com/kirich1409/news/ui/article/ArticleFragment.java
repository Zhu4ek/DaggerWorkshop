package com.kirich1409.news.ui.article;


import android.app.Activity;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.kirich1409.news.R;
import com.kirich1409.news.mvp.MVPSupportFragment;
import com.kirich1409.news.network.data.ArticleDto;
import com.kirich1409.news.ui.article.mvp.ArticleContract;

import org.threeten.bp.format.DateTimeFormatter;
import org.threeten.bp.format.FormatStyle;

import java.util.Objects;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class ArticleFragment extends MVPSupportFragment<ArticleContract.View>
        implements ArticleContract.View {

    private static final DateTimeFormatter DATE_TIME_FORMATTER =
            DateTimeFormatter.ofLocalizedDateTime(FormatStyle.LONG, FormatStyle.SHORT);

    @Inject
    ArticleContract.Presenter mPresenter;

    @BindView(R.id.description)
    TextView mDescriptionView;

    @BindView(R.id.date)
    TextView mDateView;

    @BindView(R.id.collapsing_toolbar)
    CollapsingToolbarLayout mCollapsingToolbarLayout;

    @BindView(R.id.image)
    ImageView mImageView;

    @Nullable
    private Unbinder mUnbinder;

    @Nullable
    private MenuItem mOpenWebPageMenuItem;
    private boolean mOpenWebPageMenuItemVisible;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        ((ArticleActivity) getActivity()).getActivityComponent()
                .articleFragmentComponent()
                .articleFragmentModule(new ArticleFragmentModule(this))
                .build()
                .inject(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_article, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mUnbinder = ButterKnife.bind(this, view);
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
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.fragment_articles, menu);
        mOpenWebPageMenuItem = menu.findItem(R.id.menu_web_page);
        Objects.requireNonNull(mOpenWebPageMenuItem)
                .setVisible(mOpenWebPageMenuItemVisible);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_web_page:
                mPresenter.openWebPage();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected final ArticleContract.Presenter getPresenter() {
        return mPresenter;
    }

    @Override
    public void setArticle(@NonNull ArticleDto article) {
        Activity activity = getActivity();
        if (activity != null) {
            activity.setTitle(article.getTitle());
        }
        mCollapsingToolbarLayout.setTitle(article.getTitle());
        mDescriptionView.setText(article.getDescription());

        if (article.getPublished() != null) {
            mDateView.setText(DATE_TIME_FORMATTER.format(article.getPublished()));
            mDateView.setVisibility(View.VISIBLE);
        } else {
            mDateView.setVisibility(View.GONE);
        }

        if (TextUtils.isEmpty(article.getImageUrl())) {
            mImageView.setImageDrawable(
                    new ColorDrawable(ContextCompat.getColor(getContext(), R.color.colorPrimary)));
        } else {
            Glide.with(getActivity())
                    .load(article.getImageUrl())
                    .into(mImageView);
        }
    }

    @Override
    public void setOpenWebPageEnable(boolean enable) {
        mOpenWebPageMenuItemVisible = enable;
        if (mOpenWebPageMenuItem != null) {
            mOpenWebPageMenuItem.setVisible(enable);
        }
    }
}
