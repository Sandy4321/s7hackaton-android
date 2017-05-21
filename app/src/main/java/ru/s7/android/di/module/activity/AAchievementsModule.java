package ru.s7.android.di.module.activity;

import android.content.Context;

import dagger.Module;
import dagger.Provides;
import ru.s7.android.ui.mvp.presenter.AchievementsPresenter;
import ru.s7.android.ui.mvp.presenter.AllAchievementsPresenter;

/**
 * Module for MainActivity
 *
 * @author celikindv
 * @see ru.s7.android.ui.activity.AchievementsActivity
 * @see AllAchievementsPresenter
 */
@Module
public class AAchievementsModule {
    private Context context;

    /**
     * Instantiates a new A achievements module.
     *
     * @param context the context
     */
    public AAchievementsModule(Context context) {
        this.context = context;
    }

    /**
     * Provide a main presenter
     *
     * @return the a main presenter
     */
    @Provides
    AchievementsPresenter provideAchievementsPresenter() {
        return new AchievementsPresenter(context);
    }
}