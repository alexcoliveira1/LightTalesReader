package com.alexcoliveira1.lighttalesreader.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

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

        //Log.d(TAG, "TITLE = "+novel.getTitle());
        //Log.d(TAG, "From " + novel.getFromChapter() + " To " + novel.getToChapter());

        setTitle(novel.getTitle());

        ChapterListFragment chapterListFragment = new ChapterListFragment();

        chapterListFragment.setArguments(b);
        chapterListFragment.setRetainInstance(true);

        getSupportFragmentManager().beginTransaction()
               .add(R.id.chapterlist_container, chapterListFragment).commit();

    }

    @Override
    public void onAttachFragment(Fragment fragment) {
        Log.d(TAG, "onAttachFragment");
        if(fragment instanceof ChapterListFragment) {
            //setListener((ChapterListFragment) fragment);
        }
    }
}
