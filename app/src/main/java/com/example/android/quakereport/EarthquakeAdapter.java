package com.example.android.quakereport;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * Created by ccojo on 4/29/2018.
 */

public class EarthquakeAdapter extends ArrayAdapter<Earthquake> {

    private static final String E_DD_MMM_YYYY = "E dd, MMM, yyyy";
    private static final String HH_MM_SS_A = "HH:mm:ss a";
    private static final String OF = " of ";
    private static final String TAG = EarthquakeAdapter.class.getSimpleName();

    //private List<Earthquake> earthquakes = new ArrayList<>();

    EarthquakeAdapter(@NonNull Context context, List<Earthquake> earthquakes) {
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
        GradientDrawable magCircle = (GradientDrawable) magTV.getBackground();
        int magColor = getMagColor(currentEarthquake.getmMag());
        magCircle.setColor(magColor);

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

    private int getMagColor(double mag){
        switch ((int) Math.floor(mag)){
            case 0:
            case 1:
                return ContextCompat.getColor(getContext(), R.color.magnitude1);
            case 2:
                return ContextCompat.getColor(getContext(), R.color.magnitude2);
            case 3:
                return ContextCompat.getColor(getContext(), R.color.magnitude3);
            case 4:
                return ContextCompat.getColor(getContext(), R.color.magnitude4);
            case 5:
                return ContextCompat.getColor(getContext(), R.color.magnitude5);
            case 6:
                return ContextCompat.getColor(getContext(), R.color.magnitude6);
            case 7:
                return ContextCompat.getColor(getContext(), R.color.magnitude7);
            case 8:
                return ContextCompat.getColor(getContext(), R.color.magnitude8);
            case 9:
                return ContextCompat.getColor(getContext(), R.color.magnitude9);
            default:
                return ContextCompat.getColor(getContext(), R.color.magnitude10plus);
        }
    }

    //TODO Marked with TODO for visibility. Overriding the following stops it from working
    /*
    public void setEarthquakes(List<Earthquake> data){
        Log.d(TAG, "setEarthquakes: ");
        if(earthquakes != null && !earthquakes.isEmpty()){
            earthquakes.addAll(data);
            notifyDataSetChanged();
        }
    }

    @Override
    public int getCount() {
        return earthquakes.size();
    }

    @Nullable
    @Override
    public Earthquake getItem(int position) {
        return earthquakes.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
    */
}
