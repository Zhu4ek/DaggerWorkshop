package com.kirich1409.workshop.dagger.network.data;

import android.support.annotation.Size;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * The 2-letter ISO 3166-1 code of the country you would like to get sources for
 *
 * @authror Kirill Rozov
 * @date 1/5/17.
 */
@Retention(RetentionPolicy.SOURCE)
@Size(2)
public @interface CountryCode {
}