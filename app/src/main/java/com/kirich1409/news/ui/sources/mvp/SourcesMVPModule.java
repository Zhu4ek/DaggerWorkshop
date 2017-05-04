package com.kirich1409.news.ui.sources.mvp;

import dagger.Binds;
import dagger.Module;

@Module
public abstract class SourcesMVPModule {

    @Binds
    abstract SourcesPresenter bindSourcesPresenter(SourcesPresenterImpl presenter);

    @Binds
    abstract SourcesLauncher bindSourcesLauncher(SourcesLauncherImpl launcher);
}
