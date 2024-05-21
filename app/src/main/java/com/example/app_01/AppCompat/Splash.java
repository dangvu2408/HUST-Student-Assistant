package com.example.app_01.AppCompat;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

import com.example.app_01.LoginPropeties.LoginActivity;
import com.example.app_01.R;
import com.example.app_01.UtilsPack.Utils;

import java.util.Timer;
import java.util.TimerTask;

public class Splash extends AppCompatActivity {
    boolean isLogin = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(Splash.this, LoginActivity.class));
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                finish();
            }
        }, 1500);
        this.isLogin = Utils.getInstance().getValueFromSharedPreferences(this, "share_preferences_data", "key_share_preferences_data_already_user_login").equals("1");
        if (Utils.getInstance().isOnline()) {
            new CheckVersion().execute(new Void[0]);
        } else {
            new Timer().schedule(new TimerTask() {
                public void run() {
                    if (isLogin) {
                        Intent intent = new Intent(Splash.this, MainActivity.class);
                        startActivity(intent);
                    } else {
                        Intent intent = new Intent(Splash.this, LoginActivity.class);
                        startActivity(intent);
                    }
                }
            }, 2000);
        }

    }

    private class CheckVersion extends AsyncTask<Void, Void, Void> {
        private CheckVersion() {}
        public Void doInBackground(Void... voidArr) {
            try {
                return null;
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }

        public void onPostExecute(Void voidR) {
            super.onPostExecute(voidR);
            new Timer().schedule(new TimerTask() {
                public void run() {
                    if (isLogin) {
                        Intent intent = new Intent(Splash.this, MainActivity.class);
                        startActivity(intent);
                    } else {
                        Intent intent = new Intent(Splash.this, LoginActivity.class);
                        startActivity(intent);
                    }
                }
            }, 1500);
        }
    }
}