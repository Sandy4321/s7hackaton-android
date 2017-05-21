package ru.s7.android.di.component.activity;


import dagger.Component;
import ru.s7.android.di.component.AppComponent;
import ru.s7.android.di.module.activity.ANotificationsModule;
import ru.s7.android.di.scope.activity.NotificationsScope;
import ru.s7.android.ui.activity.NotificationsActivity;

/**
 * Component for  NotificationsActivity;
 *
 * @author celikindv
 * @see NotificationsActivity
 */
@NotificationsScope
@Component(dependencies = AppComponent.class, modules = ANotificationsModule.class)
public interface ANotificationsComponent {

    /**
     * Inject NotificationsActivity
     *
     * @param activity NotificationsActivity
     * @see NotificationsActivity
     */
    void inject(NotificationsActivity activity);

}