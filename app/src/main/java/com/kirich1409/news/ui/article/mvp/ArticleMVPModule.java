package com.kirich1409.news.ui.article.mvp;

import dagger.Binds;
import dagger.Module;

/**
 * @author Kirill Rozov
 */
@Module
public abstract class ArticleMVPModule {

    @Binds
    abstract ArticleContract.Presenter bindPresenter(ArticlePresenter presenter);

    @Binds
    abstract ArticleContract.Starter bindStarter(ArticleStarter starter);
}
