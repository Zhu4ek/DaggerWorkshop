package com.kirich1409.news.mvp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * @authror Kirill Rozov
 * @date 1/5/17.
 */

public class ContextComponent implements AndroidComponent {

    @NonNull
    private final Context mContext;

    public ContextComponent(@NonNull final Context context) {
        mContext = context;
    }

    @Override
    public void startActivity(@NonNull final Intent intent) {
        mContext.startActivity(intent);
    }

    @Override
    public void startActivity(@NonNull final Intent intent, @Nullable final Bundle options) {
        mContext.startActivity(intent, options);
    }

    @Override
    public void startActivityForResult(@NonNull final Intent intent, final int requestCode) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void startActivityForResult(@NonNull final Intent intent,
                                       final int requestCode,
                                       @Nullable final Bundle options) {
        throw new UnsupportedOperationException();
    }

    @NonNull
    @Override
    public Context getContext() {
        return mContext;
    }
}
