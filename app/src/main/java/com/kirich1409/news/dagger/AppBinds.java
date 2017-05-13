package com.kirich1409.news.dagger;

import android.app.Application;
import android.content.Context;
import android.support.annotation.NonNull;

import dagger.Binds;
import dagger.Module;

/**
 * @author Kirill Rozov
 */
@Module
public abstract class AppBinds {

    @Binds
    abstract Context provideAppContext(@NonNull Application application);
}
