package com.example.android.quakereport;

import java.util.Date;

/**
 * Created by ccojo on 4/29/2018.
 */

public class Earthquake {
    private double mMag;
    private String mLocation;
    private Date mDate;
    //private Calendar cal;

    public Earthquake(double mMag, String mLocation, Date mDate) {
        this.mMag = mMag;
        this.mLocation = mLocation;
        this.mDate = mDate;
//        cal = Calendar.getInstance();
//        cal.set(year, month, day);
//        this.mDate = cal.getTime();
    }

    public double getmMag() {
        return mMag;
    }

    public String getmLocation() {
        return mLocation;
    }

    public Date getmDate() {
        return mDate;
    }
}
