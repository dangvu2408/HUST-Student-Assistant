package com.example.app_01.AppCompat;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RelativeLayout;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.example.app_01.Adapter.TKBViewPageAdapter;
import com.example.app_01.R;
import com.example.app_01.UtilsPack.OnSwipeTouchListener;
import com.google.android.material.tabs.TabLayout;

public class TKBActivity extends AppCompatActivity {

    private RelativeLayout main_layout;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.timetable_activity);
        TabLayout tabLayout = findViewById(R.id.tabLayoutTKB);
        ViewPager viewPager = findViewById(R.id.viewPagerTKB);
        TKBViewPageAdapter tkbViewPageAdapter = new TKBViewPageAdapter(getSupportFragmentManager());
        viewPager.setAdapter(tkbViewPageAdapter);
        tabLayout.setupWithViewPager(viewPager);

        ImageButton btnBack = findViewById(R.id.TKBbtnBack);
        btnBack.setOnClickListener(new View.OnClickListener() {
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
