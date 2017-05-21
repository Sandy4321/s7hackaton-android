package ru.s7.android.di.scope;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

/**
 * The interface App scope.
 *
 * @author celikindv
 * @since 25 /03/2017.
 */
@Scope
@Retention(RetentionPolicy.RUNTIME)
public @interface AppScope {
}
