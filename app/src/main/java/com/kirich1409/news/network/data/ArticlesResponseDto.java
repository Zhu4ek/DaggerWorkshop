package com.kirich1409.news.network.data;

import android.support.annotation.Keep;
import android.support.annotation.NonNull;
import android.support.annotation.StringDef;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.List;

/**
 * @authror Kirill Rozov
 * @date 1/5/17.
 */
@JsonPropertyOrder({
        ArticlesResponseDto.PROPERTY_STATUS,
        ArticlesResponseDto.PROPERTY_SOURCE,
        ArticlesResponseDto.PROPERTY_ARTICLES,
})
@Keep
public final class ArticlesResponseDto extends ResponseDto<ArticleDto> {

    public static final String PROPERTY_SOURCE = "source";
    public static final String PROPERTY_STATUS = "status";
    public static final String PROPERTY_ARTICLES = "articles";
    public static final String PROPERTY_SORT = "sortBy";

    public static final String STATUS_OK = "ok";

    public static final String STATUS_ERROR = "ok";
    public static final String SORT_TOP = "top";
    public static final String SORT_LATEST = "latest";
    public static final String SORT_POPULAR = "popular";

    @JsonProperty(PROPERTY_SOURCE)
    @NonNull
    private final String mSource;

    @JsonProperty(PROPERTY_SORT)
    @NonNull
    private final String mSort;

    @JsonCreator
    public ArticlesResponseDto(
            @NonNull @JsonProperty(PROPERTY_STATUS) @StatusDef final String status,
            @NonNull @JsonProperty(PROPERTY_SOURCE) final String source,
            @NonNull @JsonProperty(PROPERTY_ARTICLES) final List<ArticleDto> articles,
            @NonNull @JsonProperty(PROPERTY_SORT) @SortDef final String sort) {
        super(status, articles);
        mSource = source;
        mSort = sort;
    }

    @NonNull
    @StatusDef
    public String getStatus() {
        return mStatus;
    }

    @NonNull
    public String getSource() {
        return mSource;
    }

    @SortDef
    @NonNull
    public String getSort() {
        return mSort;
    }

    @JsonProperty(PROPERTY_ARTICLES)
    @NonNull
    public List<ArticleDto> getArticles() {
        return mData;
    }

    @Retention(RetentionPolicy.SOURCE)
    @StringDef({STATUS_ERROR, STATUS_OK})
    public @interface StatusDef {
    }

    @Retention(RetentionPolicy.SOURCE)
    @StringDef({
            SORT_LATEST,
            SORT_POPULAR,
            SORT_TOP
    })
    public @interface SortDef {
    }
}
