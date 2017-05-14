package com.kirich1409.news.ui.sources;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class SourcesActivityModule {

    @ContributesAndroidInjector(modules = SourcesFragmentModule.class)
    abstract SourcesFragment contributeSourcesFragmentInjector();

}
