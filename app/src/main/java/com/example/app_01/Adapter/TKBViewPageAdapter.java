package com.example.app_01.Adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.app_01.Fragment.Lichthi_Fragment;
import com.example.app_01.Fragment.TKB_Fragment;

public class TKBViewPageAdapter extends FragmentStatePagerAdapter {
    public TKBViewPageAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }
    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new TKB_Fragment();
            case 1:
                return new Lichthi_Fragment();
            default:
                return new TKB_Fragment();
        }
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        String title = "";
        switch (position) {
            case 0:
                title = "Thời khóa biểu";
                break;
            case 1:
                title = "Lịch thi";
                break;
        }
        return title;
    }
}
