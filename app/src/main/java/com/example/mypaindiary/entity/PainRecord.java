package com.example.mypaindiary.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class PainRecord {

    @PrimaryKey(autoGenerate = true)
    public int id;
    @ColumnInfo(name = "rid")
    @NonNull
    public String rid;
    @ColumnInfo(name = "pain_intensity")
    @NonNull
    public String painIntensity;
    @ColumnInfo(name = "pain_location")
    @NonNull
    public String painLocation;
    @ColumnInfo(name = "mood_level")
    @NonNull
    public String moodLevel;
    @ColumnInfo(name = "steps_taken")
    @NonNull
    public String stepsTaken;
    @ColumnInfo(name = "date_entry")
    @NonNull
    public String dateEntry;

    /*
    public String temp;
    @ColumnInfo(name = "temp")
    @NonNull
    public String humidity;
    @ColumnInfo(name = "humidity")
    @NonNull
    public String pressure;
    @ColumnInfo(name = "pressure")
    @NonNull
     */

    public PainRecord( @NonNull String rid, @NonNull String painIntensity, @NonNull String painLocation, @NonNull String moodLevel,
                       @NonNull String stepsTaken,  @NonNull String dateEntry) {
        this.rid = rid;
        this.painIntensity=painIntensity;
        this.painLocation=painLocation;
        this.moodLevel = moodLevel;
        this.stepsTaken=stepsTaken;
        this.dateEntry=dateEntry;
    }
}