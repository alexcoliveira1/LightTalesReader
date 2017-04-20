package com.alexcoliveira1.lighttalesreader.view;

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

        //Check if a Loader is running, if it is, reconnect to it
        if(getSupportLoaderManager().getLoader(0)!=null){
            getSupportLoaderManager().initLoader(0,null,this);
        }
        getSupportLoaderManager().restartLoader(0, new Bundle(), this);



        /*NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);*/
    }

    private void createTabs() {
        // Use PagerAdapter to manage page views in fragments.
        adapter = new PagerAdapter(getSupportFragmentManager(), novels);

        viewPager = (ViewPager) findViewById(R.id.pager);
        viewPager.setAdapter(adapter);


        // Create an instance of the tab layout from the view.
        tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        tabLayout.setupWithViewPager(viewPager);
        //tabLayout.addTab(tabLayout.newTab().setText("Translateds"));
        //tabLayout.addTab(tabLayout.newTab().setText("Originals"));
        //tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        // Setting a listener for clicks.
        //viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

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

    @Override
    public void onBackPressed() {
        //DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        //if (drawer.isDrawerOpen(GravityCompat.START)) {
        //    drawer.closeDrawer(GravityCompat.START);
        //} else {
            super.onBackPressed();
        //}
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
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        //drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public Loader<Novels> onCreateLoader(int id, Bundle args) {
        Log.d(TAG, "onCreateLoader");
        return new NovelsLoader(this, args.getString("queryString"));
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
}
