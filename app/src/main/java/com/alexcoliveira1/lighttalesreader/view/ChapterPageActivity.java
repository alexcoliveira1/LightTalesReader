package com.alexcoliveira1.lighttalesreader.view;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.v13.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.alexcoliveira1.lighttalesreader.R;
import com.alexcoliveira1.lighttalesreader.data.Chapter;
import com.alexcoliveira1.lighttalesreader.data.Novel;

public class ChapterPageActivity extends AppCompatActivity {

    private final static String TAG = "ChapterPageActivity";
    private PagerAdapter mPagerAdapter;
    private ViewPager mPager;
    private Novel novel;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chapter_page);

        Intent i = getIntent();
        Bundle b = i.getExtras();
        novel = b.getParcelable("NOVEL");
        Chapter chapter = b.getParcelable("CHAPTER");
        Log.d(TAG, "CurrentPosition("+chapter.getNumber()+")="+chapter.getSlug());

        toolbar = (Toolbar) findViewById(R.id.navigation_toolbar);
        toolbar.setTitle(chapter.getName());
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        mPager = (ViewPager) findViewById(R.id.pager);
        mPagerAdapter = new ScreenSlidePagerAdapter(getFragmentManager());
        mPager.setAdapter(mPagerAdapter);
        mPager.setCurrentItem(chapter.getNumber()-1);
        mPager.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                // When changing pages, reset the action bar actions since they are dependent
                // on which page is currently active. An alternative approach is to have each
                // fragment expose actions itself (rather than the activity exposing actions),
                // but for simplicity, the activity provides the actions in this sample.
                invalidateOptionsMenu();
                toolbar.setTitle(novel.getChapters().get(position).getName());
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        Log.d(TAG, "onCreateOptionsMenu");
        getMenuInflater().inflate(R.menu.menu_chapter_page, menu);

        menu.findItem(R.id.action_previous).setEnabled(mPager.getCurrentItem() > 0);
        menu.findItem(R.id.action_next).setEnabled(mPager.getCurrentItem() < (mPagerAdapter.getCount()-1));

        Log.d(TAG, "Previous enabled = " + (mPager.getCurrentItem() > 0));
        Log.d(TAG, "Next enabled = " + (mPager.getCurrentItem() < (mPagerAdapter.getCount()-1)));

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                Log.d(TAG, "HOME");
                super.onBackPressed();
                return true;

            case R.id.action_previous:
                mPager.setCurrentItem(mPager.getCurrentItem() - 1);
                return true;

            case R.id.action_next:
                mPager.setCurrentItem(mPager.getCurrentItem() + 1);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private class ScreenSlidePagerAdapter extends FragmentStatePagerAdapter {
        public ScreenSlidePagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            Log.d(TAG, "getItem("+position+")="+novel.getChapters().get(position).getSlug());
            return ChapterPageFragment.newInstance(novel.getChapters().get(position));
        }

        @Override
        public int getCount() {
            return novel.getChapters().size();
        }
    }
}
