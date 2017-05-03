package com.kirich1409.workshop.dagger.network;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.kirich1409.workshop.dagger.network.data.ArticlesResponseDto;
import com.kirich1409.workshop.dagger.network.data.ArticlesResponseDto.SortDef;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * @authror Kirill Rozov
 * @date 1/5/17.
 */
public interface ArticlesRestService {

    @NonNull
    @GET("/articles")
    Single<ArticlesResponseDto> articles(
            @Query("source") @NonNull String source,
            @Query("sortBy") @SortDef @Nullable String sortBy);
}
