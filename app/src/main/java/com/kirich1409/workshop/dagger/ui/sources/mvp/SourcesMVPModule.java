package com.kirich1409.workshop.dagger.ui.sources.mvp;

import android.support.annotation.NonNull;

import com.kirich1409.workshop.dagger.mvp.AndroidComponent;
import com.kirich1409.workshop.dagger.mvp.FragmentComponent;

import dagger.Binds;
import dagger.Module;

@Module
public abstract class SourcesMVPModule {

    @Binds
    abstract AndroidComponent bindAndroidComponent(@NonNull FragmentComponent component);

    @Binds
    abstract SourcesPresenter bindSourcesPresenter(SourcesPresenterImpl presenter);
}
