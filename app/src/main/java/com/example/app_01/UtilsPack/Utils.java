package com.example.app_01.UtilsPack;

import android.content.Context;
import android.content.SharedPreferences;

import java.io.IOException;

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

}
