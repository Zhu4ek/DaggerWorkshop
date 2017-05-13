package com.kirich1409.news.ui.article;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.kirich1409.news.NewsApp;
import com.kirich1409.news.R;
import com.kirich1409.news.network.data.ArticleDto;

public class ArticleActivity extends AppCompatActivity {

    private static final String EXTRA_ARTICLE = "article";

    private ArticleActivityComponent mActivityComponent;

    public static Intent newIntent(@NonNull Context context,
                                   @NonNull ArticleDto articleDto) {
        Intent intent = new Intent(context, ArticleActivity.class);
        intent.putExtra(EXTRA_ARTICLE, articleDto);
        return intent;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ArticleDto article = getIntent().getParcelableExtra(EXTRA_ARTICLE);
        if (article == null) {
            throw new IllegalArgumentException("Article is null");
        }

        mActivityComponent = ((NewsApp) getApplication())
                .getAppComponent()
                .articleActivityComponent()
                .article(article)
                .articleActivityModule(new ArticleActivityModule(this))
                .build();

        setContentView(R.layout.activity_article);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    @Override
    public void setTitle(CharSequence title) {
        super.setTitle(title);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle(title);
        }
    }

    final ArticleActivityComponent getActivityComponent() {
        return mActivityComponent;
    }
}
