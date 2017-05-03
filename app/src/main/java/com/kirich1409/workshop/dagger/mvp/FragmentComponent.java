package com.kirich1409.workshop.dagger.mvp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import java.util.Objects;

/**
 * @authror Kirill Rozov
 * @date 1/5/17.
 */
public class FragmentComponent implements AndroidComponent {

    @NonNull
    private final Fragment mFragment;

    public FragmentComponent(@NonNull final Fragment fragment) {
        mFragment = fragment;
    }

    @Override
    public void startActivity(@NonNull final Intent intent) {
        mFragment.startActivity(intent);
    }

    @Override
    public void startActivity(@NonNull final Intent intent, @Nullable final Bundle options) {
        mFragment.startActivity(intent, options);
    }

    @Override
    public void startActivityForResult(@NonNull final Intent intent, final int requestCode) {
        mFragment.startActivityForResult(intent, requestCode);
    }

    @Override
    public void startActivityForResult(@NonNull final Intent intent,
                                       final int requestCode,
                                       @Nullable final Bundle options) {
        mFragment.startActivityForResult(intent, requestCode, options);
    }

    @NonNull
    @Override
    public Context getContext() {
        return Objects.requireNonNull(mFragment.getContext());
    }
}
