package com.kirich1409.news.ui.sources;

import android.app.Activity;

import dagger.Binds;
import dagger.Module;
import dagger.android.ActivityKey;
import dagger.android.AndroidInjector;
import dagger.multibindings.IntoMap;

@Module(subcomponents = SourcesActivitySubcomponent.class)
public abstract class SourcesActivityModule {

    @Binds
    @IntoMap
    @ActivityKey(SourcesActivity.class)
    abstract AndroidInjector.Factory<? extends Activity> bindSourcesActivityInjectorFactory(
            SourcesActivitySubcomponent.Builder builder);

}
