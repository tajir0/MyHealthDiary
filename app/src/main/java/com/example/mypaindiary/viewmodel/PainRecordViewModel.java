package com.example.mypaindiary.viewmodel;

import android.app.Application;
import android.os.Build;

import androidx.annotation.RequiresApi;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.mypaindiary.entity.LocationCount;
import com.example.mypaindiary.entity.PainRecord;
import com.example.mypaindiary.repository.PainRecordRepository;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class PainRecordViewModel extends AndroidViewModel {
    private PainRecordRepository rRepository;
    private LiveData<List<PainRecord>> allPainRecords;
    public PainRecordViewModel (Application application) {
        super(application);
        rRepository = new PainRecordRepository(application);
        allPainRecords = rRepository.getAllCustomers();
    }
    @RequiresApi(api = Build.VERSION_CODES.N)
    public CompletableFuture<PainRecord> findByIDFuture(final String rId){
        return rRepository.findByIDFuture(rId);
    }
    /*
    @RequiresApi(api = Build.VERSION_CODES.N)
    public CompletableFuture<Integer> countLocation(final String location){
        return rRepository.countLocation(location);
    }

     */
    public LiveData<List<PainRecord>> getAllPainRecords() {
        return allPainRecords;
    }

    public void insert(PainRecord painRecord) {
        rRepository.insert(painRecord);
    }

    public void update(PainRecord painRecord) {
        rRepository.updatePainRecord(painRecord);
    }
    public void deleteAll() { rRepository.deleteAll(); }

    public LiveData<List<LocationCount>> countLocation() {
        return rRepository.countLocation();
    }
    public LiveData<PainRecord> findByDate(String date) {
        return rRepository.findByDate(date);
    }

}
