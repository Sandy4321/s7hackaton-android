package ru.s7.android.di.module;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import ru.s7.android.io.rest.MainIntercepter;
import ru.s7.android.utils.Constants;

/**
 * Module for Network;
 *
 * @author laukhinia
 * @author celikindv
 * @see Gson
 * @see OkHttpClient
 * @see Retrofit
 */
@Module
public class NetworkModule {


    /**
     * Provide gson
     *
     * @return the gson
     */
    @Provides
    @Singleton
    Gson provideGson() {
        return new GsonBuilder()
                .create();
    }

    /**
     * Provide main intercepter main intercepter.
     *
     * @return the main intercepter
     */
    @Provides
    @Singleton
    MainIntercepter provideMainIntercepter() {
        return new MainIntercepter();
    }


    /**
     * Provide ok http client
     *
     * @return the ok http client
     */
    @Provides
    @Singleton
    OkHttpClient provideOkHttpClient(MainIntercepter mainIntercepter) {
        OkHttpClient.Builder builder = new OkHttpClient().newBuilder()
                .addInterceptor(mainIntercepter)
                .retryOnConnectionFailure(true)
                .hostnameVerifier(new HostnameVerifier() {
                    @Override
                    public boolean verify(String hostname, SSLSession session) {
                        return true;
                    }
                })
                .connectTimeout(5000, TimeUnit.SECONDS)
                .readTimeout(5000, TimeUnit.SECONDS)
                .writeTimeout(5000, TimeUnit.SECONDS);
        return builder.build();
    }


    /**
     * Provide retrofit
     *
     * @param gson         the gson
     * @param okHttpClient the ok http client
     * @return the retrofit
     */
    @Provides
    @Singleton
    Retrofit provideRetrofit(Gson gson, OkHttpClient okHttpClient) {
        return new Retrofit.Builder()
                .baseUrl("http://" + Constants.HOST_NAME + "/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(okHttpClient)
                .build();
    }


}
