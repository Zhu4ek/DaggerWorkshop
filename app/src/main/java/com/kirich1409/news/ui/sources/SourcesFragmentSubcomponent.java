package com.kirich1409.news.ui.sources;

import com.kirich1409.news.dagger.FragmentScope;
import com.kirich1409.news.ui.sources.mvp.SourcesMVPModule;

import dagger.Subcomponent;
import dagger.android.AndroidInjector;

@FragmentScope
@Subcomponent(modules = {SourcesMVPModule.class, SourcesFragmentModule.class})
public interface SourcesFragmentSubcomponent extends AndroidInjector<SourcesFragment> {

    @Subcomponent.Builder
    abstract class Builder extends AndroidInjector.Builder<SourcesFragment> {
    }
}
