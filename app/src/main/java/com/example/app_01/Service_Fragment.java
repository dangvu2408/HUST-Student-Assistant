package com.example.app_01;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;

import java.util.ArrayList;


public class Service_Fragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.service_fragment, container, false);

        BarChart barChart = view.findViewById(R.id.bar_chart_display);
        LineChart lineChart = view.findViewById(R.id.line_chart_display);
        //entry for bar chart
        ArrayList<BarEntry> NoOfEmp = new ArrayList<>();
        NoOfEmp.add(new BarEntry(0f, 3.57f));
        NoOfEmp.add(new BarEntry(1f, 3.63f));
        NoOfEmp.add(new BarEntry(2f, 3.55f));
        NoOfEmp.add(new BarEntry(3f, 3.42f));
        NoOfEmp.add(new BarEntry(4f, 3.54f));
        //entry for line chart
        ArrayList<Entry> GPA = new ArrayList<>();
        GPA.add(new Entry(0f, 3.57f));
        GPA.add(new Entry(1f, 3.63f));
        GPA.add(new Entry(2f, 3.55f));
        GPA.add(new Entry(3f, 3.42f));
        GPA.add(new Entry(4f, 3.54f));

        ArrayList<String> semester = new ArrayList<>();
        semester.add("20221");
        semester.add("20222");
        semester.add("20231");
        semester.add("20232");
        semester.add("20241");

        //bar chart data set
        BarDataSet barDataSet = new BarDataSet(NoOfEmp, "");
        Description description = new Description();
        description.setText("");
        barChart.setDescription(description);
        barChart.getXAxis().setValueFormatter(new IndexAxisValueFormatter(semester));
        barChart.getXAxis().setPosition(XAxis.XAxisPosition.BOTTOM);
        barChart.getXAxis().setDrawGridLines(false);
        barChart.getAxisRight().setEnabled(false);
        barChart.getXAxis().setLabelCount(semester.size());
        barChart.getXAxis().setGranularity(1f);
        barChart.getAxisLeft().setAxisMinimum(2f);
        barChart.getAxisLeft().setAxisMaximum(4f);
        barChart.animateY(1000);
        barChart.setFitBars(false);
        BarData data = new BarData(barDataSet);
        data.setBarWidth(0.5f);
        barDataSet.setColors(Color.rgb(0, 155, 0));
        barChart.setData(data);

        //line chart data set
        LineDataSet lineDataSet = new LineDataSet(GPA, "");
        LineData lineData = new LineData(lineDataSet);
        lineChart.setDescription(description);
        lineDataSet.setColor(Color.RED);
        lineDataSet.setCircleColor(Color.BLUE);
        lineDataSet.setCircleRadius(4);
        lineDataSet.setCircleHoleRadius(2);
        lineChart.setData(lineData);
        lineChart.getXAxis().setValueFormatter(new IndexAxisValueFormatter(semester));
        lineChart.getXAxis().setPosition(XAxis.XAxisPosition.BOTTOM);
        lineChart.getAxisRight().setEnabled(false);
        lineChart.getAxisLeft().setAxisMinimum(2f);
        lineChart.getAxisLeft().setAxisMaximum(4f);
        lineDataSet.setDrawValues(true);
        lineChart.getXAxis().setAxisMinimum(-0.3f);
        lineChart.getXAxis().setAxisMaximum(4.3f);
        lineChart.getXAxis().setLabelCount(semester.size());
        lineChart.getXAxis().setGranularity(1f);
        lineChart.animateX(1000);

        return view;
    }
}