package com.alexcoliveira1.lighttalesreader.view;

/**
 * Created by alex on 19/04/17.
 */

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.alexcoliveira1.lighttalesreader.R;
import com.alexcoliveira1.lighttalesreader.data.Chapter;
import com.alexcoliveira1.lighttalesreader.data.Novel;

import java.util.LinkedList;


public class ChapterListAdapter extends RecyclerView.Adapter<ChapterListAdapter.ChapterViewHolder> {

    private final Novel novel;
    private final LinkedList<Chapter> mChapterList;
    private final LayoutInflater mInflater;


    class ChapterViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {


        public final TextView chapterItemView;
        private Chapter chapter;
        final ChapterListAdapter mAdapter;
        private static final String TAG = "ChapterViewHolder";


        public Chapter getChapter() {
            return chapter;
        }

        public void setChapter(Chapter chapter) {
            this.chapter = chapter;
        }

        /**
         * Creates a new custom view holder to hold the view to display in the RecyclerView.
         *
         * @param itemView The view in which to display the data.
         * @param adapter The adapter that manages the the data and views for the RecyclerView.
         */
        public ChapterViewHolder(View itemView, ChapterListAdapter adapter) {
            super(itemView);
            chapterItemView = (TextView) itemView.findViewById(R.id.novel_title);
            this.mAdapter = adapter;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            chapterItemView.setText ("->"+ chapterItemView.getText());
            Log.d(TAG, chapter.getName());
            Intent intent = new Intent(v.getContext(), ChapterPageActivity.class);
            intent.putExtra("NOVEL", novel);
            intent.putExtra("CHAPTER", chapter);
            v.getContext().startActivity(intent);
        }
    }

    public ChapterListAdapter(Context context, Novel novel) {
        mInflater = LayoutInflater.from(context);
        this.mChapterList = novel.getChapters();
        this.novel = novel;
    }

    /**
     * Inflates an item view and returns a new view holder that contains it.
     * Called when the RecyclerView needs a new view holder to represent an item.
     *
     * @param parent The view group that holds the item views.
     * @param viewType Used to distinguish views, if more than one type of item view is used.
     * @return a view holder.
     */
    @Override
    public ChapterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // Inflate an item view.
        View mItemView = mInflater.inflate(R.layout.listview_item, parent, false);
        return new ChapterViewHolder(mItemView, this);
    }

    /**
     * Sets the contents of an item at a given position in the RecyclerView.
     * Called by RecyclerView to display the data at a specificed position.
     *
     * @param holder The view holder for that position in the RecyclerView.
     * @param position The position of the item in the RecycerView.
     */
    @Override
    public void onBindViewHolder(ChapterViewHolder holder, int position) {
        // Retrieve the data for that position.
        String mCurrent = mChapterList.get(position).getName();
        // Add the data to the view holder.
        holder.setChapter(mChapterList.get(position));
        holder.chapterItemView.setText(mCurrent);
    }

    /**
     * Returns the size of the container that holds the data.
     *
     * @return Size of the list of data.
     */
    @Override
    public int getItemCount() {
        return mChapterList.size();
    }
}


