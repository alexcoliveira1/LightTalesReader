package com.alexcoliveira1.lighttalesreader.network;

/**
 * Created by alex on 18/04/17.
 */

/*
 * Copyright (C) 2016 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;

import com.alexcoliveira1.lighttalesreader.data.Novels;


public class NovelChaptersLoader extends AsyncTaskLoader<Novels> {

    // Variable that stores the search string.
    private String mQueryString;

    // Constructor providing a reference to the search term.
    public NovelChaptersLoader(Context context, String queryString) {
        super(context);
        mQueryString = queryString;
    }

    @Override
    protected void onStartLoading() {
        forceLoad(); // Starts the loadInBackground method
    }

    @Override
    public Novels loadInBackground() {
        return NetworkUtils.getNovels();
    }
}


