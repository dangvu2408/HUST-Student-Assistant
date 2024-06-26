package com.example.app_01.UtilsPack;

import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;

import com.example.app_01.R;

import java.io.IOException;
import java.util.Calendar;

public class Utils {
    private static Utils instance;
    private Dialog dialogLoading;
    public static Utils getInstance() {
        if (instance == null) {
            instance = new Utils();
        }
        return instance;
    }

    public boolean isOnline() {
        try {
            if (Runtime.getRuntime().exec("/system/bin/ping -c 1 8.8.8.8").waitFor() == 0) {
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
        int i, j;
        j = Calendar.getInstance().get(3);
        try {
            int m = Integer.parseInt(getInstance().getValueFromSharedPreferences(context, "share_preferences_data", "key_share_preferenceds_data_tuan_hoc_hien_tai"));
            int n = Integer.parseInt(getInstance().getValueFromSharedPreferences(context, "share_preferences_data", "key_share_preferenceds_data_week_of_year"));
            if (j >= n) {
                i = (j - n) + m;
            } else {
                i = (52 - n) + m + j;
            }
        } catch (Exception e) {
            i = 0;
        }
        return String.valueOf(i);
    }

    public void showLoadingDialog(Context context) {
        Dialog dialog = new Dialog(context);
        this.dialogLoading = dialog;
        dialog.requestWindowFeature(1);
        dialog.getWindow().setBackgroundDrawableResource(R.drawable.dialog_loading_background);
        this.dialogLoading.setContentView(R.layout.loading_dialog);
        this.dialogLoading.setCancelable(false);
        this.dialogLoading.show();
    }

    public void hideLoadingDialog(Context context) {
        Dialog dialog = this.dialogLoading;
        if (dialog != null && dialog.isShowing()) {
            this.dialogLoading.dismiss();
            this.dialogLoading = null;
        }
    }
}
