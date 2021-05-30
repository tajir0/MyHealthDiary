package com.example.mypaindiary.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.mypaindiary.databinding.MapBinding;
import com.example.mypaindiary.databinding.ReportBinding;
import com.example.mypaindiary.entity.LocationCount;
import com.example.mypaindiary.entity.PainRecord;
import com.example.mypaindiary.utils.PieChartManagger;
import com.example.mypaindiary.viewmodel.PainRecordViewModel;
import com.example.mypaindiary.viewmodel.SharedViewModel;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieEntry;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;
import java.util.concurrent.CompletableFuture;

public class Report extends Fragment {
    private PieChart p1,p2;
    private ReportBinding binding;
    private PainRecordViewModel model;
    private float stepsTaken,stepsRemaining,back,neck, head, knees, hips, abdomen, elbows, shoulders, shins, jaw, facial;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = ReportBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        initView();
        return view;
    }
    private void initView() {
        p1 = (PieChart) binding.pieChat1;
        p2 = (PieChart) binding.pieChat2;
        //initData();
        showWholePieChart();
        showRingPieChart();
    }


    private void showWholePieChart() {
        // 设置每份所占数量
        List<PieEntry> yvals = new ArrayList<>();
        //设置每份的颜色
        List<Integer> colors = new ArrayList<>();
        model = ViewModelProvider.AndroidViewModelFactory.getInstance(getActivity().getApplication()).create(PainRecordViewModel.class);
        model.countLocation().observe(getViewLifecycleOwner(), new Observer<List<LocationCount>>() {
            @Override
            public void onChanged(@Nullable List<LocationCount> locationCounts) {
               for (LocationCount tmp:locationCounts) {
                   if (tmp.location.equals("Back"))  back = tmp.count;
                   else if (tmp.location.equals("Facial")) facial = (float) tmp.count.intValue();
                   else if (tmp.location.equals("Neck")) neck = (float) tmp.count.intValue();
                   else if (tmp.location.equals("Head")) head = (float) tmp.count.intValue();
                   else if (tmp.location.equals("Knees")) knees = (float) tmp.count.intValue();
                   else if (tmp.location.equals("Hips")) hips = (float) tmp.count.intValue();
                   else if (tmp.location.equals("Abdomen")) abdomen = (float) tmp.count.intValue();
                   else if (tmp.location.equals("Elbows")) elbows = (float) tmp.count.intValue();
                   else if (tmp.location.equals("Shoulders")) shoulders = (float) tmp.count.intValue();
                   else if (tmp.location.equals("Shins")) shins = (float) tmp.count.intValue();
                   else if (tmp.location.equals("Jaw")) jaw = (float) tmp.count.intValue();
                   //binding.text.setText(String.valueOf(back)); //测试
                   //binding.text.setText(String.valueOf(tmp.count)); //测试
                  // binding.text.setText(tmp.location); //测试
               }

                yvals.add(new PieEntry(back, "back"));
                yvals.add(new PieEntry(neck, "neck"));
                yvals.add(new PieEntry(head, "head"));
                yvals.add(new PieEntry(knees, "knees"));
                yvals.add(new PieEntry(hips, "hips"));
                yvals.add(new PieEntry(abdomen, "abdomen"));
                yvals.add(new PieEntry(elbows, "elbows"));
                yvals.add(new PieEntry(shoulders, "shoulders"));
                yvals.add(new PieEntry(shins, "shins"));
                yvals.add(new PieEntry(jaw, "jaw"));
                yvals.add(new PieEntry(facial, "facial"));

                colors.add(Color.parseColor("#6785f2"));
                colors.add(Color.parseColor("#675cf2"));
                colors.add(Color.parseColor("#496cef"));
                colors.add(Color.parseColor("#aa63fa"));
                colors.add(Color.parseColor("#58a9f5"));
                colors.add(Color.parseColor("#f5a658"));
                colors.add(Color.parseColor("#f5a658"));
                colors.add(Color.parseColor("#f5a658"));
                colors.add(Color.parseColor("#f5a658"));
                colors.add(Color.parseColor("#f5a658"));
                colors.add(Color.parseColor("#f5a658"));


                PieChartManagger pieChartManagger=new PieChartManagger(p1);
                pieChartManagger.showWholePieChart(yvals,colors);
            }
        });


    }
    private void showRingPieChart() {
//设置每份所占数量
        List<PieEntry> yvals = new ArrayList<>();
        // 设置每份的颜色
        List<Integer> colors = new ArrayList<>();

        /*获取当前系统时间*/
        long time = System.currentTimeMillis();
        /*时间戳转换成IOS8601字符串*/
        Date date = new Date(time);
        TimeZone tz = TimeZone.getTimeZone("gmt");
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        df.setTimeZone(tz);
        String curDate = df.format(date);

        SharedViewModel targetModel = new
                ViewModelProvider(requireActivity()).get(SharedViewModel.class);
        final int[] goal = {10000};
        targetModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {

                goal[0] = Integer.parseInt(s);
            }
        });


        model = ViewModelProvider.AndroidViewModelFactory.getInstance(getActivity().getApplication()).create(PainRecordViewModel.class);
        model.findByDate(curDate).observe(getViewLifecycleOwner(), new Observer<PainRecord>() {
            @Override
            public void onChanged(@Nullable PainRecord painRecord) {

                yvals.add(new PieEntry(Integer.parseInt(painRecord.stepsTaken), "Current Steps Taken"));
                colors.add(Color.parseColor("#6785f2"));
                //stepTaken[0] = Integer.parseInt(painRecord.stepsTaken);

                yvals.add(new PieEntry(goal[0] - Integer.parseInt(painRecord.stepsTaken), "Remaining Steps"));
                colors.add(Color.parseColor("#675cf2"));
                PieChartManagger pieChartManagger = new PieChartManagger(p2);
                pieChartManagger.showRingPieChart(yvals, colors);
            }
        });



    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
