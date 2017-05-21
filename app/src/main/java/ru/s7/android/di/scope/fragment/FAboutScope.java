package ru.s7.android.di.scope.fragment;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

/**
 * Scope for FAboutModule;
 *
 * @author celikindv
 * @see com.icbt.messenger.di.module.fragment.FAboutModule
 * @see com.icbt.messenger.di.component.fragment.FAboutComponent
 */
@Scope
@Retention(RetentionPolicy.RUNTIME)
public @interface FAboutScope {
}