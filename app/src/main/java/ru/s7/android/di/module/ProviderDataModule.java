package ru.s7.android.di.module;


import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import ru.s7.android.io.rest.DataProvider;
import ru.s7.android.io.rest.MainApi;

/**
 * Module for Providers;
 *
 * @author celikindv
 */
@Module
public class ProviderDataModule {


    /**
     * Provider file provider.
     *
     * @param context the context
     * @param mainApi the main api
     * @return the file provider
     */
    @Provides
    @Singleton
    DataProvider providerFileProvider(Context context, MainApi mainApi) {
        return new DataProvider(context, mainApi);
    }

}
