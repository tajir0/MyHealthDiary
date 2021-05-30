package com.example.mypaindiary.model;

import androidx.annotation.NonNull;

import com.example.mypaindiary.entity.PainRecord;

import java.util.ArrayList;
import java.util.List;

public class PainRecordUnit {

    private String mRid,mPainIntensity,mPainLocation,mMoodLevel,mStepsTaken,mDataEntry,mTemp,mHumid,mPressure;

    public PainRecordUnit(String rid, String painIntensity, String painLocation, String moodLevel,
                          String stepsTaken, String dateEntry, String temp, String humid, String pressure) {
        mRid = rid;
        mPainIntensity = painIntensity;
        mPainLocation = painLocation;
        mMoodLevel = moodLevel;
        mStepsTaken = stepsTaken;
        mDataEntry = dateEntry;
        mTemp = temp;
        mHumid = humid;
        mPressure = pressure;
    }
    public String getRid() {
        return mRid;
    }
    public String getPainIntensity() {
        return mPainIntensity;
    }
    public String getPainLocation() {
        return mPainLocation;
    }
    public String getMoodLevel() {
        return mMoodLevel;
    }
    public String getStepsTaken() {
        return mStepsTaken;
    }
    public String getDataEntry() {
        return mDataEntry;
    }
    public String getTemp() {return mTemp;}
    public String getHumid() {return mHumid;}
    public String getPressure() {return  mPressure;}
    //this is used to populate the list with a few items at the start of the application
//it is static so it can be called without instantiating the class

    public static List<PainRecordUnit> createContactsList() {
        List<PainRecordUnit> units = new ArrayList<PainRecordUnit>();
        units.add(new PainRecordUnit( "","","","","","", "","",""));
        return units;
    }

}