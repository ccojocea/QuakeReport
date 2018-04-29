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
        TextView date = convertView.findViewById(R.id.date);
        TextView location = convertView.findViewById(R.id.location);

        mag.setText(String.valueOf(currentEarthquake.getmMag()));
        setDate(date, currentEarthquake.getmDate());
        location.setText(currentEarthquake.getmLocation());

        return convertView;
    }

    public void setDate(TextView view, Date dt){
        SimpleDateFormat formatter = new SimpleDateFormat("E DD, MMM, yyyy \nHH:mm:ss a");
        String date = formatter.format(dt);
        view.setText(date);
    }
}
