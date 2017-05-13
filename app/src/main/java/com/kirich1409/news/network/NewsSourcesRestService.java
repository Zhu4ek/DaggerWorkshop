package com.kirich1409.news.network;

import android.support.annotation.NonNull;

import com.kirich1409.news.network.data.NewsSourcesResponseDto;

import io.reactivex.Single;
import retrofit2.http.GET;

/**
 * @authror Kirill Rozov
 * @date 1/5/17.
 */

public interface NewsSourcesRestService {

    @NonNull
    @GET("sources")
    Single<NewsSourcesResponseDto> sources();
}
