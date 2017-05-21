package ru.s7.android;

import android.app.Application;
import android.os.AsyncTask;

import java.util.ArrayList;
import java.util.Arrays;

import ru.s7.android.di.component.AppComponent;
import ru.s7.android.di.component.DaggerAppComponent;
import ru.s7.android.di.module.AppModule;
import ru.s7.android.di.module.NetworkModule;
import ru.s7.android.di.module.ProviderDataModule;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

/**
 * Created by celikindv on 20/05/2017.
 */

public class App extends Application {

    private AppComponent appComponent;
    private ArrayList<String> cities;

    @Override
    public void onCreate() {
        super.onCreate();
        initComponent();

        initCountries();

    }

    private void initCountries() {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... params) {
                cities = new ArrayList();
                cities.addAll(Arrays.asList(getResources().getStringArray(R.array.RussiaCities)));
                return null;
            }
        }.execute();
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }

    /**
     * Initialize and injecting appComponent Dagger
     *
     * @see AppComponent
     */
    private void initComponent() {
        appComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .providerDataModule(new ProviderDataModule())
                .networkModule(new NetworkModule())
                .build();
        appComponent.inject(this);
    }

    /**
     * App component getter
     *
     * @return AppComponent app component
     * @see AppComponent
     */
    public AppComponent appComponent() {
        return this.appComponent;
    }


    public ArrayList<String> getCities() {
        return cities;
    }
}
