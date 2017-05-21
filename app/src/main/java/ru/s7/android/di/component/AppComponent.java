package ru.s7.android.di.component;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Component;
import ru.s7.android.App;
import ru.s7.android.di.module.AppModule;
import ru.s7.android.di.module.ClientModule;
import ru.s7.android.di.module.NetworkModule;
import ru.s7.android.di.module.ProviderDataModule;
import ru.s7.android.io.bus.MainThreadBus;
import ru.s7.android.ui.mvp.presenter.AchievementsPresenter;
import ru.s7.android.ui.mvp.presenter.AdventurePresenter;
import ru.s7.android.ui.mvp.presenter.AllAchievementsPresenter;
import ru.s7.android.ui.mvp.presenter.AllAdventuresPresenter;
import ru.s7.android.ui.mvp.presenter.NotificationsPresenter;


/**
 * Component for  App
 *
 * @author celikindv
 * @see App
 */
@Component(modules = {AppModule.class, ProviderDataModule.class, NetworkModule.class,
        ClientModule.class})
@Singleton
public interface AppComponent {


    /**
     * Context
     *
     * @return application context
     * @see Context
     */
    Context context();

    /**
     * Main thread bus
     *
     * @return the main thread bus
     * @see MainThreadBus
     */
    MainThreadBus mainThreadBus();


    /**
     * Inject Application
     *
     * @param app Application class
     * @see App
     */
    void inject(App app);


    void inject(AdventurePresenter adventurePresenter);


    void inject(AchievementsPresenter achievementsPresenter);

    void inject(AllAchievementsPresenter allAchievementsPresenter);

    void inject(AllAdventuresPresenter allAdventuresPresenter);

    void inject(NotificationsPresenter notificationsPresenter);
}
