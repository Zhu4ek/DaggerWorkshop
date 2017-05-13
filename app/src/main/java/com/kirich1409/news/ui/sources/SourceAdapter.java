package com.kirich1409.news.ui.sources;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.kirich1409.news.R;
import com.kirich1409.news.network.data.NewsSourceDto;
import com.kirich1409.news.widget.ArrayAdapter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author kirylrozau
 */

class SourceAdapter extends ArrayAdapter<NewsSourceDto, SourceAdapter.ViewHolder> {

    @Nullable
    private final Listener<NewsSourceDto, ViewHolder> mListener;

    SourceAdapter(@NonNull Context context,
                  @NonNull List<NewsSourceDto> sources,
                  @Nullable Listener<NewsSourceDto, ViewHolder> listener) {
        super(context, sources);
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
        holder.setSource(getItem(position));
    }

    static final class ViewHolder extends RecyclerView.ViewHolder {

        @Nullable
        private Listener<NewsSourceDto, ViewHolder> mListener;

        static ViewHolder inflate(@NonNull LayoutInflater layoutInflater,
                                  @Nullable ViewGroup parent) {
            View view = layoutInflater.inflate(R.layout.item_source, parent, false);
            return new ViewHolder(view);
        }

        @BindView(R.id.icon)
        ImageView mImageView;

        @BindView(R.id.name)
        TextView mNameView;

        @Nullable
        private NewsSourceDto mSource;

        private ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener((view) -> {
                if (mListener != null
                        && mSource != null) {
                    mListener.onItemSelected(ViewHolder.this, mSource);
                }
            });
        }

        void setSource(@NonNull NewsSourceDto source) {
            mSource = source;
            mNameView.setText(source.getName());

            if (source.getLogoUrl() == null) {
                mImageView.setVisibility(View.GONE);
                mImageView.setImageDrawable(null);
            } else {
                mImageView.setVisibility(View.VISIBLE);
                Glide.with((Activity) itemView.getContext())
                        .load(source.getLogoUrl())
                        .into(mImageView);
            }
        }

        void setListener(@Nullable Listener<NewsSourceDto, ViewHolder> listener) {
            mListener = listener;
        }
    }
}
