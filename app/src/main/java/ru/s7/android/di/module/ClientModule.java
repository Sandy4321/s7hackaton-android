package ru.s7.android.di.module;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import ru.s7.android.io.rest.MainApi;

/**
 * Module for Api Client;
 *
 * @author celikindv
 */
@Module
public class ClientModule {
    /**
     * Provide main api .
     *
     * @param retrofit the retrofit
     * @return the main api
     * @see Retrofit
     */
    @Provides
    @Singleton
    MainApi provideMainApi(Retrofit retrofit) {
        return retrofit.create(MainApi.class);
    }


}
