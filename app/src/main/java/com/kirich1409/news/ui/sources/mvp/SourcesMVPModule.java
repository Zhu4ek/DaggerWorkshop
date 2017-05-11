package com.kirich1409.news.ui.sources.mvp;

import dagger.Binds;
import dagger.Module;

@Module
public abstract class SourcesMVPModule {

    @Binds
    abstract SourcesContract.Presenter bindPresenter(SourcesPresenter presenter);

    @Binds
    abstract SourcesContract.Starter bindStarter(SourcesStarter starter);
}
