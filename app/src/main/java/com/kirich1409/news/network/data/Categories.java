package com.kirich1409.news.network.data;

import android.support.annotation.StringDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by kirylrozau on 5/9/17.
 */

public final class Categories {

    public static final String BUSINESS = "business";
    public static final String ENTERTAINMENT = "entertainment";
    public static final String GAMING = "gaming";
    public static final String GENERAL = "general";
    public static final String MUSIC = "music";
    public static final String POLITICS = "politics";
    public static final String SCIENCE_AND_NATURE = "science-and-nature";
    public static final String SPORT = "sport";
    public static final String TECHNOLOGY = "technology";

    private Categories() {
    }

    @Retention(RetentionPolicy.SOURCE)
    @StringDef({
            BUSINESS,
            ENTERTAINMENT,
            GAMING,
            GENERAL,
            MUSIC,
            MUSIC,
            POLITICS,
            SCIENCE_AND_NATURE,
            SPORT,
            TECHNOLOGY
    })
    public @interface CategoryDef {
    }
}
