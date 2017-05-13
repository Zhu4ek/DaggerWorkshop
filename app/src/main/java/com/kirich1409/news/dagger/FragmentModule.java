package com.kirich1409.news.dagger;

import android.support.v4.app.Fragment;

import com.kirich1409.news.mvp.AndroidComponent;
import com.kirich1409.news.mvp.FragmentComponent;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Kirill Rozov on 5/3/17.
 */
@Module
public class FragmentModule {

    private final Fragment mFragment;

    public FragmentModule(Fragment fragment) {
        mFragment = fragment;
    }

    @FragmentScope
    @Provides
    public Fragment provideFragment() {
        return mFragment;
    }

    @FragmentScope
    @Provides
    AndroidComponent provideAndroidComponent() {
        return new FragmentComponent(mFragment);
    }
}
