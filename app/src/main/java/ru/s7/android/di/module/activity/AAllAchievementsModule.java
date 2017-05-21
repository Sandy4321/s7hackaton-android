package ru.s7.android.di.module.activity;

import android.content.Context;

import dagger.Module;
import dagger.Provides;
import ru.s7.android.ui.mvp.presenter.AllAchievementsPresenter;

/**
 * Module for AllAchievementsActivity
 *
 * @author celikindv
 * @see ru.s7.android.ui.activity.AllAchievementsActivity
 * @see AllAchievementsPresenter
 */
@Module
public class AAllAchievementsModule {
    private Context context;

    /**
     * Instantiates a new A achievements module.
     *
     * @param context the context
     */
    public AAllAchievementsModule(Context context) {
        this.context = context;
    }

    /**
     * Provide a all Achievements presenter
     *
     * @return the a all Achievements presenter
     */
    @Provides
    AllAchievementsPresenter provideAllAchievementsPresenter() {
        return new AllAchievementsPresenter(context);
    }
}