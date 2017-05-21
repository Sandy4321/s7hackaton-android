package ru.s7.android.di.scope.activity;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

/**
 * Scope for AAllAchievementsModule;
 *
 * @author celikindv
 * @see ru.s7.android.di.module.activity.AAllAchievementsModule
 * @see ru.s7.android.di.component.activity.AAllAchievementsComponent
 */
@Scope
@Retention(RetentionPolicy.RUNTIME)
public @interface AAllAchievementsScope {
}