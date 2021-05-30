package com.example.mypaindiary.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.mypaindiary.entity.LocationCount;
import com.example.mypaindiary.entity.PainRecord;

import java.util.List;

@Dao
public interface PainRecordDAO {
    @Query("SELECT * FROM painRecord")
    LiveData<List<PainRecord>> getAll();
    @Query("SELECT * FROM painRecord WHERE rid  = :recordId LIMIT 1")
    PainRecord findByID(String recordId);
    @Insert
    void insert(PainRecord painRecord);
    @Update
    void updatePainRecord(PainRecord painRecord);
    @Query("DELETE FROM painRecord")
    void deleteAll();
    @Query("SELECT pain_location, COUNT(*) AS count FROM painRecord GROUP BY pain_location" )
    LiveData<List<LocationCount>> countLocation();
    @Query("SELECT * FROM painRecord WHERE date_entry  = :date LIMIT 1")
    LiveData<PainRecord> findByDate(String date);
}
