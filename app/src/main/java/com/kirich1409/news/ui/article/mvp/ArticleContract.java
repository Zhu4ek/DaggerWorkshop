package com.kirich1409.news.ui.article.mvp;

import android.support.annotation.NonNull;

import com.kirich1409.news.mvp.MVPPresenter;
import com.kirich1409.news.mvp.MVPView;
import com.kirich1409.news.network.data.ArticleDto;

/**
 * @author Kirill Rozov
 */

public final class ArticleContract {

    public interface View extends MVPView {

        void setArticle(@NonNull ArticleDto article);

        void setOpenWebPageEnable(boolean enable);
    }

    public interface Presenter extends MVPPresenter<View> {

        void openWebPage();
    }

    public interface Starter {

        void openWebPage(@NonNull String url);
    }

    private ArticleContract() {
    }
}
