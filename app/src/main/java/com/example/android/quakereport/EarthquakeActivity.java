/*
 * Copyright (C) 2016 The Android Open Source Project
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
package com.example.android.quakereport;

import android.app.LoaderManager;
import android.content.Context;
import android.content.Intent;
import android.content.Loader;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;


public class EarthquakeActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<Earthquake>>, SwipeRefreshLayout.OnRefreshListener{
    private static final String USGS_URL ="https://earthquake.usgs.gov/fdsnws/event/1/query?format=geojson&eventtype=earthquake&orderby=magnitude&minmag=4.5";
    private static final String TAG = EarthquakeActivity.class.getName();

    /**
     * Constant value for the earthquake loader ID. We can choose any integer.
     * This really only comes into play if you're using multiple loaders.
     */
    private static final int LOADER_ID = 1;

    /** Adapter for the list of earthquakes */
    private EarthquakeAdapter mAdapter;

    private TextView emptyView;
    private ProgressBar loadingIndicator;
    private SwipeRefreshLayout swipeRefreshEmpty;
    private SwipeRefreshLayout swipeRefreshList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.earthquake_activity);

        // Find a reference to the {@link ListView} in the layout
        ListView earthquakeListView = findViewById(R.id.list);

        /** Set the empty state TextView onto the ListView */
        emptyView = findViewById(R.id.empty_list_view);
        swipeRefreshEmpty = findViewById(R.id.swiperefresh_empty);
        swipeRefreshList = findViewById(R.id.swiperefresh_listview);

        earthquakeListView.setEmptyView(swipeRefreshEmpty);

        // Create a new {@link ArrayAdapter} of earthquakes
        mAdapter = new EarthquakeAdapter(this, new ArrayList<Earthquake>());

        // Set the adapter on the {@link ListView}
        // so the list can be populated in the user interface
        earthquakeListView.setAdapter(mAdapter);

        earthquakeListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Earthquake currentEarthquake = mAdapter.getItem(position);
                Uri earthquakeUri = Uri.parse(currentEarthquake.getUrl());
                Intent intent = new Intent(Intent.ACTION_VIEW, earthquakeUri);
                startActivity(intent);
            }
        });

        //TODO might have to remove these 2 - swipe related
        onCreateSwipeToRefresh(swipeRefreshList);
        onCreateSwipeToRefresh(swipeRefreshEmpty);

        //Connection stuff following
        ConnectivityManager cm = (ConnectivityManager) this.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        boolean isConnected = activeNetwork != null && activeNetwork.isConnectedOrConnecting();

        loadingIndicator = findViewById(R.id.loading_indicator);

        if(isConnected){
            Log.d(TAG, "onCreate: CONNECTED initLoader called ------------------------------------");
            // Initialize the loader. Pass in the int ID constant defined above and pass in null for
            // the bundle. Pass in this activity for the LoaderCallbacks parameter (which is valid
            // because this activity implements the LoaderCallbacks interface).
            getLoaderManager().initLoader(LOADER_ID, null, this);
        } else {
            Log.d(TAG, "onCreate: NOT CONNECTED ------------------");
            loadingIndicator.setVisibility(View.GONE);
            emptyView.setText(R.string.no_internet);
        }


        //Start the AsyncTask to fetch earthquake data
        //new DownloadTask().execute(USGS_URL);
    }

    private void onCreateSwipeToRefresh(SwipeRefreshLayout refreshLayout) {
        refreshLayout.setOnRefreshListener(this);
//        refreshLayout.setColorScheme(
//                android.R.color.holo_blue_light,
//                android.R.color.holo_orange_light,
//                android.R.color.holo_green_light,
//                android.R.color.holo_red_light);
    }

    @Override
    public void onRefresh() {
        ConnectivityManager cm = (ConnectivityManager) this.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        boolean isConnected = activeNetwork != null && activeNetwork.isConnectedOrConnecting();

        loadingIndicator = findViewById(R.id.loading_indicator);

        if(isConnected){
            Log.d(TAG, "onRefresh: CONNECTED initLoader called ------------------------------------");
            // Initialize the loader. Pass in the int ID constant defined above and pass in null for
            // the bundle. Pass in this activity for the LoaderCallbacks parameter (which is valid
            // because this activity implements the LoaderCallbacks interface).
            getLoaderManager().initLoader(LOADER_ID, null, this);
        } else {
            Log.d(TAG, "onRefresh: NOT CONNECTED ----------------");
            loadingIndicator.setVisibility(View.GONE);
            emptyView.setText(R.string.no_internet);
        }
    }

    @Override
    public Loader<List<Earthquake>> onCreateLoader(int id, Bundle args) {
        Log.d(TAG, "onCreateLoader: ----------------------");
        return new EarthquakeLoader(EarthquakeActivity.this, USGS_URL);
    }

    @Override
    public void onLoadFinished(Loader<List<Earthquake>> loader, List<Earthquake> data) {
        Log.d(TAG, "onLoadFinished: -----------------------");

        //useless
        //mAdapter.setEarthquakes(data);

        // Clear the adapter of previous earthquake data
        mAdapter.clear();

        loadingIndicator.setVisibility(View.GONE);

        // If there is a valid list of {@link Earthquake}s, then add them to the adapter's
        // data set. This will trigger the ListView to update.
        if(data != null && !data.isEmpty()){
            mAdapter.addAll(data);
        } else {
            emptyView.setText(R.string.no_earthquakes);
        }
    }


    @Override
    public void onLoaderReset(Loader<List<Earthquake>> loader) {
        Log.d(TAG, "onLoaderReset: -----------------------");
        // Loader reset, so we can clear out our existing data.
        mAdapter.clear();
    }



    /*
    private class DownloadTask extends AsyncTask<String, Void, List<Earthquake>>{

        @Override
        protected List<Earthquake> doInBackground(String... strings) {
            return QueryUtils.requestEarthquakeData(strings[0]);
        }

        @Override
        protected void onPostExecute(List<Earthquake> earthquakes) {
            //Clear the adapter of previous earthquake data
            mAdapter.clear();

            // If there is a valid list of {@link Earthquake}s, then add them to the adapter's
            // data set. This will trigger the ListView to update.
            if(earthquakes != null && !earthquakes.isEmpty()){
                mAdapter.addAll(earthquakes);
            }
        }
    }
    */
}
