package com.kirich1409.news.network.data;

import android.support.annotation.NonNull;
import android.support.annotation.StringDef;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.List;

/**
 * @authror Kirill Rozov
 * @date 1/5/17.
 */
abstract class ResponseDto<T> {

    public static final String PROPERTY_STATUS = "status";

    public static final String STATUS_OK = "ok";
    public static final String STATUS_ERROR = "error";

    @JsonProperty(PROPERTY_STATUS)
    @StatusDef
    @NonNull
    protected final String mStatus;

    @NonNull
    protected final List<T> mData;

    ResponseDto(@NonNull final String status,
                @NonNull final List<T> data) {
        mStatus = status;
        mData = data;
    }

    @Retention(RetentionPolicy.SOURCE)
    @StringDef({STATUS_ERROR, STATUS_OK})
    public @interface StatusDef {
    }
}
