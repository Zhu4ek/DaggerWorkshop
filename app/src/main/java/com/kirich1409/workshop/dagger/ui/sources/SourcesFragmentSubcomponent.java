package com.kirich1409.workshop.dagger.ui.sources;

import com.kirich1409.workshop.dagger.dagger.FragmentScope;
import com.kirich1409.workshop.dagger.ui.sources.mvp.SourcesMVPModule;

import dagger.Subcomponent;
import dagger.android.AndroidInjector;

@FragmentScope
@Subcomponent(modules = SourcesMVPModule.class)
public interface SourcesFragmentSubcomponent extends AndroidInjector<SourcesFragment> {

    @Subcomponent.Builder
    abstract class Builder extends AndroidInjector.Builder<SourcesFragment> {
    }
}
