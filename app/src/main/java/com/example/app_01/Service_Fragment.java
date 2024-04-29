package com.example.app_01;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.github.mikephil.charting.animation.Easing;
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
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class Service_Fragment extends Fragment {
    CustomAdapterScore adapter;
    ArrayList<CourseScore> arrayScore;
    ListView courseList;
    ProgressBar progressBar;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.service_fragment, container, false);

        courseList = view.findViewById(R.id.courses);
        DatabaseReference mData = FirebaseDatabase.getInstance().getReference("CourseScore");
        DatabaseReference pointData = FirebaseDatabase.getInstance().getReference("GPA_CPA");
        arrayScore = new ArrayList<>();
        progressBar = view.findViewById(R.id.progress_bar);
        progressBar.setVisibility(View.VISIBLE);


        BarChart barChart = view.findViewById(R.id.bar_chart_display);
        LineChart lineChart = view.findViewById(R.id.line_chart_display);
        //entry for bar chart
        ArrayList<BarEntry> NoOfEmp = new ArrayList<>();
        NoOfEmp.add(new BarEntry(0f, 3.57f));
        NoOfEmp.add(new BarEntry(1f, 3.63f));
        NoOfEmp.add(new BarEntry(2f, 3.55f));
        NoOfEmp.add(new BarEntry(3f, 3.42f));
        NoOfEmp.add(new BarEntry(4f, 3.54f));
        NoOfEmp.add(new BarEntry(5f, 3.72f));
        NoOfEmp.add(new BarEntry(6f, 3.80f));
        NoOfEmp.add(new BarEntry(7f, 4.00f));
        //entry for line chart
        ArrayList<Entry> GPA = new ArrayList<>();
        GPA.add(new Entry(0f, 3.57f));
        GPA.add(new Entry(1f, 3.63f));
        GPA.add(new Entry(2f, 3.55f));
        GPA.add(new Entry(3f, 3.42f));
        GPA.add(new Entry(4f, 3.54f));
        GPA.add(new Entry(5f, 3.72f));
        GPA.add(new Entry(6f, 3.80f));
        GPA.add(new Entry(7f, 4.00f));

        ArrayList<String> semester = new ArrayList<>();
        semester.add("20221");
        semester.add("20222");
        semester.add("20231");
        semester.add("20232");
        semester.add("20241");
        semester.add("20242");
        semester.add("20251");
        semester.add("20252");

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
        barChart.getAxisLeft().setAxisMaximum(4.3f);
        barChart.animateY(500);
        barChart.setFitBars(false);
        BarData data = new BarData(barDataSet);
        data.setBarWidth(0.5f);
        barDataSet.setColors(Color.rgb(0, 155, 0));
        barDataSet.setLabel("GPA");
        barChart.setData(data);

        //line chart data set
        LineDataSet lineDataSetGPA = new LineDataSet(GPA, "");

        LineData lineData = new LineData(lineDataSetGPA);


        lineDataSetGPA.setColor(Color.RED);
        lineDataSetGPA.setCircleColor(Color.BLUE);
        lineDataSetGPA.setCircleRadius(4);
        lineDataSetGPA.setCircleHoleRadius(2);
        lineDataSetGPA.setDrawValues(true);
        lineDataSetGPA.setLabel("GPA");
        lineDataSetGPA.setMode(LineDataSet.Mode.CUBIC_BEZIER);

        lineChart.setData(lineData);
        lineChart.getXAxis().setValueFormatter(new IndexAxisValueFormatter(semester));
        lineChart.getXAxis().setPosition(XAxis.XAxisPosition.BOTTOM);
        lineChart.getAxisRight().setEnabled(false);
        lineChart.getAxisLeft().setAxisMinimum(2f);
        lineChart.getAxisLeft().setAxisMaximum(4.3f);
        lineChart.getXAxis().setAxisMinimum(-0.3f);
        lineChart.getXAxis().setAxisMaximum((float)(semester.size() - 0.7));
        lineChart.getXAxis().setLabelCount(semester.size());
        lineChart.getXAxis().setGranularity(1f);
        lineChart.animateX(500, Easing.EaseInOutCubic);
        lineChart.setDescription(description);

        mData.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    String courseName = dataSnapshot.child("courseName").getValue(String.class);
                    String courseID = dataSnapshot.child("courseID").getValue(String.class);
                    int tc = dataSnapshot.child("tc").getValue(Integer.class);
                    double qt = dataSnapshot.child("qt").getValue(Double.class);
                    double ck = dataSnapshot.child("ck").getValue(Double.class);
                    String alphabet = dataSnapshot.child("alphabet").getValue(String.class);

                    CourseScore sv = new CourseScore(courseName, courseID, tc, qt, ck, alphabet);
                    arrayScore.add(sv);
                }
                if (adapter == null) {
                    adapter = new CustomAdapterScore(getContext(), arrayScore);
                    courseList.setAdapter(adapter);
                } else {
                    adapter.notifyDataSetChanged();
                }
                progressBar.setVisibility(View.GONE);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        return view;
    }
}