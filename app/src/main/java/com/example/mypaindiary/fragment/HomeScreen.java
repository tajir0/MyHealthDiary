package com.example.mypaindiary.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.mypaindiary.databinding.HomeScreenBinding;
import com.example.mypaindiary.retrofit.Items;
import com.example.mypaindiary.retrofit.RetrofitClient;
import com.example.mypaindiary.retrofit.RetrofitInterface;
import com.example.mypaindiary.retrofit.SearchResponse;
import com.example.mypaindiary.viewmodel.SharedViewModel;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeScreen extends Fragment {
    private static final String API_KEY = "AIzaSyDy6NkP1QALklqKGiYpeHFziDMUSSPD0j0";
    private static final String SEARCH_ID_cx = "3ce74a8777942240f";

    private RetrofitInterface retrofitInterface;
    private SharedViewModel model;
    private HomeScreenBinding addBinding;
    public HomeScreen(){}
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the View for this fragment
        addBinding = HomeScreenBinding.inflate(inflater, container, false);
        View view = addBinding.getRoot();

        /*获取当前系统时间*/
        long time = System.currentTimeMillis();
        /*时间戳转换成IOS8601字符串*/
        Date date = new Date(time);
        TimeZone tz = TimeZone.getTimeZone("gmt");
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        df.setTimeZone(tz);
        String curDate = df.format(date);
        addBinding.date.setText(curDate);


        retrofitInterface = RetrofitClient.getRetrofitService();
        Call<SearchResponse> callAsync =
                retrofitInterface.customSearch(API_KEY,SEARCH_ID_cx, "Tokyo");
        //makes an async request & invokes callback methods when the response returns
        callAsync.enqueue(new Callback<SearchResponse>() {
            @Override
            public void onResponse(Call<SearchResponse> call,
                                   Response<SearchResponse> response) {
                if (response.isSuccessful()) {
                    List<Items> list = response.body().items;
                    String temp= list.get(0).getTemp();
                    String humid = list.get(0).getSd();
                    String pres = list.get(0).getAp();
                    addBinding.temp.setText(temp + "℃");
                    addBinding.humidity.setText(humid);
                    addBinding.pressure.setText(pres);
                }
                else {
                    Log.i("Error ","Response failed");
                }
            }
            @Override
            public void onFailure(Call<SearchResponse> call, Throwable t){
                Toast.makeText(getActivity(), t.getMessage(), Toast.LENGTH_SHORT);
            }
        });
        //addBinding.temp.setText("23.9" + "℃");
        //addBinding.humidity.setText("79%");
        //addBinding.pressure.setText("1004.9hPa");

        return view;
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        addBinding = null;
    }


}