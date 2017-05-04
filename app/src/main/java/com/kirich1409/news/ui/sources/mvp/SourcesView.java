package com.kirich1409.news.ui.sources.mvp;

import android.support.annotation.NonNull;

import com.kirich1409.news.mvp.MVPView;
import com.kirich1409.news.network.data.NewsSourceDto;

import java.util.List;

/**
 * @authror Kirill Rozov
 * @date 1/5/17.
 */

public interface SourcesView extends MVPView {

    void setSources(@NonNull List<NewsSourceDto> sources);

    void setProgressVisible(boolean visible);
}
