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

//import android.icu.util.Calendar;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class EarthquakeActivity extends AppCompatActivity {

    public static final String LOG_TAG = EarthquakeActivity.class.getName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.earthquake_activity);

        // Create a fake list of earthquake locations.
        ArrayList<Earthquake> earthquakes = new ArrayList<>();
        earthquakes.add(new Earthquake(3.4f, getString(R.string.san_francisco), 2018, 3, 29));
        earthquakes.add(new Earthquake(2.3f, getString(R.string.london), 2018, 3, 29));
        earthquakes.add(new Earthquake(5.4f, getString(R.string.mexico_city), 2018, 3, 29));
        earthquakes.add(new Earthquake(4.4f, getString(R.string.moscow), 2018, 3, 29));
        earthquakes.add(new Earthquake(1.4f, getString(R.string.rio_de_janeiro), 2018, 3, 29));
        earthquakes.add(new Earthquake(7.3f, getString(R.string.tokyo), 2018, 3, 29));
        earthquakes.add(new Earthquake(2.3f, getString(R.string.paris), 2018, 3, 29));
        earthquakes.add(new Earthquake(6.3f, getString(R.string.bucharest),2018, 3, 29));


        // Find a reference to the {@link ListView} in the layout
        ListView earthquakeListView = findViewById(R.id.list);

        // Create a new {@link ArrayAdapter} of earthquakes
        EarthquakeAdapter adapter = new EarthquakeAdapter(getApplicationContext(), earthquakes);

        // Set the adapter on the {@link ListView}
        // so the list can be populated in the user interface
        earthquakeListView.setAdapter(adapter);
    }
}
