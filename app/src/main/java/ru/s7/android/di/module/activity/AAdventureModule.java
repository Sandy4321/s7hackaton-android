package ru.s7.android.di.module.activity;

import android.content.Context;

import dagger.Module;
import dagger.Provides;
import ru.s7.android.ui.mvp.presenter.AdventurePresenter;

/**
 * Module for AdventureActivity
 *
 * @author celikindv
 * @see ru.s7.android.ui.activity.AdventureActivity
 * @see ru.s7.android.ui.mvp.presenter.AdventurePresenter
 */
@Module
public class AAdventureModule {
    private Context context;

    /**
     * Instantiates a new A achievements module.
     *
     * @param context the context
     */
    public AAdventureModule(Context context) {
        this.context = context;
    }

    /**
     * Provide a main presenter
     *
     * @return the a main presenter
     */
    @Provides
    AdventurePresenter provideAdventurePresenter() {
        return new AdventurePresenter(context);
    }
}