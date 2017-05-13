package com.kirich1409.news.dagger;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.inject.Scope;

/**
 * @author Kirill Rozov
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.CLASS)
@Scope
public @interface FragmentScope {
}
