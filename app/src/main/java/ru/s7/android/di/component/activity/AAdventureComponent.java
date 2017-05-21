package ru.s7.android.di.component.activity;


import dagger.Component;
import ru.s7.android.di.component.AppComponent;
import ru.s7.android.di.module.activity.AAdventureModule;
import ru.s7.android.di.scope.activity.AAdventureScope;
import ru.s7.android.ui.activity.AdventureActivity;

/**
 * Component for  AdventureActivity;
 *
 * @author celikindv
 * @see ru.s7.android.ui.activity.AdventureActivity
 */
@AAdventureScope
@Component(dependencies = AppComponent.class, modules = AAdventureModule.class)
public interface AAdventureComponent {

    /**
     * Inject AdventureActivity
     *
     * @param activity AdventureActivity
     * @see AdventureActivity
     */
    void inject(AdventureActivity activity);

}