package com.alexcoliveira1.lighttalesreader.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;

import com.alexcoliveira1.lighttalesreader.R;
import com.alexcoliveira1.lighttalesreader.data.Novel;

public class ChaptersActivity extends AppCompatActivity{

    private static final String TAG = "ChaptersActivity";
    private Novel novel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate");
        setContentView(R.layout.activity_chapters);

        Intent i = getIntent();
        Bundle b = i.getExtras();
        novel = b.getParcelable("NOVEL");

        Toolbar toolbar = (Toolbar) findViewById(R.id.navigation_toolbar);
        toolbar.setTitle(novel.getTitle());
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        ChapterListFragment chapterListFragment = new ChapterListFragment();
        chapterListFragment.setArguments(b);
        chapterListFragment.setRetainInstance(true);

        getSupportFragmentManager().beginTransaction()
               .add(R.id.chapterlist_container, chapterListFragment).commit();

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == android.R.id.home) {
            Log.d(TAG, "HOME");
            super.onBackPressed();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
