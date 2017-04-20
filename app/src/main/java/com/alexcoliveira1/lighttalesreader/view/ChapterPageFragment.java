package com.alexcoliveira1.lighttalesreader.view;

import android.app.Fragment;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.app.LoaderManager;
import android.content.Loader;
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

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ChapterPageFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ChapterPageFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ChapterPageFragment extends Fragment implements LoaderManager.LoaderCallbacks<Chapter>{

    private Chapter chapter;
    private final static String TAG = "ChapterPageFragment";
    private TextView chapterContentTextView;

    //private OnFragmentInteractionListener mListener;

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
            if (getActivity().getLoaderManager().getLoader(10000+chapter.getNumber()) != null) {
                getActivity().getLoaderManager().initLoader(10000+chapter.getNumber(), null, this);
            }
            getActivity().getLoaderManager().restartLoader(10000+chapter.getNumber(), new Bundle(), this);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
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

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        /*if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }*/
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        /*if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }*/
    }

    @Override
    public void onDetach() {
        super.onDetach();
        //mListener = null;
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

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
