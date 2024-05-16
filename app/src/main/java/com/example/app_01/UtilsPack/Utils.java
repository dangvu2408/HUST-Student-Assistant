package com.example.app_01.UtilsPack;

import android.content.Context;
import android.content.SharedPreferences;

import java.io.IOException;
import java.util.Calendar;

public class Utils {
    private static Utils instance;

    public static Utils getInstance() {
        if (instance == null) {
            instance = new Utils();
        }
        return instance;
    }

    public boolean isOnline() {
        try {
            if(Runtime.getRuntime().exec("/system/bin/ping -c 1 8.8.8.8").waitFor() == 0) {
                return true;
            }
            return false;
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            return false;
        }
    }


    public void saveToSharedPreferences(Context context, String str, String str2, String str3) {
        SharedPreferences.Editor edit = context.getSharedPreferences(str, 0).edit();
        edit.putString(str2, str3);
        edit.apply();
    }

    public String getValueFromSharedPreferences(Context context, String str, String str2) {
        return context.getSharedPreferences(str, 0).getString(str2, "");
    }

    public String getWeek(Context context) {
        int i;
        try {
            i = Integer.parseInt(getInstance().getValueFromSharedPreferences(context, "share_preferences_data", "key_share_preferenceds_data_tuan_hoc_hien_tai"))
                    + (Calendar.getInstance().get(3) - Integer.parseInt(getInstance().getValueFromSharedPreferences(context, "share_preferences_data", "key_share_preferenceds_data_week_of_year")));
        } catch (Exception e) {
            i = 0;
            e.printStackTrace();
        }
        return String.valueOf(i);
    }
}
