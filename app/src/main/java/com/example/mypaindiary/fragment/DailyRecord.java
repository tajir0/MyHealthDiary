package com.example.mypaindiary.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mypaindiary.adapter.RecyclerViewAdapter;
import com.example.mypaindiary.databinding.DailyRecordBinding;
import com.example.mypaindiary.entity.PainRecord;
import com.example.mypaindiary.model.PainRecordUnit;
import com.example.mypaindiary.viewmodel.PainRecordViewModel;

import java.util.ArrayList;
import java.util.List;

public class DailyRecord extends Fragment {
    private RecyclerView.LayoutManager layoutManager;
    private List<PainRecordUnit> units;
    private RecyclerViewAdapter adapter;
    private DailyRecordBinding binding;
    private PainRecordViewModel model;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DailyRecordBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        units=new ArrayList<PainRecordUnit>();
        //units = PainRecordUnit.createContactsList(); //每点一次DailyRecord都会创建一条空记录
        adapter = new RecyclerViewAdapter(units);

        model = ViewModelProvider.AndroidViewModelFactory.getInstance(getActivity().getApplication()).create(PainRecordViewModel.class);
        model.getAllPainRecords().observe(getViewLifecycleOwner(), new
                Observer<List<PainRecord>>() {
                    @Override
                    public void onChanged(@Nullable final List<PainRecord> painRecords) {
                        for (PainRecord temp : painRecords) {
                            saveData(temp.rid,temp.painIntensity,temp.painLocation,temp.moodLevel,temp.stepsTaken,temp.dateEntry,"","","");
                        }
                    }
                });
        //this just creates a line divider between rows
        binding.recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), LinearLayoutManager.VERTICAL));
        binding.recyclerView.setAdapter(adapter);
        layoutManager = new LinearLayoutManager(getActivity());
        binding.recyclerView.setLayoutManager(layoutManager);
        return view;
    }
    private void saveData(String rid,String painIntensity, String painLocation, String moodLevel, String stepsTaken, String dataEntry, String temp, String humid, String pressure) {
        PainRecordUnit painRecordUnit = new PainRecordUnit(rid, painIntensity, painLocation, moodLevel, stepsTaken, dataEntry, temp, humid, pressure);
        units.add(painRecordUnit);
        adapter.addUnits(units);
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
