package com.example.suggestmeamovie;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Context;
import android.content.Intent;
import android.content.Loader;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;

import com.example.suggestmeamovie.view_adapters.MoviesViewAdapter;
import com.example.suggestmeamovie.data.Movie;
import com.example.suggestmeamovie.data.MoviesData;
import com.example.suggestmeamovie.data_loaders.MoviesLoader;
import com.example.suggestmeamovie.fragments.FragmentAdapter;
import com.google.android.material.tabs.TabLayout;

import java.util.List;

public class MainActivity extends AppCompatActivity implements android.app.LoaderManager.LoaderCallbacks<MoviesData> , MoviesViewAdapter.ItemClicked {

    TabLayout mTabLayout;
    ViewPager2 mViewpager;
    FragmentAdapter fragmentAdapter;

    public static List<Movie> popularMoviesList;
    public static List<Movie> topratedMoviesList ;
    public static List<Movie> upcomingMoviesList ;
    android.app.LoaderManager moviesLoaderManager;

    final int LOADER_ID = 0;
    private String apiKey;
    int movieType;

     private static ConnectivityManager connectivityManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //check whether we connected or not
       connectivityManager = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);

        mTabLayout = findViewById(R.id.tabView);
        mViewpager = findViewById(R.id.viewPager);

        apiKey = getString(R.string.api_key);
        moviesLoaderManager = getLoaderManager();
        moviesLoaderManager.initLoader(LOADER_ID, null, this);
    }

    public  static boolean isConnected(){
        return connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED;

    }

    @Override
    public Loader<MoviesData> onCreateLoader(int i, Bundle bundle) {
        return new MoviesLoader(this ,apiKey);
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
        Intent intent = new Intent(MainActivity.this, Movie_Details_Activity.class);
        intent.putExtra(getString(R.string.index),i);
        intent.putExtra(getString(R.string.movie_type),movieType);
        startActivity(intent);
    }
}