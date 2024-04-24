package com.example.app_01;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;


public class Service_Fragment extends Fragment {
    Button btnBarChart, btnLineChart;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.service_fragment, container, false);

        btnBarChart = view.findViewById(R.id.bar_chart_btn);
        btnLineChart = view.findViewById(R.id.line_chart_btn);





        return view;
    }
}