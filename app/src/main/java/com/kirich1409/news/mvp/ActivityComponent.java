package com.kirich1409.news.mvp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * @authror Kirill Rozov
 * @date 1/5/17.
 */

public class ActivityComponent extends ContextComponent {

    @NonNull
    private final Activity mActivity;

    public ActivityComponent(@NonNull final Activity activity) {
        super(activity);
        mActivity = activity;
    }

    @Override
    public void startActivityForResult(@NonNull final Intent intent, final int requestCode) {
        mActivity.startActivityForResult(intent, requestCode);
    }

    @Override
    public void startActivityForResult(@NonNull final Intent intent,
                                       final int requestCode,
                                       @Nullable final Bundle options) {
        mActivity.startActivityForResult(intent, requestCode, options);
    }

    @NonNull
    @Override
    public Activity getContext() {
        return mActivity;
    }
}
