package com.example.mypaindiary.repository;


import android.app.Application;
import android.os.Build;

import androidx.annotation.RequiresApi;
import androidx.lifecycle.LiveData;

import com.example.mypaindiary.dao.PainRecordDAO;
import com.example.mypaindiary.database.PainRecordDatabase;
import com.example.mypaindiary.entity.LocationCount;
import com.example.mypaindiary.entity.PainRecord;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Supplier;

public class PainRecordRepository {
    private PainRecordDAO painRecordDao;
    private LiveData<List<PainRecord>> allPainRecords;
    public PainRecordRepository(Application application){
        PainRecordDatabase db = PainRecordDatabase.getInstance(application);
        painRecordDao =db.painRecordDao();
        allPainRecords = painRecordDao.getAll();
    }
    // Room executes this query on a separate thread
    public LiveData<List<PainRecord>> getAllCustomers() {
        return allPainRecords;
    }
    public  void insert(final PainRecord painRecord){
        PainRecordDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                painRecordDao.insert(painRecord);
            }
        });
    }
    public void updatePainRecord(final PainRecord painRecord){
        PainRecordDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                painRecordDao.updatePainRecord(painRecord);
            }
        });
    }
    public void deleteAll(){
        PainRecordDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                painRecordDao.deleteAll();
            }
        });
    }
    public LiveData<List<LocationCount>> countLocation() {
        return painRecordDao.countLocation();
    }
    public LiveData<PainRecord> findByDate(String date) {
        return painRecordDao.findByDate(date);
    }
    @RequiresApi(api = Build.VERSION_CODES.N)
    public CompletableFuture<PainRecord> findByIDFuture(final String rId) {
        return CompletableFuture.supplyAsync(new Supplier<PainRecord>() {
            @Override
            public PainRecord get() {
                return painRecordDao.findByID(rId);
            }
        }, PainRecordDatabase.databaseWriteExecutor);
    }
}