package com.kirich1409.news.ui.sources;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

import com.kirich1409.news.R;

import javax.inject.Inject;

import dagger.android.AndroidInjection;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.HasSupportFragmentInjector;

public class SourcesActivity extends AppCompatActivity
        implements HasSupportFragmentInjector {

    @Inject
    DispatchingAndroidInjector<Fragment> mSupportFragmentInjector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (mSupportFragmentInjector == null) {
            AndroidInjection.inject(this);
        }
        setContentView(R.layout.activity_sources);
    }

    @Override
    public DispatchingAndroidInjector<Fragment> supportFragmentInjector() {
        return mSupportFragmentInjector;
    }
}
