package com.example.app_01.Fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import com.example.app_01.Adapter.CustomAdapterTimetable;
import com.example.app_01.Constructor.TimeTable;
import com.example.app_01.R;
import com.example.app_01.UtilsPack.HeightUtils;
import com.example.app_01.UtilsPack.Utils;
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

public class TKB_Fragment extends Fragment {
    private TextView textView, noLichHoc;
    private ArrayList<TimeTable> timeTables, filteredDay;
    private CustomAdapterTimetable adapterTimetable;
    private ListView listTimetable;
    public int dayOfWeek;
    private String data;
    public TKB_Fragment() {}
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.thoikhoabieu_fragment, container, false);
        textView = view.findViewById(R.id.realtimeselect);
        MaterialCalendarView material = view.findViewById(R.id.calendarTKBFrg);
        listTimetable = view.findViewById(R.id.thoiKhoaBieuCus);
        noLichHoc = view.findViewById(R.id.khongHoc);
        initLayout();
        CalendarDay calendarDay = CalendarDay.today();
        material.setSelectedDate(calendarDay);
        Calendar date = Calendar.getInstance();
        int dayOfMonth = date.get(Calendar.DAY_OF_MONTH);
        int month = date.get(Calendar.MONTH) + 1; // Tháng bắt đầu từ 0, nên cần cộng thêm 1
        int year = date.get(Calendar.YEAR);
        int thu = date.get(Calendar.DAY_OF_WEEK);
        String str = String.valueOf(thu);
        String dateString = dayOfMonth + " Tháng " + month + ", " + year;
        textView.setText(dateString);
        filteredDay = filtered(str);
        if (filteredDay == null) {
            noLichHoc.setVisibility(View.VISIBLE);
            noLichHoc.setText("Không có lớp học nào trong học kì này");
        } else {
            noLichHoc.setVisibility(View.GONE);
            adapterTimetable = new CustomAdapterTimetable(getContext(), filteredDay);
            listTimetable.setAdapter(adapterTimetable);
            if (adapterTimetable.getCount() == 0) {
                noLichHoc.setVisibility(View.VISIBLE);
                noLichHoc.setText("Không có lớp học nào");
            } else {
                noLichHoc.setVisibility(View.GONE);
            }
            HeightUtils.setListViewHeight(listTimetable);
        }
        listTimetable.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                showDialogClass(getContext(), filteredDay.get(position));
            }
        });

        material.setOnDateChangedListener(new OnDateSelectedListener() {
            @Override
            public void onDateSelected(@NonNull MaterialCalendarView widget, @NonNull CalendarDay date, boolean selected) {
                int dayOfMonth = date.getDay();
                int month = date.getMonth();
                int year = date.getYear();
                String datestring = dayOfMonth + " Tháng " + month + ", " + year;
                textView.setText(datestring);
                LocalDate select = date.getDate();
                Date convert = DateTimeUtils.toDate(select.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
                Calendar cal = Calendar.getInstance();
                cal.setTime(convert);
                dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);
                String str = String.valueOf(dayOfWeek);
                filteredDay = filtered(str);
                if (filteredDay == null) {
                    noLichHoc.setVisibility(View.VISIBLE);
                    noLichHoc.setText("Không có lớp học nào trong học kì này");
                } else {
                    noLichHoc.setVisibility(View.GONE);
                    adapterTimetable = new CustomAdapterTimetable(getContext(), filteredDay);
                    listTimetable.setAdapter(adapterTimetable);
                    if (adapterTimetable.getCount() == 0) {
                        noLichHoc.setVisibility(View.VISIBLE);
                        noLichHoc.setText("Không có lớp học nào");
                    } else {
                        noLichHoc.setVisibility(View.GONE);
                    }
                    HeightUtils.setListViewHeight(listTimetable);
                }
                listTimetable.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        showDialogClass(getContext(), filteredDay.get(position));
                    }
                });
            }
        });
        return view;
    }

    private ArrayList<TimeTable> filtered(String select) {
        ArrayList<TimeTable> stack = new ArrayList<>();
        if (timeTables == null) {
            return null;
        }
        for (TimeTable item : timeTables) {
            if (item.getThoigian().equals(" ") && item.getLoailop().equals("ĐA") || item.getThoigian().length() == 1) {
                stack.add(item);
            } else if (!item.getThoigian().equals(" ")) {
                if (item.getThoigian().substring(4, 5).equals(select)) {
                    stack.add(item);
                }
            }
        }
        return stack;
    }

    private void showDialogClass(Context context, TimeTable timeTable) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        LayoutInflater inflater = LayoutInflater.from(context);
        View dialogView = inflater.inflate(R.layout.classtkb_dialog, null);
        builder.setView(dialogView);
        AlertDialog dialog = builder.create();
        dialog.getWindow().setBackgroundDrawableResource(R.drawable.linearlayout_background);
        Button btnCancel = dialogView.findViewById(R.id.thoatDialog);
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        TextView txt01 = dialogView.findViewById(R.id.malop);
        TextView txt02 = dialogView.findViewById(R.id.loaihinh);
        TextView txt03 = dialogView.findViewById(R.id.giangvien);
        TextView txt04 = dialogView.findViewById(R.id.nhom);
        TextView txt05 = dialogView.findViewById(R.id.ghichu);
        TextView txt06 = dialogView.findViewById(R.id.hinhthuc);
        TextView txt07 = dialogView.findViewById(R.id.codeteams);
        TextView txt08 = dialogView.findViewById(R.id.link);
        TextView txt09 = dialogView.findViewById(R.id.thoigiandiadiem);
        TextView txt10 = dialogView.findViewById(R.id.tuanhoc);
        TextView txt00 = dialogView.findViewById(R.id.title_txt);
        txt00.setText(timeTable.getMahocphan() + " - " + timeTable.getTenlop());
        txt01.setText("Mã lớp: " + timeTable.getMalop());
        txt02.setText("Loại hình: " + timeTable.getLoailop());
        txt03.setText("GVDH: "+ timeTable.getGiangvien());
        txt04.setText("Nhóm: " + timeTable.getNhom());
        txt05.setText("Ghi chú: " +timeTable.getGhichu());
        txt06.setText("Hình thức GD: " + timeTable.getHinhthuc());
        txt07.setText("Mã code Teams: " + timeTable.getCodeteams());
        txt08.setText("Link: " + timeTable.getLink());
        txt09.setText(timeTable.getThoigian() + ", " + timeTable.getPhonghoc());
        txt10.setText("Tuần học: " + timeTable.getTuanhoc());
        dialog.show();
    }
    private void initLayout() {
        if (getContext() != null) {
            String value = Utils.getInstance().getValueFromSharedPreferences(getContext(),"share_preferences_data", "key_share_preferences_data_tkb");
            this.data = value;
            if (value == null || value.equals("") || this.data.equals("[]")) {
                Toast.makeText(getContext(), "Không tìm thấy thông tin", Toast.LENGTH_SHORT).show();
            } else {
                showTimeTable();
            }
        }
    }
    private void showTimeTable() {
        try {
            JSONArray jsonArray = new JSONArray(this.data);
            this.timeTables = new ArrayList<>();
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                this.timeTables.add(new TimeTable(jsonObject.getString("Thoi_gian"),
                        jsonObject.getString("Tuan_hoc"),
                        jsonObject.getString("Phong_hoc"),
                        jsonObject.getString("Ma_lop"),
                        jsonObject.getString("Loai_lop"),
                        jsonObject.getString("Nhom"),
                        jsonObject.getString("Ma_HP"),
                        jsonObject.getString("Ten_lop"),
                        jsonObject.getString("Ghi_chu"),
                        jsonObject.getString("Hinh_thuc_day"),
                        jsonObject.getString("Giang_vien"),
                        jsonObject.getString("Link_online"),
                        jsonObject.getString("Code_teams")));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
