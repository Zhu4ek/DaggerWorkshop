package com.kirich1409.news.network.data;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.Keep;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.Map;
import java.util.Objects;

/**
 * @authror Kirill Rozov
 * @date 1/5/17.
 */
@JsonPropertyOrder({
        NewsSourceDto.PROPERTY_ID,
        NewsSourceDto.PROPERTY_NAME,
        NewsSourceDto.PROPERTY_DESCRIPTION,
        NewsSourceDto.PROPERTY_URL,
})
@Keep
public class NewsSourceDto implements Parcelable {

    public static final String PROPERTY_ID = "id";
    public static final String PROPERTY_NAME = "name";
    public static final String PROPERTY_DESCRIPTION = "description";
    public static final String PROPERTY_URL = "url";

    public static final Parcelable.Creator<NewsSourceDto> CREATOR = new NewsSourceDtoCreator();

    @NonNull
    private final String mId;

    @NonNull
    private final String mName;

    @NonNull
    private final String mDescription;

    @Nullable
    private final String mUrl;

    @JsonCreator
    public NewsSourceDto(
            @NonNull @JsonProperty(PROPERTY_ID) final String id,
            @NonNull @JsonProperty(PROPERTY_NAME) final String name,
            @NonNull @JsonProperty(PROPERTY_DESCRIPTION) final String description,
            @Nullable @JsonProperty(PROPERTY_URL) final String url) {
        mId = id;
        mName = name;
        mDescription = description;
        mUrl = url == null || url.isEmpty() ? null : url;
    }

    protected NewsSourceDto(Parcel in) {
        mId = in.readString();
        mName = in.readString();
        mDescription = in.readString();
        mUrl = in.readString();
    }

    @Nullable
    private static String filterUrl(@NonNull Map<String, String> logosUrl) {
        for (Map.Entry<String, String> entry : logosUrl.entrySet()) {
            if (!TextUtils.isEmpty(entry.getValue())) {
                return entry.getValue();
            }
        }
        return null;
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
                Objects.equals(mUrl, newsSourceDto.mUrl);
    }

    @Override
    public int hashCode() {
        return mId.hashCode();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mId);
        dest.writeString(mName);
        dest.writeString(mDescription);
        dest.writeString(mUrl);
    }

    private static class NewsSourceDtoCreator implements Creator<NewsSourceDto> {

        @Override
        public NewsSourceDto createFromParcel(Parcel source) {
            return new NewsSourceDto(source);
        }

        @Override
        public NewsSourceDto[] newArray(int size) {
            return new NewsSourceDto[size];
        }
    }
}
