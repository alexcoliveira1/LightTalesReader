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


package com.alexcoliveira1.lighttalesreader.view;


import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alexcoliveira1.lighttalesreader.R;
import com.alexcoliveira1.lighttalesreader.data.Novel;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class NovelListFragment extends Fragment implements UpdateListInterface {

    private static final String TAG = "NovelListFragment";
    private RecyclerView mRecyclerView;
    private NovelListAdapter mAdapter;
    private LinkedList<Novel> novels = new LinkedList<>();

    public static NovelListFragment newInstance(LinkedList<Novel> novels) {
        NovelListFragment fragment = new NovelListFragment();

        Bundle args = new Bundle();
        args.putParcelableArrayList("novels", new ArrayList<Parcelable>(novels));
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.novellist_fragment, container, false);

        Bundle args = getArguments();

        if (args != null) {
            Log.d(TAG, "Args found");
            List<Novel> lNovel = args.getParcelableArrayList("novels");
            if (lNovel != null) {
                Log.d(TAG, "List NOT null = " + lNovel.size());
                novels.clear();
                novels.addAll(lNovel);
            }
        }


        mRecyclerView = (RecyclerView) view.findViewById(R.id.recyclerview_novels);
        mAdapter = new NovelListAdapter(container.getContext(), novels);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(container.getContext()));
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(mRecyclerView.getContext(),
                DividerItemDecoration.VERTICAL);
        mRecyclerView.addItemDecoration(dividerItemDecoration);

        return view;
    }

    @Override
    public void updateList(List<Novel> novels) {
        Log.d(TAG, "updateList() = " + novels.size());
        this.novels.clear();
        this.novels.addAll(novels);
        if(isVisible()) {
            Log.d(TAG, "VISIBLE");
            mRecyclerView.getAdapter().notifyDataSetChanged();
        }
    }
}
