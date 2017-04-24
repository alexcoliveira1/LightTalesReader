package com.alexcoliveira1.lighttalesreader.view;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.content.Loader;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.LoaderManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.support.v7.widget.Toolbar;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;

import com.alexcoliveira1.lighttalesreader.R;
import com.alexcoliveira1.lighttalesreader.data.Novels;
import com.alexcoliveira1.lighttalesreader.network.NovelsLoader;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,LoaderManager.LoaderCallbacks<Novels> {

    TabLayout tabLayout;
    ViewPager viewPager;
    PagerAdapter adapter;

    private static final String TAG = "MyActivity";
    public Novels novels = new Novels();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.d(TAG, "onCreate");

        createTabs();

        createFloatingActionButton();

        createActionBar();

        if(getSupportLoaderManager().getLoader(0)!=null){
            getSupportLoaderManager().initLoader(0,null,this);
        }
        getSupportLoaderManager().restartLoader(0, new Bundle(), this);

    }

    private void createTabs() {
        adapter = new PagerAdapter(getSupportFragmentManager(), novels);

        viewPager = (ViewPager) findViewById(R.id.pager);
        viewPager.setAdapter(adapter);

        tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }
        });

    }

    private void createFloatingActionButton() {
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    private void createActionBar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public Loader<Novels> onCreateLoader(int id, Bundle args) {
        Log.d(TAG, "onCreateLoader");
        return new NovelsLoader(this);
    }

    @Override
    public void onLoadFinished(Loader<Novels> loader, Novels novels) {

        this.novels.copy(novels);

        Fragment frag0 = adapter.getFragment(0);
        if(frag0 != null && frag0 instanceof NovelListFragment)
            ((UpdateListInterface) frag0).updateList(novels.getTranslatedNovels());
        Fragment frag1 = adapter.getFragment(1);
        if(frag1 != null && frag1 instanceof NovelListFragment)
            ((UpdateListInterface) frag1).updateList(novels.getOriginalNovels());

    }

    @Override
    public void onLoaderReset(Loader<Novels> loader) {

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        return false;
    }
}
