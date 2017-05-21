package ru.s7.android.di.module;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import ru.s7.android.App;
import ru.s7.android.io.bus.MainThreadBus;

/**
 * Module for  App Application;
 *
 * @author celikindv
 * @see App
 */
@Module
public class AppModule {
    private final App app;

    /**
     * Instantiates a new App module.
     *
     * @param app the app
     */
    public AppModule(App app) {
        this.app = app;
    }


    /**
     * Provide context.
     *
     * @return the context
     */
    @Provides
    @Singleton
    public Context provideContext() {
        return this.app.getApplicationContext();
    }

    /**
     * Provide app messenger.
     *
     * @return the messenger
     */
    @Provides
    @Singleton
    public App provideApp() {
        return this.app;
    }

    /**
     * Provide main thread bus
     *
     * @return the main thread bus
     */
    @Provides
    @Singleton
    public MainThreadBus provideMainThreadBus() {
        return new MainThreadBus();
    }

}
