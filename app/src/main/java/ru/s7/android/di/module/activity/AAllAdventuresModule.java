package ru.s7.android.di.module.activity;

import android.content.Context;

import dagger.Module;
import dagger.Provides;
import ru.s7.android.ui.mvp.presenter.AllAdventuresPresenter;

/**
 * Module for AllAdventuresActivity
 *
 * @author celikindv
 * @see ru.s7.android.ui.activity.AllAdventuresActivity
 * @see ru.s7.android.ui.mvp.presenter.AllAdventuresPresenter
 */
@Module
public class AAllAdventuresModule {
    private Context context;

    /**
     * Instantiates a new A adventures module.
     *
     * @param context the context
     */
    public AAllAdventuresModule(Context context) {
        this.context = context;
    }

    /**
     * Provide a all adventures presenter
     *
     * @return the a all adventures presenter
     */
    @Provides
    AllAdventuresPresenter provideAllAdventuresPresenter() {
        return new AllAdventuresPresenter(context);
    }
}