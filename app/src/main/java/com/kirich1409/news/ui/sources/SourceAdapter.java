package com.kirich1409.news.ui.sources;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.kirich1409.news.network.data.NewsSourceDto;

import java.util.List;

/**
 * @author kirylrozau
 */

class SourceAdapter extends RecyclerView.Adapter<SourceViewHolder> {

    private final LayoutInflater mLayoutInflater;

    private final List<NewsSourceDto> mSources;

    private final Listener mListener;

    SourceAdapter(@NonNull Context context,
                  @NonNull List<NewsSourceDto> sources,
                  @Nullable Listener listener) {
        mLayoutInflater = LayoutInflater.from(context);
        mSources = sources;
        mListener = listener;
    }

    @Override
    public SourceViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        SourceViewHolder holder = SourceViewHolder.inflate(mLayoutInflater, parent);
        holder.setListener(mListener);
        return holder;
    }

    @Override
    public void onBindViewHolder(SourceViewHolder holder, int position) {
        holder.setSource(mSources.get(position));
    }

    @Override
    public int getItemCount() {
        return mSources.size();
    }

    interface Listener {

        void onSourceClicked(@NonNull RecyclerView.ViewHolder viewHolder,
                             @NonNull NewsSourceDto source);
    }
}
