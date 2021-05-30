package com.example.mypaindiary.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class LocationCount {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "pain_location")
    public String location;
    @ColumnInfo(name = "count")
    public Integer count;
}
