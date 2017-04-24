package com.alexcoliveira1.lighttalesreader.network;

/**
 * Created by alex on 18/04/17.
 */
import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;

import com.alexcoliveira1.lighttalesreader.data.Novels;

public class NovelsLoader extends AsyncTaskLoader<Novels> {

    public NovelsLoader(Context context) {
        super(context);
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }

    @Override
    public Novels loadInBackground() {
        return NetworkUtils.getNovels();
    }
}


