package com.kirich1409.news.network.data;

import android.os.Parcel;
import android.os.Parcelable;
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
        ArticleDto.PROPERTY_TITLE,
        ArticleDto.PROPERTY_DESCRIPTION,
        ArticleDto.PROPERTY_URL,
        ArticleDto.PROPERTY_IMAGE_URL,
        ArticleDto.PROPERTY_PUBLISHED,
})
@Keep
public class ArticleDto implements Parcelable {

    public static final Parcelable.Creator<ArticleDto> CREATOR = new ArticleDtoCreator();

    public static final String PROPERTY_TITLE = "title";
    public static final String PROPERTY_DESCRIPTION = "description";
    public static final String PROPERTY_URL = "url";
    public static final String PROPERTY_IMAGE_URL = "urlToImage";
    public static final String PROPERTY_PUBLISHED = "publishedAt";

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
    @Nullable
    private final OffsetDateTime mPublished;

    @JsonCreator
    public ArticleDto(@NonNull @JsonProperty(PROPERTY_TITLE) final String title,
                      @NonNull @JsonProperty(PROPERTY_DESCRIPTION) final String description,
                      @Nullable @JsonProperty(PROPERTY_URL) final String url,
                      @Nullable @JsonProperty(PROPERTY_IMAGE_URL) final String imageUrl,
                      @Nullable @JsonProperty(PROPERTY_PUBLISHED) final OffsetDateTime published) {
        mTitle = title;
        mDescription = description;
        mUrl = url;
        mImageUrl = imageUrl;
        mPublished = published;
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

    @Nullable
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
                && mTitle.equals(that.mTitle)
                && mDescription.equals(that.mDescription)
                && Objects.equals(mUrl, that.mUrl)
                && Objects.equals(mImageUrl, that.mImageUrl);

    }

    @Override
    public int hashCode() {
        return 31 * mPublished.hashCode() + Objects.hashCode(mUrl);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mTitle);
        dest.writeString(mDescription);
        dest.writeString(mUrl);
        dest.writeString(mImageUrl);
        dest.writeSerializable(mPublished);
    }

    protected ArticleDto(Parcel in) {
        mTitle = in.readString();
        mDescription = in.readString();
        mUrl = in.readString();
        mImageUrl = in.readString();
        mPublished = (OffsetDateTime) in.readSerializable();
    }

    private static class ArticleDtoCreator implements Creator<ArticleDto> {

        @Override
        public ArticleDto createFromParcel(Parcel source) {
            return new ArticleDto(source);
        }

        @Override
        public ArticleDto[] newArray(int size) {
            return new ArticleDto[size];
        }
    }
}
