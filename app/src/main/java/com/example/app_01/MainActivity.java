package com.example.app_01;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.app_01.databinding.ActivityMainBinding;


public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());





        repalceFragment(new Home_Fragment());

        binding.navigation.setOnItemSelectedListener(item -> {
            int itemId = item.getItemId();
            if (itemId == R.id.item1) {
                repalceFragment(new Home_Fragment());
            } else if (itemId == R.id.item2) {
                repalceFragment(new Service_Fragment());
            } else if (itemId == R.id.item3) {
                repalceFragment(new Notification_Fragment());
            } else if (itemId == R.id.item4) {
                repalceFragment(new Profile_Fragment());
            }
            return true;
        });
    }
    private void repalceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction trans = fragmentManager.beginTransaction();
        trans.replace(R.id.frame_layout, fragment);
        trans.commit();
    }

    private void makeStatusBarTransparent() {

    }

}