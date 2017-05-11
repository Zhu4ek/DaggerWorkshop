package com.kirich1409.news.ui.sources;

import dagger.Subcomponent;
import dagger.android.AndroidInjector;

@Subcomponent(modules = SourcesFragmentActivityModule.class)
public interface SourcesActivitySubcomponent extends AndroidInjector<SourcesActivity> {

    @Subcomponent.Builder
    abstract class Builder extends AndroidInjector.Builder<SourcesActivity> {
    }
}
