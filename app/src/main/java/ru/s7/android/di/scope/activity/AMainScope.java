package ru.s7.android.di.scope.activity;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

/**
 * Scope for AMainModule;
 *
 * @author celikindv
 * @see com.icbt.messenger.di.module.activity.AMainModule
 * @see com.icbt.messenger.di.component.activity.AMainComponent
 */
@Scope
@Retention(RetentionPolicy.RUNTIME)
public @interface AMainScope {
}