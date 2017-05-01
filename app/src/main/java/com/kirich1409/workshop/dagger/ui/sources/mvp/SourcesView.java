package com.kirich1409.workshop.dagger.ui.sources.mvp;

import android.support.annotation.NonNull;

import com.kirich1409.workshop.dagger.mvp.MVPView;
import com.kirich1409.workshop.dagger.network.data.NewsSourceDto;

import java.util.List;

/**
 * @authror Kirill Rozov
 * @date 1/5/17.
 */

public interface SourcesView extends MVPView {

    void setSources(@NonNull List<NewsSourceDto> sources);

    void setProgressVisible(boolean visible);
}
