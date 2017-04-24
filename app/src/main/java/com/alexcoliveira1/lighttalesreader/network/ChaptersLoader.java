package com.alexcoliveira1.lighttalesreader.network;

/**
 * Created by alex on 18/04/17.
 */


import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;
import android.util.Log;

import com.alexcoliveira1.lighttalesreader.data.Novel;

public class ChaptersLoader extends AsyncTaskLoader<Novel> {

    private Novel novel;
    private boolean loaded;

    public ChaptersLoader(Context context, Novel novel) {
        super(context);
        this.novel = novel;
        this.loaded = false;
    }

    @Override
    protected void onStartLoading() {
        Log.d(ChaptersLoader.class.getSimpleName(), "ChaptersLoader.onStartLoading");
        if(!loaded)
            forceLoad(); // Starts the loadInBackground method
    }

    @Override
    public Novel loadInBackground() {
        Log.d(ChaptersLoader.class.getSimpleName(), "ChaptersLoader.loadInBackground");
        novel = NetworkUtils.getNovelChapters(novel);
        loaded = true;
        return novel;
    }
}


