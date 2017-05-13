package com.kirich1409.news.ui.sources;

import com.kirich1409.news.mvp.AndroidComponent;
import com.kirich1409.news.mvp.FragmentComponent;
import com.kirich1409.news.ui.sources.mvp.SourcesMVPModule;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Kirill Rozov on 5/3/17.
 */
@Module(includes = SourcesMVPModule.class)
public abstract class SourcesFragmentModule {

    @Provides
    static AndroidComponent provideAndroidComponent(SourcesFragment fragment) {
        return new FragmentComponent(fragment);
    }
}
