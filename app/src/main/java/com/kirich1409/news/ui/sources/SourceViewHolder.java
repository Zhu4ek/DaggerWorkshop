package com.kirich1409.news.ui.sources;

import android.app.Activity;
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

import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author kirylrozau
 */

final class SourceViewHolder extends RecyclerView.ViewHolder {

    @Nullable
    private SourceAdapter.Listener mListener;

    static SourceViewHolder inflate(@NonNull LayoutInflater layoutInflater,
                                    @Nullable ViewGroup parent) {
        View view = layoutInflater.inflate(R.layout.item_source, parent, false);
        return new SourceViewHolder(view);
    }

    @BindView(R.id.icon)
    ImageView mImageView;

    @BindView(R.id.name)
    TextView mNameView;

    @Nullable
    private NewsSourceDto mSource;

    private SourceViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        itemView.setOnClickListener((view) -> {
            if (mListener != null
                    && mSource != null) {
                mListener.onSourceClicked(SourceViewHolder.this, mSource);
            }
        });
    }

    void setSource(@NonNull NewsSourceDto source) {
        mSource = source;
        mNameView.setText(source.getName());

        Map<String, String> logosUrl = source.getLogosUrl();
        if (logosUrl.isEmpty()) {
            mImageView.setVisibility(View.GONE);
            mImageView.setImageDrawable(null);
        } else {
            mImageView.setVisibility(View.VISIBLE);
            Glide.with((Activity) itemView.getContext())
                    .load(logosUrl.get(logosUrl.keySet().iterator().next()))
                    .into(mImageView);
        }
    }

    void setListener(@Nullable SourceAdapter.Listener listener) {
        mListener = listener;
    }
}
