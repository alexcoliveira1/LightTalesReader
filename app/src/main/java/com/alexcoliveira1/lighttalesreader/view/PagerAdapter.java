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

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.util.SparseArray;
import android.view.ViewGroup;

import com.alexcoliveira1.lighttalesreader.data.Novels;

/**
 * Fragment to return the clicked tab.
 */
public class PagerAdapter extends FragmentStatePagerAdapter {
    private int positionSelected;
    private SparseArray<Fragment> mPageReferenceMap = new SparseArray<>();
    private Novels novels = new Novels();
    private String fragments [] = {"Translateds","Originals"};

    public PagerAdapter(FragmentManager fm, Novels novels) {
        super(fm);
        this.novels = novels;
    }

    @Override
    public Fragment getItem(int position) {
        Fragment myFragment;
        switch (position) {
            case 0:
                myFragment = NovelListFragment.newInstance(novels.getTranslatedNovels());
                break;
            case 1:
                myFragment = NovelListFragment.newInstance(novels.getOriginalNovels());
                break;
            default:
                myFragment = null;
                break;
        }
        if(myFragment != null)
            mPageReferenceMap.put(position, myFragment);
        positionSelected = position;
        return myFragment;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        super.destroyItem(container, position, object);
        mPageReferenceMap.remove(position);
    }

    public Fragment getFragment(int key) {
        return mPageReferenceMap.get(key);
    }

    @Override
    public int getCount() {
        return fragments.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return fragments[position];
    }
}
