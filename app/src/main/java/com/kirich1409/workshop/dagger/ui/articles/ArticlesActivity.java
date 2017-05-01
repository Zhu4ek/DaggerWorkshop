package com.kirich1409.workshop.dagger.ui.articles;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;

import com.kirich1409.workshop.dagger.R;
import com.kirich1409.workshop.dagger.network.data.ArticlesResponseDto;

public class ArticlesActivity extends AppCompatActivity {

    private static final String EXTRA_SOURCE = "source";
    private static final String EXTRA_SORT = "sort";

    public static Intent newIntent(@NonNull Context context,
                                   @NonNull String source) {
        return new Intent(context, ArticlesActivity.class)
                .putExtra(EXTRA_SOURCE, source);
    }

    public static Intent newIntent(@NonNull Context context,
                                   @NonNull String source,
                                   @NonNull @ArticlesResponseDto.SortDef String sort) {
        return newIntent(context, source)
                .putExtra(EXTRA_SORT, sort);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_articles);
    }
}
