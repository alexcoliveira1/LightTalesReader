package com.alexcoliveira1.lighttalesreader.view;

import android.app.Fragment;
import android.app.LoaderManager;
import android.content.Loader;
import android.os.Bundle;
import android.text.Html;
import android.text.Spanned;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.alexcoliveira1.lighttalesreader.R;
import com.alexcoliveira1.lighttalesreader.data.Chapter;
import com.alexcoliveira1.lighttalesreader.network.ChapterContentLoader;

public class ChapterPageFragment extends Fragment implements LoaderManager.LoaderCallbacks<Chapter>{

    private Chapter chapter;
    private final static String TAG = "ChapterPageFragment";
    private TextView chapterContentTextView;

    public ChapterPageFragment() {
        // Required empty public constructor
    }

    public static ChapterPageFragment newInstance(Chapter chapter) {
        ChapterPageFragment fragment = new ChapterPageFragment();
        Bundle args = new Bundle();
        args.putParcelable("CHAPTER", chapter);
        fragment.setArguments(args);
        Log.d(TAG, "newInstance - " + chapter.getSlug());
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            chapter = getArguments().getParcelable("CHAPTER");
            Log.d(TAG, "onCreate - " + chapter.getSlug());
        }

        if(savedInstanceState == null) {
            //Check if a Loader is running, if it is, reconnect to it
            if (getActivity().getLoaderManager().getLoader(10000+chapter.getChapterId()) != null) {
                getActivity().getLoaderManager().initLoader(10000+chapter.getChapterId(), null, this);
            }
            getActivity().getLoaderManager().restartLoader(10000+chapter.getChapterId(), new Bundle(), this);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.d(TAG, "onCreateView - " + chapter.getSlug());

        ViewGroup vg = (ViewGroup)inflater.inflate(R.layout.fragment_chapter_page, container, false);

        chapterContentTextView = (TextView) vg.findViewById(R.id.chapter_content);

        String tvContent = chapter.getContent();
        if(tvContent == null || "".compareTo(tvContent) == 0)
            tvContent = chapter.getName();

        Spanned htmlAsSpanned = fromHtml(tvContent);
        chapterContentTextView.setText(htmlAsSpanned);

        return vg;
    }

    @Override
    public Loader<Chapter> onCreateLoader(int id, Bundle args) {
        Log.d(TAG, "onCreateLoader - " + chapter.getSlug());
        return new ChapterContentLoader(getActivity().getApplicationContext(), chapter);
    }

    @SuppressWarnings("deprecation")
    public static Spanned fromHtml(String html){
        Spanned result;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            result = Html.fromHtml(html,Html.FROM_HTML_MODE_LEGACY);
        } else {
            result = Html.fromHtml(html);
        }
        return result;
    }

    @Override
    public void onLoadFinished(Loader<Chapter> loader, Chapter data) {
        this.chapter = data;

        Spanned htmlAsSpanned = fromHtml(chapter.getContent());
        chapterContentTextView.setText(htmlAsSpanned);

        Log.d(TAG, "onLoadFinished - " + data.getSlug());
    }

    @Override
    public void onLoaderReset(Loader<Chapter> loader) {

    }
}
