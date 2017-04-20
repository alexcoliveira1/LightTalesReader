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

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.alexcoliveira1.lighttalesreader.R;
import com.alexcoliveira1.lighttalesreader.data.Novel;

import java.util.LinkedList;

import static android.provider.AlarmClock.EXTRA_MESSAGE;

/**
 * Shows how to implement a simple Adapter for a RecyclerView.
 * Demonstrates how to add a click handler for each item in the ViewHolder.
 */
public class NovelListAdapter extends RecyclerView.Adapter<NovelListAdapter.NovelViewHolder> {

    private final LinkedList<Novel> mNovelList;
    private final LayoutInflater mInflater;

    class NovelViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public final TextView novelItemView;
        private Novel novel;
        final NovelListAdapter mAdapter;


        public Novel getNovel() {
            return novel;
        }

        public void setNovel(Novel novel) {
            this.novel = novel;
        }

        /**
         * Creates a new custom view holder to hold the view to display in the RecyclerView.
         *
         * @param itemView The view in which to display the data.
         * @param adapter The adapter that manages the the data and views for the RecyclerView.
         */
        public NovelViewHolder(View itemView, NovelListAdapter adapter) {
            super(itemView);
            novelItemView = (TextView) itemView.findViewById(R.id.novel_title);
            this.mAdapter = adapter;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            // All we do here is prepend "Clicked! " to the text in the view, to verify that
            // the correct item was clicked. The underlying data does not change.
            novelItemView.setText ("+"+ novelItemView.getText());

            Intent intent = new Intent(v.getContext(), ChaptersActivity.class);
            intent.putExtra("NOVEL", novel);
            v.getContext().startActivity(intent);
        }
    }

    public NovelListAdapter(Context context, LinkedList<Novel> novelList) {
        mInflater = LayoutInflater.from(context);
        this.mNovelList = novelList;
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
    public NovelViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // Inflate an item view.
        View mItemView = mInflater.inflate(R.layout.listview_item, parent, false);
        return new NovelViewHolder(mItemView, this);
    }

    /**
     * Sets the contents of an item at a given position in the RecyclerView.
     * Called by RecyclerView to display the data at a specificed position.
     *
     * @param holder The view holder for that position in the RecyclerView.
     * @param position The position of the item in the RecycerView.
     */
    @Override
    public void onBindViewHolder(NovelViewHolder holder, int position) {
        // Retrieve the data for that position.
        String mCurrent = mNovelList.get(position).getTitle();
        // Add the data to the view holder.
        holder.setNovel(mNovelList.get(position));
        holder.novelItemView.setText(mCurrent);
    }

    /**
     * Returns the size of the container that holds the data.
     *
     * @return Size of the list of data.
     */
    @Override
    public int getItemCount() {
        return mNovelList.size();
    }
}


