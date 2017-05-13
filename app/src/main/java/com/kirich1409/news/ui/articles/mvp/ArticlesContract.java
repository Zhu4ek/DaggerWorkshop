package com.kirich1409.news.ui.articles.mvp;

import android.support.annotation.NonNull;

import com.kirich1409.news.mvp.MVPPresenter;
import com.kirich1409.news.mvp.MVPView;
import com.kirich1409.news.network.data.ArticleDto;

import java.util.List;

/**
 * @author Kirill Rozov
 */

public final class ArticlesContract {

    public interface Presenter extends MVPPresenter<ArticlesContract.View> {

        void openArticle(final @NonNull ArticleDto article);

        void openSourceWebPage();
    }

    public interface Starter {

        void openArticle(final @NonNull ArticleDto article);

        void openSourceWebPage(final @NonNull String url);
    }

    public interface View extends MVPView {

        void setArticles(@NonNull List<ArticleDto> articles);

        void setProgressVisible(boolean visible);

        void showError(@NonNull String errorMessage);

        void setTitle(@NonNull CharSequence title);

        void setOpenSourceWebPageEnable(boolean enable);
    }

    private ArticlesContract() {
    }
}
