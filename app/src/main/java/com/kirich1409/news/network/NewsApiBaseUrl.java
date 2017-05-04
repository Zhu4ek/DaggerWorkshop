package com.kirich1409.news.network;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Named;

/**
 * @authror Kirill Rozov
 * @date 1/5/17.
 */
@Retention(RetentionPolicy.CLASS)
@Named
public @interface NewsApiBaseUrl {
}
