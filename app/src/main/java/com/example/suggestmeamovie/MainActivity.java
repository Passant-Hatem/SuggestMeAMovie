package com.example.suggestmeamovie;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.content.Loader;
import android.os.Bundle;

import com.example.suggestmeamovie.adapters.MoviesViewAdapter;
import com.example.suggestmeamovie.data.Movie;
import com.example.suggestmeamovie.data.MoviesData;
import com.example.suggestmeamovie.data_loaders.MoviesLoader;
import com.google.android.material.tabs.TabLayout;

import java.util.List;

public class MainActivity extends AppCompatActivity implements android.app.LoaderManager.LoaderCallbacks<MoviesData> , MoviesViewAdapter.ItemClicked {

    TabLayout mTabLayout;
    ViewPager2 mViewpager;
    FragmentAdapter fragmentAdapter;

    static List<Movie> popularMoviesList;
    static List<Movie> topratedMoviesList ;
    static List<Movie> upcomingMoviesList ;
    android.app.LoaderManager moviesLoaderManager;

    final int LOADER_ID = 0;

    int movieType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTabLayout = findViewById(R.id.tabView);
        mViewpager = findViewById(R.id.viewPager);


        moviesLoaderManager = getLoaderManager();
        moviesLoaderManager.initLoader(LOADER_ID, null, this);
    }

    @Override
    public Loader<MoviesData> onCreateLoader(int i, Bundle bundle) {
        return new MoviesLoader(this);
    }

    @Override
    public void onLoadFinished(Loader<MoviesData> loader, MoviesData movies) {

        popularMoviesList = movies.getPopularmoviesList();
        topratedMoviesList = movies.getTopratedmoviesList();
        upcomingMoviesList = movies.getUpcomingmoviesList();

        FragmentManager mFragmentManager = getSupportFragmentManager();
        fragmentAdapter = new FragmentAdapter(mFragmentManager, getLifecycle());
        mViewpager.setAdapter(fragmentAdapter);

        mTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                mViewpager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        mViewpager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                movieType = position;
                mTabLayout.selectTab(mTabLayout.getTabAt(position));
            }
        });

    }

    @Override
    public void onLoaderReset(Loader<MoviesData> loader) {

    }

    @Override
    public void onItemClicked(int i) {
        Intent intent = new Intent(MainActivity.this, Movie_Details.class);
        intent.putExtra(getString(R.string.index),i);
        intent.putExtra(getString(R.string.movie_type),movieType);
        startActivity(intent);
    }
}