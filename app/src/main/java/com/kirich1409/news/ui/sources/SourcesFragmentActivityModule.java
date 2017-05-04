package com.kirich1409.news.ui.sources;

import android.support.v4.app.Fragment;

import dagger.Binds;
import dagger.Module;
import dagger.android.AndroidInjector;
import dagger.android.support.FragmentKey;
import dagger.multibindings.IntoMap;

@Module(subcomponents = SourcesFragmentSubcomponent.class)
abstract class SourcesFragmentActivityModule {

  @Binds
  @IntoMap
  @FragmentKey(SourcesFragment.class)
  abstract AndroidInjector.Factory<? extends Fragment> bindSourcesFragmentInjectorFactory(
          SourcesFragmentSubcomponent.Builder builder);
}
