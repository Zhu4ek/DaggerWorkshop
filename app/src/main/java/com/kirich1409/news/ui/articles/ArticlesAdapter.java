package com.kirich1409.news.ui.articles;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.kirich1409.news.R;
import com.kirich1409.news.network.data.ArticleDto;
import com.kirich1409.news.widget.ArrayAdapter;

import org.threeten.bp.OffsetDateTime;
import org.threeten.bp.format.DateTimeFormatter;
import org.threeten.bp.format.FormatStyle;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author kirylrozau
 */

class ArticlesAdapter extends ArrayAdapter<ArticleDto, ArticlesAdapter.ViewHolder> {

    @Nullable
    private final Listener<ArticleDto, ViewHolder> mListener;

    ArticlesAdapter(@NonNull Context context,
                    @NonNull List<ArticleDto> articles,
                    @Nullable Listener<ArticleDto, ViewHolder> listener) {
        super(context, articles);
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ViewHolder holder = ViewHolder.inflate(getLayoutInflater(), parent);
        holder.setListener(mListener);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.setArticle(getItem(position));
    }

    static final class ViewHolder extends RecyclerView.ViewHolder {

        private static final DateTimeFormatter DATE_TIME_FORMATTER =
                DateTimeFormatter.ofLocalizedDateTime(FormatStyle.LONG, FormatStyle.SHORT);

        @Nullable
        private Listener<ArticleDto, ViewHolder> mListener;

        static ViewHolder inflate(@NonNull LayoutInflater layoutInflater,
                                  @Nullable ViewGroup parent) {
            View view = layoutInflater.inflate(R.layout.item_article, parent, false);
            return new ViewHolder(view);
        }

        @BindView(R.id.image)
        ImageView mImageView;

        @BindView(R.id.title)
        TextView mTitleView;

        @BindView(R.id.short_description)
        TextView mShortDescriptionView;

        @BindView(R.id.date)
        TextView mDateView;

        @Nullable
        private ArticleDto mArticle;

        private ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener((view) -> {
                if (mListener != null
                        && mArticle != null) {
                    mListener.onItemSelected(ViewHolder.this, mArticle);
                }
            });
        }

        void setArticle(@NonNull ArticleDto article) {
            itemView.setEnabled(hasContent(article));
            mArticle = article;
            mTitleView.setText(article.getTitle());
            mShortDescriptionView.setText(article.getDescription());
            setPublished(article.getPublished());
            loadImage(article);
        }

        private static boolean hasContent(@NonNull ArticleDto article) {
            return !TextUtils.isEmpty(article.getDescription())
                    && !TextUtils.isEmpty(article.getImageUrl());
        }

        private void setPublished(@Nullable OffsetDateTime published) {
            if (published == null) {
                mDateView.setVisibility(View.GONE);
                mDateView.setText(null);
            } else {
                mDateView.setText(DATE_TIME_FORMATTER.format(published));
                mDateView.setVisibility(View.VISIBLE);
            }
        }

        private void loadImage(@NonNull ArticleDto article) {
            if (TextUtils.isEmpty(article.getImageUrl())) {
                mImageView.setVisibility(View.GONE);
                mImageView.setImageDrawable(null);
            } else {
                mImageView.setVisibility(View.VISIBLE);
                Glide.with((Activity) mImageView.getContext())
                        .load(article.getImageUrl())
                        .into(mImageView);
            }
        }

        void setListener(@Nullable Listener<ArticleDto, ViewHolder> listener) {
            mListener = listener;
        }
    }
}
