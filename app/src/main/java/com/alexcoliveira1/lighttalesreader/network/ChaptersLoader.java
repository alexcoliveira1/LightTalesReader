package com.alexcoliveira1.lighttalesreader.network;

/**
 * Created by alex on 18/04/17.
 */


import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;
import android.util.Log;

import com.alexcoliveira1.lighttalesreader.data.Novel;

public class ChaptersLoader extends AsyncTaskLoader<Novel> {

    // Variable that stores the search string.
    private Novel novel;
    private boolean loaded;

    // Constructor providing a reference to the search term.
    public ChaptersLoader(Context context, Novel novel) {
        super(context);
        this.novel = novel;
        this.loaded = false;
    }

    /**
     * This method is invoked by the LoaderManager whenever the loader is started
     */
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


