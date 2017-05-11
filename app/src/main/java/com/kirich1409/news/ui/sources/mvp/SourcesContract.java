package com.kirich1409.news.ui.sources.mvp;

import android.support.annotation.NonNull;

import com.kirich1409.news.mvp.MVPPresenter;
import com.kirich1409.news.mvp.MVPView;
import com.kirich1409.news.network.data.NewsSourceDto;

import java.util.List;

public final class SourcesContract {

    public interface Presenter extends MVPPresenter<View> {

        void openArticles(final @NonNull NewsSourceDto source);
    }

    public interface Starter {

        void openArticles(final @NonNull NewsSourceDto source);
    }

    public interface View extends MVPView {

        void setSources(@NonNull List<NewsSourceDto> sources);

        void setProgressVisible(boolean visible);

        void showError(@NonNull String errorMessage);
    }

    private SourcesContract() {
    }
}
