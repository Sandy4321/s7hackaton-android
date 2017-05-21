package ru.s7.android.di.component.activity;


import dagger.Component;
import ru.s7.android.di.component.AppComponent;
import ru.s7.android.di.module.activity.AAllAchievementsModule;
import ru.s7.android.di.scope.activity.AAllAchievementsScope;
import ru.s7.android.ui.activity.AllAchievementsActivity;
import ru.s7.android.ui.activity.NotificationsActivity;

/**
 * Component for  AchievementsActivity;
 *
 * @author celikindv
 * @see ru.s7.android.ui.activity.AllAchievementsActivity
 */
@AAllAchievementsScope
@Component(dependencies = AppComponent.class, modules = AAllAchievementsModule.class)
public interface AAllAchievementsComponent {

    /**
     * Inject AllAchievementsActivity
     *
     * @param activity AllAchievementsActivity
     * @see AllAchievementsActivity
     */
    void inject(AllAchievementsActivity activity);

}