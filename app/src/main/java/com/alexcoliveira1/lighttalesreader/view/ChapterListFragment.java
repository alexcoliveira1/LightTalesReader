package com.alexcoliveira1.lighttalesreader.view;

/**
 * Created by alex on 19/04/17.
 */

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alexcoliveira1.lighttalesreader.R;
import com.alexcoliveira1.lighttalesreader.data.Chapter;
import com.alexcoliveira1.lighttalesreader.data.Novel;
import com.alexcoliveira1.lighttalesreader.network.ChaptersLoader;

import java.util.LinkedList;


public class ChapterListFragment extends Fragment implements
        LoaderManager.LoaderCallbacks<Novel>
{

    private static final String TAG = "ChapterListFragment";
    private RecyclerView mRecyclerView;
    private ChapterListAdapter mAdapter;
    private Novel novel;
    private LinkedList<Chapter> chapters = new LinkedList<>();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle args = getArguments();

        if (args != null) {
            Log.d(TAG, "Args found");
            novel = args.getParcelable("NOVEL");
            if (novel != null) {
                Log.d(TAG, "Novel NOT null = " + novel.getFromChapter() + " - " + novel.getToChapter());
            }
        }

        Log.d(TAG, "onCreate - "+ novel.getTitle());
        Log.d(TAG, "From " + novel.getFromChapter() + " To " + novel.getToChapter());

        if(savedInstanceState == null) {
            //Check if a Loader is running, if it is, reconnect to it
            if (getActivity().getSupportLoaderManager().getLoader(1) == null) {
                getActivity().getSupportLoaderManager().initLoader(1, null, this);
            }
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.chapterlist_fragment, container, false);

        Log.d(TAG, "onCreateView");

        return view;
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d(TAG, "onPause");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d(TAG, "onStop");
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.d(TAG, "onActivityCreated");
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d(TAG, "onStart");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d(TAG, "onResume");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.d(TAG, "onDestroyView");
    }

    @Override
    public Loader<Novel> onCreateLoader(int id, Bundle args) {
        Log.d(TAG, "onCreateLoader");
        return new ChaptersLoader(getContext(), novel);
    }

    @Override
    public void onLoadFinished(Loader<Novel> loader, Novel data) {
        this.novel = data;

        Log.d(TAG, "Chapters " + data.getFromChapter()+"-"+data.getToChapter());

        // Create recycler view.
        mRecyclerView = (RecyclerView) getView().findViewById(R.id.recyclerview_chapters);
        // Create an adapter and supply the data to be displayed.
        chapters.clear();
        chapters.addAll(novel.getChapters());
        mAdapter = new ChapterListAdapter(getContext(), novel);
        // Connect the adapter with the recycler view.
        mRecyclerView.setAdapter(mAdapter);
        // Give the recycler view a default layout manager.
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(mRecyclerView.getContext(),
                DividerItemDecoration.VERTICAL);
        mRecyclerView.addItemDecoration(dividerItemDecoration);

    }

    @Override
    public void onLoaderReset(Loader<Novel> loader) {
        Log.d(TAG, "onLoaderReset");
    }
}
