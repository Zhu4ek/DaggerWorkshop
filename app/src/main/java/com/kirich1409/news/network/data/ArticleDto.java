package com.kirich1409.news.network.data;

import android.support.annotation.Keep;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import org.threeten.bp.OffsetDateTime;

import java.util.Objects;

/**
 * @authror Kirill Rozov
 * @date 1/5/17.
 */
@JsonPropertyOrder({
        ArticleDto.PROPERTY_AUTHOR,
        ArticleDto.PROPERTY_TITLE,
        ArticleDto.PROPERTY_DESCRIPTION,
        ArticleDto.PROPERTY_URL,
        ArticleDto.PROPERTY_IMAGE_URL,
        ArticleDto.PROPERTY_PUBLISHED,
})
@Keep
public class ArticleDto {

    public static final String PROPERTY_AUTHOR = "author";
    public static final String PROPERTY_TITLE = "title";
    public static final String PROPERTY_DESCRIPTION = "description";
    public static final String PROPERTY_URL = "url";
    public static final String PROPERTY_IMAGE_URL = "urlToImage";
    public static final String PROPERTY_PUBLISHED = "publishedAt";

    @JsonProperty(PROPERTY_AUTHOR)
    @NonNull
    private final String mAuthor;

    @JsonProperty(PROPERTY_TITLE)
    @NonNull
    private final String mTitle;

    @JsonProperty(PROPERTY_DESCRIPTION)
    @NonNull
    private final String mDescription;

    @JsonProperty(PROPERTY_URL)
    @Nullable
    private final String mUrl;

    @JsonProperty(PROPERTY_IMAGE_URL)
    @Nullable
    private final String mImageUrl;

    @JsonProperty(PROPERTY_PUBLISHED)
    @NonNull
    private final OffsetDateTime mPublished;

    @JsonCreator
    public ArticleDto(@NonNull @JsonProperty(PROPERTY_AUTHOR) final String author,
                      @NonNull @JsonProperty(PROPERTY_TITLE) final String title,
                      @NonNull @JsonProperty(PROPERTY_DESCRIPTION) final String description,
                      @Nullable @JsonProperty(PROPERTY_URL) final String url,
                      @Nullable @JsonProperty(PROPERTY_IMAGE_URL) final String imageUrl,
                      @NonNull @JsonProperty(PROPERTY_PUBLISHED) final OffsetDateTime published) {
        mAuthor = author;
        mTitle = title;
        mDescription = description;
        mUrl = url;
        mImageUrl = imageUrl;
        mPublished = published;
    }

    @NonNull
    public String getAuthor() {
        return mAuthor;
    }

    @NonNull
    public String getTitle() {
        return mTitle;
    }

    @NonNull
    public String getDescription() {
        return mDescription;
    }

    @Nullable
    public String getUrl() {
        return mUrl;
    }

    @Nullable
    public String getImageUrl() {
        return mImageUrl;
    }

    @NonNull
    public OffsetDateTime getPublished() {
        return mPublished;
    }

    @Override
    public String toString() {
        return mTitle;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ArticleDto that = (ArticleDto) o;
        return mPublished.equals(that.mPublished)
                && mAuthor.equals(that.mAuthor)
                && mTitle.equals(that.mTitle)
                && mDescription.equals(that.mDescription)
                && Objects.equals(mUrl, that.mUrl)
                && Objects.equals(mImageUrl, that.mImageUrl);

    }

    @Override
    public int hashCode() {
        return 31 * mPublished.hashCode() + Objects.hashCode(mUrl);
    }
}
