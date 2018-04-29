package com.example.android.quakereport;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

/**
 * Created by ccojo on 4/29/2018.
 */

public class EarthquakeAdapter extends ArrayAdapter<Earthquake> {

    private static final String E_DD_MMM_YYYY = "E DD, MMM, yyyy";
    private static final String HH_MM_SS_A = "HH:mm:ss a";
    public static final String OF = " of ";

    EarthquakeAdapter(@NonNull Context context, ArrayList<Earthquake> earthquakes) {
        super(context, 0, earthquakes);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        Earthquake currentEarthquake = getItem(position);

        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.earthquake_list_item, parent, false);
        }

        TextView magTV = convertView.findViewById(R.id.mag);
        TextView locationTV = convertView.findViewById(R.id.location);
        TextView proximityTV = convertView.findViewById(R.id.proximity);
        TextView dateTV = convertView.findViewById(R.id.date);
        TextView timeTV = convertView.findViewById(R.id.time);

        //set magnitude
        setMag(magTV, currentEarthquake.getmMag());

        //set location and location offset
        String location = currentEarthquake.getmLocation();

        if(location.contains(OF)){
            String prox = (location.split(OF))[0] + OF;
            String loc = (location.split(OF))[1];

            locationTV.setText(loc);
            proximityTV.setText(prox);
        } else {
            locationTV.setText(location);
            proximityTV.setText(R.string.near_the);
        }

        //set date and time
        long currentEarthquakeDateTime = currentEarthquake.getmTimeInMilliseconds();
        Date dt = new Date(currentEarthquakeDateTime);
        setTime(timeTV, dt);
        setDate(dateTV, dt);

        return convertView;
    }

    public void setDate(TextView view, Date dt){
        SimpleDateFormat formatter = new SimpleDateFormat(E_DD_MMM_YYYY, Locale.getDefault());
        String date = formatter.format(dt);
        view.setText(date);
    }

    public void setTime(TextView view, Date dt){
        SimpleDateFormat formatter = new SimpleDateFormat(HH_MM_SS_A, Locale.getDefault());
        String time = formatter.format(dt);
        view.setText(time);
    }

    public void setMag(TextView view, double mag){
        DecimalFormat formatter = new DecimalFormat("0.0");
        String magText = formatter.format(mag);
        view.setText(magText);
    }
}
