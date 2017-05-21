package ru.s7.android.di.scope.activity;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

/**
 * Scope for AAdventureModule;
 *
 * @author celikindv
 * @see ru.s7.android.di.module.activity.AAdventureModule
 * @see ru.s7.android.di.component.activity.AAdventureComponent
 */
@Scope
@Retention(RetentionPolicy.RUNTIME)
public @interface AAdventureScope {
}