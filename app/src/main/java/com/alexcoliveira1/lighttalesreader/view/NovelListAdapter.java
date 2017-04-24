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

        public NovelViewHolder(View itemView, NovelListAdapter adapter) {
            super(itemView);
            novelItemView = (TextView) itemView.findViewById(R.id.novel_title);
            this.mAdapter = adapter;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
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

    @Override
    public NovelViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View mItemView = mInflater.inflate(R.layout.listview_item, parent, false);
        return new NovelViewHolder(mItemView, this);
    }

    @Override
    public void onBindViewHolder(NovelViewHolder holder, int position) {
        String mCurrent = mNovelList.get(position).getTitle();

        holder.setNovel(mNovelList.get(position));
        holder.novelItemView.setText(mCurrent);
    }

    @Override
    public int getItemCount() {
        return mNovelList.size();
    }
}


