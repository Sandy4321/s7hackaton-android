package ru.s7.android.di.scope.activity;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

/**
 * Scope for ASplashModule;
 *
 * @author celikindv
 * @see com.icbt.messenger.di.module.activity.ASplashModule
 * @see com.icbt.messenger.di.component.activity.ASplashComponent
 */
@Scope
@Retention(RetentionPolicy.RUNTIME)
public @interface ASplashScope {
}