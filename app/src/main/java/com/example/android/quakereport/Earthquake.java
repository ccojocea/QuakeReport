package com.example.android.quakereport;

import java.util.Date;

/**
 * Created by ccojo on 4/29/2018.
 */

public class Earthquake {
    private double mMag;
    private String mLocation;
    private long mTimeInMilliseconds;
    private String url;
    //private Calendar cal;

    public Earthquake(double mMag, String mLocation, long mTimeInMilliseconds, String url) {
        this.mMag = mMag;
        this.mLocation = mLocation;
        this.mTimeInMilliseconds = mTimeInMilliseconds;
        this.url = url;
//        cal = Calendar.getInstance();
//        cal.set(year, month, day);
//        this.mDate = cal.getTime();
    }

    public String getUrl() {
        return url;
    }

    public double getmMag() {
        return mMag;
    }

    public String getmLocation() {
        return mLocation;
    }

    public long getmTimeInMilliseconds() {
        return mTimeInMilliseconds;
    }
}
