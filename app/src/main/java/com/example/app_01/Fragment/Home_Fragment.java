package com.example.app_01.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.app_01.Adapter.ItemsAdapter;
import com.example.app_01.Constructor.ReItems;
import com.example.app_01.R;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Home_Fragment extends Fragment {
    private RecyclerView recyclerView;
    private ItemsAdapter adapter;
    private CardView cardView;
    private boolean isTrans = false;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.home_fragment, container, false);
        cardView = view.findViewById(R.id.cardview_trans);

        recyclerView = view.findViewById(R.id.recycler);
        adapter = new ItemsAdapter(getContext());
        TextView textView = view.findViewById(R.id.date_realtime);

        MaterialCalendarView materialCalendarView = view.findViewById(R.id.calendarView);
        CalendarDay calendar = CalendarDay.today();
        materialCalendarView.setSelectedDate(calendar);

        Calendar date = Calendar.getInstance();

        int dayOfWeek = date.get(Calendar.DAY_OF_WEEK);
        int dayOfMonth = date.get(Calendar.DAY_OF_MONTH);
        int month = date.get(Calendar.MONTH) + 1; // Tháng bắt đầu từ 0, nên cần cộng thêm 1
        int year = date.get(Calendar.YEAR);

        String[] daysWeek = {"Chủ Nhật", "Thứ Hai", "Thứ Ba", "Thứ Tư", "Thứ Năm", "Thứ Sáu", "Thứ Bảy"};

        SimpleDateFormat dateFormat = new SimpleDateFormat("E, dd/MM/yyyy");

        String dateString = dayOfMonth + " Tháng " + month + ", " + year;
        textView.setText(dateString);

        materialCalendarView.setOnDateChangedListener(new OnDateSelectedListener() {
            @Override
            public void onDateSelected(@NonNull MaterialCalendarView widget, @NonNull CalendarDay date, boolean selected) {
                int dayOfMonth = date.getDay();
                int month = date.getMonth();
                int year = date.getYear();

                String datestring = dayOfMonth + " Tháng " + month + ", " + year;
                textView.setText(datestring);
            }
        });

        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 3);
        recyclerView.setLayoutManager(gridLayoutManager);

        adapter.setData(getList());
        recyclerView.setAdapter(adapter);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        if (isTrans) {
            cardView.setTranslationX(-700);
            cardView.animate().translationX(0).setDuration(1800).setInterpolator(new AccelerateDecelerateInterpolator()).start();
        }
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (savedInstanceState == null) {
            isTrans = true;
        }

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