package com.kirich1409.workshop.dagger.network;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.kirich1409.workshop.dagger.network.data.CountryCode;
import com.kirich1409.workshop.dagger.network.data.LanguageCode;
import com.kirich1409.workshop.dagger.network.data.NewsSourceDto.CategoryDef;
import com.kirich1409.workshop.dagger.network.data.NewsSourcesResponseDto;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * @authror Kirill Rozov
 * @date 1/5/17.
 */

public interface NewsSourcesRestService {

    @NonNull
    @GET("/sources")
    Single<NewsSourcesResponseDto> sources(
            @Nullable @Query("category") @CategoryDef String category,
            @Nullable @Query("language") @LanguageCode String language,
            @Nullable @Query("country") @CountryCode String country);
}
