package com.kirich1409.news.ui.articles.mvp;

import dagger.Binds;
import dagger.Module;

/**
 * @author Kirill Rozov
 */
@Module
public abstract class ArticlesMVPModule {

    @Binds
    abstract ArticlesContract.Presenter bindPresenter(ArticlesPresenter presenter);

    @Binds
    abstract ArticlesContract.Starter bindStarter(ArticlesStarter starter);
}
