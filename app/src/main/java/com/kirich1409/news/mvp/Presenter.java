package com.kirich1409.news.mvp;

import android.support.annotation.NonNull;

/**
 * @authror Kirill Rozov
 * @date 1/5/17.
 */

public interface Presenter<V extends MVPView> {

    void onAttachView(@NonNull V view);

    void onDetachView();
}
