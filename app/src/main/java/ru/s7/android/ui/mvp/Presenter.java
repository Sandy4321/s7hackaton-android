package ru.s7.android.ui.mvp;


import android.content.Context;

import ru.s7.android.App;
import ru.s7.android.di.component.AppComponent;

/**
 * The type Presenter.
 *
 * @param <V> The view interface
 * @author celikindv
 * @since 20 /05/17. <p> Interface Presenter for implementation in classes presenters in application MVP realisation,
 */
public abstract class Presenter<V> {

    /**
     * Attach view to presenter.
     *
     * @param view the view
     */
    public abstract void attachView(V view);

    /**
     * Detach view from presenter.
     */
    public abstract void detachView();

    /**
     * Initialize presenter.
     */
    public void init() {
    }


    /**
     * Injector app component.
     *
     * @param context the context
     * @return the app component
     */
    public AppComponent Injector(Context context) {
        return ((App) context.getApplicationContext()).appComponent();
    }

}