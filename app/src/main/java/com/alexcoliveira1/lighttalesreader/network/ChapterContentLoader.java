package com.alexcoliveira1.lighttalesreader.network;

import android.content.Context;
import android.content.AsyncTaskLoader;

import com.alexcoliveira1.lighttalesreader.data.Chapter;

/**
 * Created by alex on 20/04/17.
 */

public class ChapterContentLoader extends AsyncTaskLoader<Chapter> {

    // Variable that stores the search string.
    private Chapter chapter;

    // Constructor providing a reference to the search term.
    public ChapterContentLoader(Context context, Chapter chapter) {
        super(context);
        this.chapter = chapter;
    }

    @Override
    protected void onStartLoading() {
        forceLoad(); // Starts the loadInBackground method
    }

    @Override
    public Chapter loadInBackground() {
        return NetworkUtils.getChapterContent(chapter);
    }
}
