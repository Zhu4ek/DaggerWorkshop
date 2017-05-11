package com.kirich1409.news.network.data;

import android.support.annotation.NonNull;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.List;

/**
 * @authror Kirill Rozov
 * @date 1/5/17.
 */

@JsonPropertyOrder({
        NewsSourcesResponseDto.PROPERTY_STATUS,
        NewsSourcesResponseDto.PROPERTY_SOURCES,
})
public class NewsSourcesResponseDto extends ResponseDto<NewsSourceDto> {

    public static final String PROPERTY_SOURCES = "sources";

    @JsonCreator
    public NewsSourcesResponseDto(
            @NonNull @JsonProperty(PROPERTY_STATUS) final String status,
            @NonNull @JsonProperty(PROPERTY_SOURCES) final List<NewsSourceDto> sources,
            @NonNull @JsonProperty(PROPERTY_ERROR_CODE) int errorCode,
            @NonNull @JsonProperty(PROPERTY_ERROR_MESSAGE) String errorMessage) {
        super(status, errorCode, errorMessage, sources);
    }

    @NonNull
    @JsonProperty
    public List<NewsSourceDto> getSources() {
        return mData;
    }
}
