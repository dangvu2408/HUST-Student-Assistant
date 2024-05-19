package com.example.app_01.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.ProgressBar;

import androidx.fragment.app.Fragment;

import com.example.app_01.Adapter.CustomAdapterScore;
import com.example.app_01.Constructor.CourseScore;
import com.example.app_01.R;

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

        arrayScore = new ArrayList<>();
        progressBar = view.findViewById(R.id.progress_bar);
        progressBar.setVisibility(View.VISIBLE);



        return view;
    }
}