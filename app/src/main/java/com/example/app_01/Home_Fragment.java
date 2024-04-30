package com.example.app_01;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;


public class Home_Fragment extends Fragment {
    private RecyclerView recyclerView;
    private ItemsAdapter adapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.home_fragment, container, false);

        recyclerView = view.findViewById(R.id.recycler);
        adapter = new ItemsAdapter(getContext());

        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 3);
        recyclerView.setLayoutManager(gridLayoutManager);

        adapter.setData(getList());
        recyclerView.setAdapter(adapter);
        return view;
    }

    private List<ReItems> getList() {
        List<ReItems> list = new ArrayList<>();
        list.add(new ReItems(R.drawable.calendar, "Thời khóa biểu"));
        list.add(new ReItems(R.drawable.data, "Sắp xếp thời khóa biểu"));
        list.add(new ReItems(R.drawable.checklist, "Chương trình đào tạo"));
        list.add(new ReItems(R.drawable.target, "Mục tiêu ra trường"));
        list.add(new ReItems(R.drawable.money, "Học phí - Công nợ"));
        list.add(new ReItems(R.drawable.rank, "Bảng xếp hạng"));
        return list;
    }
}