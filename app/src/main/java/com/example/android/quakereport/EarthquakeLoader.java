package com.example.android.quakereport;

import android.content.AsyncTaskLoader;
import android.content.Context;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ccojo on 5/13/2018.
 */

public class EarthquakeLoader extends AsyncTaskLoader<List<Earthquake>> {
    private static final String USGS_URL ="https://earthquake.usgs.gov/fdsnws/event/1/query?format=geojson&eventtype=earthquake&orderby=time&minmag=5&limit=15";
    private static final String TAG = EarthquakeLoader.class.getSimpleName();

    public EarthquakeLoader(Context context) {
        super(context);
    }

    @Override
    public List<Earthquake> loadInBackground() {
        List<Earthquake> result = QueryUtils.requestEarthquakeData(USGS_URL);

        Log.d(TAG, "loadInBackground: " + result.size());

        return result;
    }
}
