package com.kirich1409.news.network;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.inject.Qualifier;

/**
 * @authror Kirill Rozov
 * @date 1/5/17.
 */
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.CLASS)
@Qualifier
public @interface NewsApiBaseUrl {
}
