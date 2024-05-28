package com.example.app_01.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.app_01.R;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;

import org.threeten.bp.DateTimeUtils;
import org.threeten.bp.LocalDate;
import org.threeten.bp.ZoneId;

import java.util.Calendar;
import java.util.Date;

public class Lichthi_Fragment extends Fragment {
    private TextView dayDisplay, khongthi;
    public Lichthi_Fragment() {}
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.lichthi_fragment, container, false);
        dayDisplay = view.findViewById(R.id.realtimeselect1);
        khongthi = view.findViewById(R.id.khongThi);
        MaterialCalendarView materialCalendar = view.findViewById(R.id.calendarLichThi);
        CalendarDay calendarDay = CalendarDay.today();
        materialCalendar.setSelectedDate(calendarDay);
        Calendar date = Calendar.getInstance();
        int dayOfMonth = date.get(Calendar.DAY_OF_MONTH);
        int month = date.get(Calendar.MONTH) + 1; // Tháng bắt đầu từ 0, nên cần cộng thêm 1
        int year = date.get(Calendar.YEAR);
        int thu = date.get(Calendar.DAY_OF_WEEK);
        String dateString = dayOfMonth + " Tháng " + month + ", " + year;
        String dateThi = "Ngày " + dayOfMonth + "/" + month + "/" + year + " không có lớp thi";
        dayDisplay.setText(dateString);
        khongthi.setText(dateThi);

        materialCalendar.setOnDateChangedListener(new OnDateSelectedListener() {
            @Override
            public void onDateSelected(@NonNull MaterialCalendarView widget, @NonNull CalendarDay date, boolean selected) {
                int dayOfMonth = date.getDay();
                int month = date.getMonth();
                int year = date.getYear();
                String datestring = dayOfMonth + " Tháng " + month + ", " + year;
                String datethi = "Ngày " + dayOfMonth + "/" + month + "/" + year + " không có lớp thi";
                dayDisplay.setText(datestring);
                khongthi.setText(datethi);
                LocalDate select = date.getDate();
                Date convert = DateTimeUtils.toDate(select.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
                Calendar cal = Calendar.getInstance();
                cal.setTime(convert);
            }
        });
        return view;
    }
}
