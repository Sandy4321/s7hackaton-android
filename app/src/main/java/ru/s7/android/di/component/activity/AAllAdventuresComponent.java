package ru.s7.android.di.component.activity;


import dagger.Component;
import ru.s7.android.di.component.AppComponent;
import ru.s7.android.di.module.activity.AAllAdventuresModule;
import ru.s7.android.di.scope.activity.AAllAdventuresScope;
import ru.s7.android.ui.activity.AllAdventuresActivity;

/**
 * Component for  AllAdventuresActivity;
 *
 * @author celikindv
 * @see AllAdventuresActivity
 */
@AAllAdventuresScope
@Component(dependencies = AppComponent.class, modules = AAllAdventuresModule.class)
public interface AAllAdventuresComponent {

    /**
     * Inject MainActivity
     *
     * @param activity AllAdventuresActivity
     * @see AllAdventuresActivity
     */
    void inject(AllAdventuresActivity activity);

}