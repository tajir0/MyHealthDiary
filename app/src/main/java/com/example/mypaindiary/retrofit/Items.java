package com.example.mypaindiary.retrofit;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Items {
    @SerializedName("city")
    @Expose
    private String city;
    @SerializedName("cityid")
    @Expose
    private String cityid;
    @SerializedName("temp")
    @Expose
    private String temp;
    @SerializedName("WD")
    @Expose
    private String wd;
    @SerializedName("WS")
    @Expose
    private String ws;
    @SerializedName("SD")
    @Expose
    private String sd;
    @SerializedName("AP")
    @Expose
    private String ap;
    @SerializedName("njd")
    @Expose
    private String njd;
    @SerializedName("WSE")
    @Expose
    private String wse;
    @SerializedName("time")
    @Expose
    private String time;
    @SerializedName("sm")
    @Expose
    private String sm;
    @SerializedName("isRadar")
    @Expose
    private String isRadar;
    @SerializedName("Radar")
    @Expose
    private String radar;
    public String getTemp() {
        return temp;
    }
    public void setTemp(String temp) {
        this.temp = temp;
    }
    public String getSd() {
        return sd;
    }

    public void setSd(String sd) {
        this.sd = sd;
    }

    public String getAp() {
        return ap;
    }

    public void setAp(String ap) {
        this.ap = ap;
    }
}