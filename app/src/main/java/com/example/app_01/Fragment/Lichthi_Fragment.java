package com.example.app_01.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.app_01.Adapter.LichThiAdapter;
import com.example.app_01.Constructor.LichThi;
import com.example.app_01.R;
import com.example.app_01.UtilsPack.Utils;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;

import org.json.JSONArray;
import org.json.JSONObject;
import org.threeten.bp.DateTimeUtils;
import org.threeten.bp.LocalDate;
import org.threeten.bp.ZoneId;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class Lichthi_Fragment extends Fragment {
    private TextView dayDisplay, khongthi, loadingTitle;
    private ListView listViewLichthi;
    private ArrayList<LichThi> arrayLichThi, filteredList;
    private ArrayList<String> maLopHoc;
    private String data, thang, ngay;
    public Lichthi_Fragment() {}
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.lichthi_fragment, container, false);
        dayDisplay = view.findViewById(R.id.realtimeselect1);
        khongthi = view.findViewById(R.id.khongThi);
        listViewLichthi = view.findViewById(R.id.lichThilist);
        loadingTitle = view.findViewById(R.id.loadingTitle);
        ProgressBar progressBar = view.findViewById(R.id.progressBar);
        initLayout();
        arrayLichThi = new ArrayList<>();
        filteredList = new ArrayList<>();
        DatabaseReference mData = FirebaseDatabase.getInstance().getReference().child("lichCK");
        mData.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    String maHP = dataSnapshot.child("Mã HP").getValue(String.class);
                    String tenHP = dataSnapshot.child("Tên học phần").getValue(String.class);
                    String maLop = String.valueOf(dataSnapshot.child("Mã lớp").getValue(Integer.class));
                    String phongThi = dataSnapshot.child("Phòng thi").getValue(String.class);
                    String ngayThi = dataSnapshot.child("Ngày thi").getValue(String.class);
                    String kipThi = dataSnapshot.child("Kíp thi").getValue(String.class);
                    LichThi lichThi = new LichThi(maHP, tenHP, maLop, phongThi, ngayThi, kipThi);
                    arrayLichThi.add(lichThi);
                }
                filteredList = filtered();
                LichThiAdapter adapter = new LichThiAdapter(getContext(), filteredList);
                listViewLichthi.setAdapter(adapter);
                progressBar.setVisibility(View.GONE);
                loadingTitle.setVisibility(View.GONE);
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        MaterialCalendarView materialCalendar = view.findViewById(R.id.calendarLichThi);
        CalendarDay calendarDay = CalendarDay.today();
        materialCalendar.setSelectedDate(calendarDay);
        Calendar date = Calendar.getInstance();
        int dayOfMonth = date.get(Calendar.DAY_OF_MONTH);
        int month = date.get(Calendar.MONTH) + 1; // Tháng bắt đầu từ 0, nên cần cộng thêm 1
        if (dayOfMonth < 10) {
            ngay = "0" + dayOfMonth;
        } else {
            ngay = String.valueOf(dayOfMonth);
        }
        if (month < 10) {
            thang = "0" + month;
        } else {
            thang = String.valueOf(month);
        }
        int year = date.get(Calendar.YEAR);
        int thu = date.get(Calendar.DAY_OF_WEEK);
        String dateString = ngay + " Tháng " + thang + ", " + year;
        String dateThi = "Ngày " + ngay + "/" + thang + "/" + year + " không có lớp thi";
        String testDate = ngay + "/" + month + "/" + year;
        for (LichThi item : filteredList) {
            if(item.getNgayThi().equals(testDate)) {
                dateThi = "Ngày " + ngay + "/" + thang + "/" + year + " có lớp thi";
            }
        }
        dayDisplay.setText(dateString);
        khongthi.setText(dateThi);

        materialCalendar.setOnDateChangedListener(new OnDateSelectedListener() {
            @Override
            public void onDateSelected(@NonNull MaterialCalendarView widget, @NonNull CalendarDay date, boolean selected) {
                int dayOfMonth = date.getDay();
                int month = date.getMonth();
                int year = date.getYear();
                String thang0 = "", ngay0 = "";
                if (month < 10) {
                    thang0 = "0" + month;
                } else {
                    thang0 = String.valueOf(month);
                }
                if (dayOfMonth < 10) {
                    ngay0 = "0" + dayOfMonth;
                } else {
                    ngay0 = String.valueOf(dayOfMonth);
                }
                String datestring = ngay0 + " Tháng " + thang0 + ", " + year;
                String datethi = "Ngày " + ngay0 + "/" + thang0 + "/" + year + " không có lớp thi";
                String testDate = ngay0 + "/" + thang0 + "/" + year;
                for (LichThi item : filteredList) {
                    if(item.getNgayThi().equals(testDate)) {
                        datethi = "Ngày " + ngay0 + "/" + thang0 + "/" + year + " có lớp thi";
                    }
                }
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

    private void initLayout() {
        if (getContext() != null) {
            String value = Utils.getInstance().getValueFromSharedPreferences(getContext(),"share_preferences_data", "key_share_preferences_data_tkb");
            this.data = value;
            if (value == null || value.equals("") || this.data.equals("[]")) {
                Toast.makeText(getContext(), "Không tìm thấy thông tin", Toast.LENGTH_SHORT).show();
            } else {
                addMaHPList();
            }
        }
    }

    private void addMaHPList() {
        try {
            JSONArray jsonArray = new JSONArray(this.data);
            this.maLopHoc = new ArrayList<>();
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                this.maLopHoc.add(jsonObject.getString("Ma_lop"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private ArrayList<LichThi> filtered() {
        ArrayList<LichThi> stack = new ArrayList<>();
        for (LichThi item : arrayLichThi) {
            for (int i = 0; i < this.maLopHoc.size(); i++) {
                if (item.getMaLop().equals(maLopHoc.get(i))) {
                    stack.add(item);
                }
            }
        }
        return stack;
    }
}
