package ru.s7.android.di.component.activity;


import dagger.Component;
import ru.s7.android.di.component.AppComponent;
import ru.s7.android.di.module.activity.AAchievementsModule;
import ru.s7.android.di.scope.activity.AAllAchievementsScope;
import ru.s7.android.ui.activity.AchievementsActivity;

/**
 * Component for  AchievementsActivity;
 *
 * @author celikindv
 * @see ru.s7.android.ui.activity.AchievementsActivity
 */
@AAllAchievementsScope
@Component(dependencies = AppComponent.class, modules = AAchievementsModule.class)
public interface AAachievementsComponent {

    /**
     * Inject AchievementsActivity
     *
     * @param activity AchievementsActivity
     * @see AchievementsActivity
     */
    void inject(AchievementsActivity activity);

}