package com.example.app_01.AppCompat;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.app_01.Fragment.Home_Fragment;
import com.example.app_01.Fragment.Notification_Fragment;
import com.example.app_01.Fragment.Profile_Fragment;
import com.example.app_01.Fragment.Service_Fragment;
import com.example.app_01.R;
import com.example.app_01.databinding.ActivityMainBinding;


public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    ImageButton img;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        img = findViewById(R.id.btnsetting);



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

        img.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, MainActivity2.class);
            startActivity(intent);
        });
    }
    private void repalceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction trans = fragmentManager.beginTransaction();
        trans.replace(R.id.frame_layout, fragment);
        trans.commit();
    }


}