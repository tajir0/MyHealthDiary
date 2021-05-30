package com.example.mypaindiary.fragment;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.lifecycle.Observer;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.mypaindiary.LoginActivity;
import com.example.mypaindiary.databinding.PainDataEntryBinding;
import com.example.mypaindiary.entity.PainRecord;
import com.example.mypaindiary.viewmodel.PainRecordViewModel;
import com.example.mypaindiary.viewmodel.SharedViewModel;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;
import java.util.concurrent.CompletableFuture;

public class PainDataEntry extends Fragment {


    private PainDataEntryBinding addBinding;
    private PainRecordViewModel model;
    String intensity, location, mood, steps, recordId, curDate;
    public PainDataEntry(){}
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        addBinding = PainDataEntryBinding.inflate(inflater, container, false);

        View view = addBinding.getRoot();
        model = ViewModelProvider.AndroidViewModelFactory.getInstance(getActivity().getApplication()).create(PainRecordViewModel.class);
        SharedViewModel targetModel = new
                ViewModelProvider(requireActivity()).get(SharedViewModel.class);
        //初始时设置成不可编辑状态
        addBinding.painIntensity.setEnabled(false);
        addBinding.painIntensity.setClickable(false);
        addBinding.painIntensity.setFocusable(false);
        addBinding.painLocation.setEnabled(false);
        addBinding.painLocation.setClickable(false);
        addBinding.painLocation.setFocusable(false);
        addBinding.veryLow.setEnabled(false);
        addBinding.veryLow.setClickable(false);
        addBinding.veryLow.setFocusable(false);
        addBinding.editGoal.setFocusableInTouchMode(false);
        addBinding.editGoal.setFocusable(false);
        addBinding.editSteps.setFocusableInTouchMode(false);
        addBinding.editSteps.setFocusable(false);
        addBinding.addSteps.setEnabled(false);
        addBinding.addSteps.setClickable(false);
        addBinding.addSteps.setFocusable(false);
        addBinding.addGoal.setEnabled(false);
        addBinding.addGoal.setClickable(false);
        addBinding.addGoal.setFocusable(false);
        Toast.makeText(getActivity(), "Can not entry before click edit.", Toast.LENGTH_LONG).show();
        addBinding.painIntensity.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                addBinding.intensityHint.setText("Current Level:" + progress + "  / 10 ");
                intensity = addBinding.intensityHint.getText().toString();
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}
        });
        addBinding.painLocation.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public  void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                location = parent.getItemAtPosition(position).toString();
            }
            @Override
            public  void onNothingSelected(AdapterView<?> parent) {}
        });

        addBinding.veryLow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addBinding.moodHint.setText("Current Mood: very low");
                mood = addBinding.moodHint.getText().toString();
            }
        });
        addBinding.low.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addBinding.moodHint.setText("Current Mood: low");
                mood = addBinding.moodHint.getText().toString();
            }
        });
        addBinding.average.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addBinding.moodHint.setText("Current Mood: average");
                mood = addBinding.moodHint.getText().toString();
            }
        });
        addBinding.good.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addBinding.moodHint.setText("Current Mood: good");
                mood= addBinding.moodHint.getText().toString();
            }
        });
        addBinding.verygood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addBinding.moodHint.setText("Current Mood: very good");
                mood = addBinding.moodHint.getText().toString();
            }
        });
        addBinding.addGoal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String message = addBinding.editGoal.getText().toString();
                addBinding.goalHint.setText("Current goal: " + message + " steps per day");
                targetModel.setMessage(message);
            }
        });
        addBinding.addSteps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                steps = addBinding.editSteps.getText().toString();
                addBinding.stepsHint.setText("Taken steps today: " + steps + " steps");
            }
        });
        addBinding.edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addBinding.painIntensity.setEnabled(true);
                addBinding.painIntensity.setClickable(true);
                addBinding.painIntensity.setFocusable(true);
                addBinding.painLocation.setEnabled(true);
                addBinding.painLocation.setClickable(true);
                addBinding.painLocation.setFocusable(true);
                addBinding.veryLow.setEnabled(true);
                addBinding.veryLow.setClickable(true);
                addBinding.veryLow.setFocusable(true);
                addBinding.editGoal.setFocusableInTouchMode(true);
                addBinding.editGoal.setFocusable(true);
                addBinding.editSteps.setFocusableInTouchMode(true);
                addBinding.editSteps.setFocusable(true);
                addBinding.addSteps.setEnabled(true);
                addBinding.addSteps.setClickable(true);
                addBinding.addSteps.setFocusable(true);
                addBinding.addGoal.setEnabled(true);
                addBinding.addGoal.setClickable(true);
                addBinding.addGoal.setFocusable(true);
            }
        });
        addBinding.save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addBinding.painIntensity.setEnabled(false);
                addBinding.painIntensity.setClickable(false);
                addBinding.painIntensity.setFocusable(false);
                addBinding.painLocation.setEnabled(false);
                addBinding.painLocation.setClickable(false);
                addBinding.painLocation.setFocusable(false);
                addBinding.veryLow.setEnabled(false);
                addBinding.veryLow.setClickable(false);
                addBinding.veryLow.setFocusable(false);
                addBinding.editGoal.setFocusableInTouchMode(false);
                addBinding.editGoal.setFocusable(false);
                addBinding.editSteps.setFocusableInTouchMode(false);
                addBinding.editSteps.setFocusable(false);
                addBinding.addSteps.setEnabled(false);
                addBinding.addSteps.setClickable(false);
                addBinding.addSteps.setFocusable(false);
                addBinding.addGoal.setEnabled(false);
                addBinding.addGoal.setClickable(false);
                addBinding.addGoal.setFocusable(false);


                //String intensity, location, mood, steps
                if (!intensity.isEmpty() && intensity!=null && !location.isEmpty() && location!=null &&
                        !mood.isEmpty() && mood!=null&& !steps.isEmpty() && steps!=null) {

                    SharedPreferences sp=getActivity().getSharedPreferences("loginInfo", Activity.MODE_PRIVATE);
                    recordId = sp.getString("loginUserName", "");
                    // recordId = "1";  //测试
                    /*获取当前系统时间*/
                    long time = System.currentTimeMillis();
                    /*时间戳转换成IOS8601字符串*/
                    Date date = new Date(time);
                    TimeZone tz = TimeZone.getTimeZone("gmt");
                    DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
                    df.setTimeZone(tz);
                    curDate = df.format(date);
                    //数据库中用户初始化
                    PainRecord painRecord1 = new PainRecord(recordId, intensity, location, mood, steps, curDate);
                    model.insert(painRecord1);
                    //model.deleteAll();  //用来清空Room存放的painrecods数据
                    Toast.makeText(getActivity(), "Update was successful.", Toast.LENGTH_LONG).show();
                } else {
                                Toast.makeText(getActivity(), "Data entry was not completed.", Toast.LENGTH_LONG).show(); }
            }
        });

        return view;
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        addBinding = null;
    }

}
