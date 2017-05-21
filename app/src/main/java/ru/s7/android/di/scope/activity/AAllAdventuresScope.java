package ru.s7.android.di.scope.activity;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

/**
 * Scope for AAllAdventuresModule;
 *
 * @author celikindv
 * @see ru.s7.android.di.module.activity.AAllAdventuresModule
 * @see ru.s7.android.di.component.activity.AAllAdventuresComponent
 */
@Scope
@Retention(RetentionPolicy.RUNTIME)
public @interface AAllAdventuresScope {
}