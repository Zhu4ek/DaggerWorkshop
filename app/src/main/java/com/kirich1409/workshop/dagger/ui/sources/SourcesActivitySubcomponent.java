package com.kirich1409.workshop.dagger.ui.sources;

import com.kirich1409.workshop.dagger.dagger.ActivityScope;

import dagger.Subcomponent;
import dagger.android.AndroidInjector;

@ActivityScope
@Subcomponent(modules = SourcesFragmentActivityModule.class)
public interface SourcesActivitySubcomponent extends AndroidInjector<SourcesActivity> {

    @Subcomponent.Builder
    abstract class Builder extends AndroidInjector.Builder<SourcesActivity> {
    }
}
