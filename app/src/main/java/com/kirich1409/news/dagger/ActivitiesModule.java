package com.kirich1409.news.dagger;

import com.kirich1409.news.ui.sources.SourcesActivity;
import com.kirich1409.news.ui.sources.SourcesActivityModule;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * @authror Kirill Rozov
 * @date 1/5/17.
 */
@Module
public abstract class ActivitiesModule {

    @ContributesAndroidInjector(modules = SourcesActivityModule.class)
    abstract SourcesActivity contributeSourcesActivityInjector();

}
