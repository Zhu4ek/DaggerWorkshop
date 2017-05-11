package com.kirich1409.news.network.data;

import android.support.annotation.Keep;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.util.ArrayMap;
import android.text.TextUtils;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * @authror Kirill Rozov
 * @date 1/5/17.
 */
@JsonPropertyOrder({
        NewsSourceDto.PROPERTY_ID,
        NewsSourceDto.PROPERTY_NAME,
        NewsSourceDto.PROPERTY_DESCRIPTION,
        NewsSourceDto.PROPERTY_URL,
        NewsSourceDto.PROPERTY_CATEGORY,
        NewsSourceDto.PROPERTY_LANGUAGE,
        NewsSourceDto.PROPERTY_COUNTRY,
        NewsSourceDto.PROPERTY_LOGOS_URL,
        NewsSourceDto.PROPERTY_SUPPORTED_SORTS,
})
@Keep
public class NewsSourceDto {

    public static final String PROPERTY_ID = "id";
    public static final String PROPERTY_NAME = "name";
    public static final String PROPERTY_DESCRIPTION = "description";
    public static final String PROPERTY_URL = "url";
    public static final String PROPERTY_CATEGORY = "category";
    public static final String PROPERTY_LANGUAGE = "language";
    public static final String PROPERTY_COUNTRY = "country";
    public static final String PROPERTY_LOGOS_URL = "urlsToLogos";
    public static final String PROPERTY_SUPPORTED_SORTS = "sortBysAvailable";

    @NonNull
    private final String mId;

    @NonNull
    private final String mName;

    @NonNull
    private final String mDescription;

    @Nullable
    private final String mUrl;

    @NonNull
    private final String mCategory;

    @NonNull
    private final String mLanguage;

    @NonNull
    private final String mCountry;

    @NonNull
    private final Map<String, String> mLogosUrl;

    @NonNull
    private final List<String> mSupportedSorts;

    @JsonCreator
    public NewsSourceDto(
            @NonNull @JsonProperty(PROPERTY_ID) final String id,
            @NonNull @JsonProperty(PROPERTY_NAME) final String name,
            @NonNull @JsonProperty(PROPERTY_DESCRIPTION) final String description,
            @Nullable @JsonProperty(PROPERTY_URL) final String url,
            @NonNull @JsonProperty(PROPERTY_CATEGORY) @Categories.CategoryDef final String category,
            @NonNull @JsonProperty(PROPERTY_LANGUAGE) @LanguageCode final String language,
            @NonNull @JsonProperty(PROPERTY_COUNTRY) @CountryCode final String country,
            @NonNull @JsonProperty(PROPERTY_LOGOS_URL) final Map<String, String> logosUrl,
            @NonNull @JsonProperty(PROPERTY_SUPPORTED_SORTS) final List<String> supportedSorts) {
        mId = id;
        mName = name;
        mDescription = description;
        mUrl = url == null || url.isEmpty() ? null : url;
        mCategory = category;
        mLanguage = language;
        mCountry = country;
        mLogosUrl = filterUrl(logosUrl);
        mSupportedSorts = Collections.unmodifiableList(supportedSorts);
    }

    private static Map<String, String> filterUrl(@NonNull Map<String, String> logosUrl) {
        ArrayMap<String, String> result = new ArrayMap<>(logosUrl.size());
        for (Map.Entry<String, String> entry : logosUrl.entrySet()) {
            if (!TextUtils.isEmpty(entry.getValue())) {
                result.put(entry.getKey(), entry.getValue());
            }
        }
        if (result.isEmpty()) {
            return Collections.emptyMap();
        } else {
            return result;
        }
    }

    @NonNull
    public String getId() {
        return mId;
    }

    @NonNull
    public String getName() {
        return mName;
    }

    @NonNull
    public String getDescription() {
        return mDescription;
    }

    @Nullable
    public String getUrl() {
        return mUrl;
    }

    @Categories.CategoryDef
    @NonNull
    public String getCategory() {
        return mCategory;
    }

    @LanguageCode
    @NonNull
    public String getLanguage() {
        return mLanguage;
    }

    @CountryCode
    @NonNull
    public String getCountry() {
        return mCountry;
    }

    @NonNull
    public Map<String, String> getLogosUrl() {
        return mLogosUrl;
    }

    @NonNull
    public List<String> getSupportedSorts() {
        return mSupportedSorts;
    }

    @Override
    public String toString() {
        return mName;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        NewsSourceDto newsSourceDto = (NewsSourceDto) o;
        return mId.equals(newsSourceDto.mId) &&
                mName.equals(newsSourceDto.mName) &&
                mDescription.equals(newsSourceDto.mDescription) &&
                mUrl == null ? newsSourceDto.mUrl == null : mUrl.equals(newsSourceDto.mUrl) &&
                mCategory.equals(newsSourceDto.mCategory) &&
                mLanguage.equals(newsSourceDto.mLanguage) &&
                mCountry.equals(newsSourceDto.mCountry) &&
                mLogosUrl.equals(newsSourceDto.mLogosUrl) &&
                mSupportedSorts.equals(newsSourceDto.mSupportedSorts);
    }

    @Override
    public int hashCode() {
        return mId.hashCode();
    }

}
