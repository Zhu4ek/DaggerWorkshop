package com.kirich1409.news.dagger;

import com.kirich1409.news.util.RuntimeUtils;

import java.util.concurrent.Executors;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * @author kirylrozau
 */
@Module
public abstract class RxModule {

    public static final String NETWORK = "network";
    public static final String MAIN = "main";
    public static final String COMPUTATION = "computation";

    @Provides
    @Named(COMPUTATION)
    public static Scheduler providerComputationScheduler() {
        return Schedulers.computation();
    }

    @Provides
    @Named(NETWORK)
    @Singleton
    public static Scheduler providerNetworkScheduler() {
        int threads = Math.max(RuntimeUtils.availableProcessors() / 2, 1);
        return Schedulers.from(Executors.newFixedThreadPool(threads));
    }

    @Provides
    @Named(MAIN)
    public static Scheduler providerMainScheduler() {
        return AndroidSchedulers.mainThread();
    }
}
