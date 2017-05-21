package ru.s7.android.di.module.activity;

import android.content.Context;

import dagger.Module;
import dagger.Provides;
import ru.s7.android.ui.mvp.presenter.NotificationsPresenter;

/**
 * Module for NotificationsActivity
 *
 * @author celikindv
 * @see ru.s7.android.ui.activity.NotificationsActivity
 * @see NotificationsPresenter
 */

@Module
public class ANotificationsModule {
    private Context context;

    /**
     * Instantiates a new A achievements module.
     *
     * @param context the context
     */
    public ANotificationsModule(Context context) {
        this.context = context;
    }

    /**
     * Provide a all Notifications presenter
     *
     * @return the a all Notifications presenter
     */
    @Provides
    NotificationsPresenter provideNotificationsPresenter() {
        return new NotificationsPresenter(context);
    }
}