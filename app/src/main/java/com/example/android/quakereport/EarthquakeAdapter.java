package com.example.android.quakereport;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

/**
 * Created by ccojo on 4/29/2018.
 */

public class EarthquakeAdapter extends ArrayAdapter<Earthquake> {

    public EarthquakeAdapter(@NonNull Context context, ArrayList<Earthquake> earthquakes) {
        super(context, 0, earthquakes);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        Earthquake currentEarthquake = getItem(position);

        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.earthquake_list_item, parent, false);
        }

        TextView mag = convertView.findViewById(R.id.mag);
        TextView location = convertView.findViewById(R.id.location);
        TextView date = convertView.findViewById(R.id.date);
        TextView time = convertView.findViewById(R.id.time);

        mag.setText(String.valueOf(currentEarthquake.getmMag()));
        location.setText(currentEarthquake.getmLocation());
        Date currentEarthquakeDate = currentEarthquake.getmDate();
        setTime(time, currentEarthquakeDate);
        setDate(date, currentEarthquakeDate);

        return convertView;
    }

    public void setDate(TextView view, Date dt){
        SimpleDateFormat formatter = new SimpleDateFormat("E DD, MMM, yyyy", Locale.getDefault());
        String date = formatter.format(dt);
        view.setText(date);
    }

    public void setTime(TextView view, Date dt){
        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss a", Locale.getDefault());
        String time = formatter.format(dt);
        view.setText(time);
    }
}
