package com.example.android.quakereport;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by ccojo on 4/29/2018.
 */

public class Earthquake {
    private float mMag;
    private String mLocation;
    private Date mDate;
    private Calendar cal;

    public Earthquake(float mMag, String mLocation, int year, int month, int day) {
        this.mMag = mMag;
        this.mLocation = mLocation;
        cal = Calendar.getInstance();
        cal.set(year, month, day);
        this.mDate = cal.getTime();
    }

    public float getmMag() {
        return mMag;
    }

    public String getmLocation() {
        return mLocation;
    }

    public Date getmDate() {
        return mDate;
    }
}
