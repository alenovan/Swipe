package com.onetech.novan.swipfresh.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class SharedPref {
    private Context context;
    private SharedPreferences sharedPreferences;
    private SharedPreferences prefs;

    public static final String LOGGED_IN        = "logged_in";
    public static final String LOGGED_ID        = "logged_id";
    public static final String LOGGED_NAME      = "logged_name";
    public static final String LOGGED_EMAIL     = "logged_email";
    public static final String LOGGED_PIC_WEB   = "logged_pic_web";
    public static final String LOGGED_PIC_APP   = "logged_pic_app";
    public static final String LOGGED_GPS_LAT   = "logged_gps_lat";
    public static final String LOGGED_GPS_LONG  = "logged_gps_long";
    public static final String LOGGED_IDENTITY  = "logged_identity";
    public static final String LOGGED_PHONE     = "logged_phone";
    public static final String LOGGED_VERIFIED  = "logged_verified";
    public static final String LOGGED_CITY_ID   = "logged_city_id";
    public static final String LOGGED_CITY_NAME = "logged_city_name";

    public static final String LOGGED_TOKEN             = "logged_token";
    public static final String LOGGED_TOKEN_REFRESH     = "logged_token_refresh";
    public static final String LOGGED_EXPIRE            = "logged_expire";

    public SharedPref(Context context) {
        this.context = context;
        sharedPreferences = context.getSharedPreferences("MAIN_PREF", Context.MODE_PRIVATE);
        prefs = PreferenceManager.getDefaultSharedPreferences(context);
    }

    public boolean isLogged(){
        return sharedPreferences.getBoolean(LOGGED_IN, false);
    }

    public boolean isVerified() {
        if ( sharedPreferences.getInt(LOGGED_VERIFIED, 0) >= 1 ) {
            return true;
        }
        return false;
    }

    public void setLoggedIn(Boolean b) {
        sharedPreferences.edit().putBoolean(LOGGED_IN, b).apply();
    }

    public void setLogout() {
        Boolean b = false;
        Integer i = 0;
        String s = "";
        sharedPreferences.edit().putBoolean(LOGGED_IN, b).apply();
        sharedPreferences.edit().putInt(LOGGED_ID, i).apply();
        sharedPreferences.edit().putString(LOGGED_NAME, s).apply();
        sharedPreferences.edit().putString(LOGGED_EMAIL, s).apply();
        sharedPreferences.edit().putString(LOGGED_PIC_WEB, s).apply();
        sharedPreferences.edit().putString(LOGGED_PIC_APP, s).apply();
        sharedPreferences.edit().putString(LOGGED_GPS_LAT, s).apply();
        sharedPreferences.edit().putString(LOGGED_GPS_LONG, s).apply();
        sharedPreferences.edit().putString(LOGGED_IDENTITY, s).apply();
        sharedPreferences.edit().putString(LOGGED_PHONE, s).apply();
        sharedPreferences.edit().putInt(LOGGED_VERIFIED, i).apply();
        sharedPreferences.edit().putInt(LOGGED_CITY_ID, i).apply();
        sharedPreferences.edit().putString(LOGGED_CITY_NAME, s).apply();
        sharedPreferences.edit().putString(LOGGED_TOKEN, s).apply();
        sharedPreferences.edit().putString(LOGGED_TOKEN_REFRESH, s).apply();
        sharedPreferences.edit().putInt(LOGGED_EXPIRE, i).apply();
    }

    public void setLoggedId(Integer i) {
        sharedPreferences.edit().putInt(LOGGED_ID, i).apply();
    }

    public void setLoggedName(String s) {
        sharedPreferences.edit().putString(LOGGED_NAME, s).apply();
    }

    public void setLoggedEmail(String s) {
        sharedPreferences.edit().putString(LOGGED_EMAIL, s).apply();
    }

    public void setLoggedPicWeb(String s) {
        sharedPreferences.edit().putString(LOGGED_PIC_WEB, s).apply();
    }

    public void setLoggedPicApp(String s) {
        sharedPreferences.edit().putString(LOGGED_PIC_APP, s).apply();
    }

    public void setLoggedGpsLat(String s) {
        sharedPreferences.edit().putString(LOGGED_GPS_LAT, s).apply();
    }

    public void setLoggedGpsLong(String s) {
        sharedPreferences.edit().putString(LOGGED_GPS_LONG, s).apply();
    }

    public void setLoggedIdentity(String s) {
        sharedPreferences.edit().putString(LOGGED_IDENTITY, s).apply();
    }

    public void setLoggedPhone(String s) {
        sharedPreferences.edit().putString(LOGGED_PHONE, s).apply();
    }

    public void setLoggedVerified(Integer i) {
        sharedPreferences.edit().putInt(LOGGED_VERIFIED, i).apply();
    }

    public void setLoggedCityId(Integer i) {
        sharedPreferences.edit().putInt(LOGGED_CITY_ID, i).apply();
    }

    public void setLoggedCityName(String s) {
        sharedPreferences.edit().putString(LOGGED_CITY_NAME, s).apply();
    }

    public void setToken(String s){
        sharedPreferences.edit().putString(LOGGED_TOKEN, s).apply();
    }

    public void setTokenRefresh(String s){
        sharedPreferences.edit().putString(LOGGED_TOKEN_REFRESH, s).apply();
    }

    public void setTokenExpire(Integer i){
        sharedPreferences.edit().putInt(LOGGED_EXPIRE, i).apply();
    }

    public Boolean getLoggedIn() {
        return sharedPreferences.getBoolean(LOGGED_IN, false);
    }

    public Integer getLoggedId() {
        return sharedPreferences.getInt(LOGGED_ID, 0);
    }

    public String getLoggedName() {
        return sharedPreferences.getString(LOGGED_NAME, "");
    }

    public String getLoggedEmail() {
        return sharedPreferences.getString(LOGGED_EMAIL, "");
    }

    public String getLoggedPicWeb() {
        return sharedPreferences.getString(LOGGED_PIC_WEB, "");
    }

    public String getLoggedPicApp() {
        return sharedPreferences.getString(LOGGED_PIC_APP, "");
    }

    public String getLoggedGpsLat() {
        return sharedPreferences.getString(LOGGED_GPS_LAT, "");
    }

    public String getLoggedGpsLong() {
        return sharedPreferences.getString(LOGGED_GPS_LONG, "");
    }

    public String getLoggedIdentity() {
        return sharedPreferences.getString(LOGGED_IDENTITY, "");
    }

    public String getLoggedPhone() {
        return sharedPreferences.getString(LOGGED_PHONE, "");
    }

    public Integer getLoggedVerified() {
        return sharedPreferences.getInt(LOGGED_VERIFIED, 0);
    }

    public Integer getLoggedCityId() {
        return sharedPreferences.getInt(LOGGED_CITY_ID, 0);
    }

    public String getLoggedCityName() {
        return sharedPreferences.getString(LOGGED_CITY_NAME, "");
    }

    public String getToken()
    {
        return sharedPreferences.getString(LOGGED_TOKEN, "");
    }

    public String getTokenRefresh(){
        return sharedPreferences.getString(LOGGED_TOKEN_REFRESH, "");
    }

    public Integer getTokenExpire(){
        return sharedPreferences.getInt(LOGGED_EXPIRE, 0);
    }

}
