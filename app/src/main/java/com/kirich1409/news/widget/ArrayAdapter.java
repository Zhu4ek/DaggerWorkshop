package com.kirich1409.news.widget;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;

import java.util.ArrayList;
import java.util.List;

/**
 * @author kirylrozau
 */

public abstract class ArrayAdapter<E, VH extends RecyclerView.ViewHolder>
        extends RecyclerView.Adapter<VH> {

    private final LayoutInflater mLayoutInflater;

    private final List<E> mData;

    protected ArrayAdapter(@NonNull Context context,
                           @NonNull List<E> data) {
        mLayoutInflater = LayoutInflater.from(context);
        mData = new ArrayList<>(data);
    }

    protected final LayoutInflater getLayoutInflater() {
        return mLayoutInflater;
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public E getItem(int position) {
        return mData.get(position);
    }

    public interface Listener<E, VH extends RecyclerView.ViewHolder> {

        void onItemSelected(@NonNull VH viewHolder, @NonNull E item);
    }
}
