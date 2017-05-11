package com.kirich1409.news.mvp;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * @authror Kirill Rozov
 * @date 1/5/17.
 */

public interface MVPPresenter<V extends MVPView> {

    void attachView(@NonNull V view);

    void detachView();

    void onDestroyView();

    @Nullable
    Bundle saveInstanceState();

    void onRestoreInstanceState(@NonNull Bundle savedInstanceState);
}
