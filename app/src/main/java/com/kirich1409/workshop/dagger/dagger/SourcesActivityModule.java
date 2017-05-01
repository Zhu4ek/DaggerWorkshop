package com.kirich1409.workshop.dagger.dagger;

import android.app.Activity;

import com.kirich1409.workshop.dagger.ui.sources.SourcesActivity;
import com.kirich1409.workshop.dagger.ui.sources.SourcesActivitySubcomponent;

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
