package com.example.app_01;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;

import java.util.ArrayList;


public class Service_Fragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.service_fragment, container, false);

        BarChart barChart = view.findViewById(R.id.bar_chart_display);

        ArrayList<BarEntry> NoOfEmp = new ArrayList<>();

        NoOfEmp.add(new BarEntry(0f, 3.57f));
        NoOfEmp.add(new BarEntry(1f, 3.63f));
        NoOfEmp.add(new BarEntry(2f, 3.55f));

        ArrayList<String> semester = new ArrayList<>();
        semester.add("20221");
        semester.add("20222");
        semester.add("20231");

        Description description = new Description();
        description.setText("");
        barChart.setDescription(description);
        barChart.getXAxis().setValueFormatter(new IndexAxisValueFormatter(semester));
        barChart.getXAxis().setPosition(XAxis.XAxisPosition.BOTTOM);
        barChart.getXAxis().setDrawGridLines(false);
        barChart.getAxisRight().setEnabled(false);
        barChart.getAxisLeft().setAxisMinimum(2f);
        barChart.getAxisLeft().setAxisMaximum(4f);
        BarDataSet barDataSet = new BarDataSet(NoOfEmp, "");
        barChart.animateY(2000);
        BarData data = new BarData(barDataSet);
        data.setBarWidth(0.5f);
        barDataSet.setColors(Color.rgb(0, 155, 0));
        barChart.setData(data);
        return view;
    }
}