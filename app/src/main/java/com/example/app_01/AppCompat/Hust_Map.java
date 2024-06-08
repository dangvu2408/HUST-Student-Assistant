package com.example.app_01.AppCompat;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.app_01.R;
import com.example.app_01.UtilsPack.OnSwipeTouchListener;

public class Hust_Map extends AppCompatActivity {
    private ConstraintLayout main_layout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hust_map_activity);

        ImageButton returnbtn = findViewById(R.id.return_map);
        returnbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        main_layout = findViewById(R.id.main_layout);
        main_layout.setOnTouchListener(new OnSwipeTouchListener(this) {
            @Override
            public void onSwipeRight() {
                finish();
            }
        });
    }
}