package com.kirich1409.news.ui.articles;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;

import com.kirich1409.news.R;
import com.kirich1409.news.network.data.NewsSourceDto;
import com.kirich1409.news.util.Exceptions;

public class ArticlesActivity extends AppCompatActivity {

    private static final String EXTRA_SOURCE = "source";

    public static Intent newIntent(@NonNull Context context, @NonNull NewsSourceDto source) {
        return new Intent(context, ArticlesActivity.class)
                .putExtra(EXTRA_SOURCE, source);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        NewsSourceDto source = getIntent().getParcelableExtra(EXTRA_SOURCE);
        if (source == null) {
            throw Exceptions.newIllegalArgument(
                    "Intent must contains string extra \"%s\"", EXTRA_SOURCE);
        }
        setContentView(R.layout.activity_articles);
    }

    @Override
    public void setTitle(CharSequence title) {
        super.setTitle(title);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle(title);
        }
    }
}
