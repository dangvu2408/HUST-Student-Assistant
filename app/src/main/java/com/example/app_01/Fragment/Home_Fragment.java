package com.example.app_01.Fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import com.example.app_01.Adapter.CustomAdapterTimetable;
import com.example.app_01.Adapter.ItemsAdapter;
import com.example.app_01.AppCompat.TKBActivity;
import com.example.app_01.Constructor.ReItems;
import com.example.app_01.Constructor.TimeTable;
import com.example.app_01.R;
import com.example.app_01.UtilsPack.HeightUtils;
import com.example.app_01.UtilsPack.Utils;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.threeten.bp.DateTimeUtils;
import org.threeten.bp.LocalDate;
import org.threeten.bp.ZoneId;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class Home_Fragment extends Fragment {
    public TextView txt;
    private TextView noClassToday;
    public int dayOfWeek;
    private CardView cardView;
    private ListView listTimetable;
    private boolean isTrans = false;
    private CustomAdapterTimetable adapterTimetable;
    private ArrayList<TimeTable> timeTables;
    private ArrayList<TimeTable> filteredTimes;
    private String data;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.home_fragment, container, false);
        initLayout();
        cardView = view.findViewById(R.id.cardview_trans);
        listTimetable = view.findViewById(R.id.thoikhoabieu);
        GridView gridView = view.findViewById(R.id.gridview);
        List<ReItems> list = new ArrayList<>();
        list.add(new ReItems(R.drawable.timetablehust10, "Thời khóa biểu", "Tra cứu thời khóa biểu, lịch thi"));
        list.add(new ReItems(R.drawable.customtimetable, "Sắp xếp TKB", "Sắp xếp, chỉnh sửa thời khóa biểu"));
        list.add(new ReItems(R.drawable.checklisthust, "Danh mục học phần", "Tra cứu học phần trong CTDT"));
        list.add(new ReItems(R.drawable.tuitionhust, "Học phí - Công nợ", "Tra cứu thông tin về học phí"));
        ItemsAdapter adapter = new ItemsAdapter(this.getContext(), list);
        gridView.setAdapter(adapter);
        HeightUtils.setGridViewHeight(gridView, 2);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (getActivity() != null) {
                    switch (position) {
                        case 0:
                            startActivity(new Intent(getActivity(), TKBActivity.class));
                            break;
                        case 1:
                            startActivity(new Intent(getActivity(), TKBActivity.class));
                            break;
                        case 2:
                            startActivity(new Intent(getActivity(), TKBActivity.class));
                            break;
                        case 3:
                            startActivity(new Intent(getActivity(), TKBActivity.class));
                            break;
                    }
                }

            }
        });



        noClassToday = view.findViewById(R.id.nolichhoc);
        TextView textView = view.findViewById(R.id.date_realtime);

        try {
            txt = view.findViewById(R.id.namestudent);
            JSONObject jsonObject = new JSONObject(Utils.getInstance().getValueFromSharedPreferences(getContext(), "share_preferences_data", "key_share_preferences_data_thong_tin_sinh_vien"));
            txt.setText(jsonObject.getString("Ho_ten"));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        MaterialCalendarView materialCalendarView = view.findViewById(R.id.calendarView);
        CalendarDay calendar = CalendarDay.today();
        materialCalendarView.setSelectedDate(calendar);
        Calendar date = Calendar.getInstance();

        int dayOfMonth = date.get(Calendar.DAY_OF_MONTH);
        int month = date.get(Calendar.MONTH) + 1; // Tháng bắt đầu từ 0, nên cần cộng thêm 1
        int year = date.get(Calendar.YEAR);
        int thu = date.get(Calendar.DAY_OF_WEEK);
        String str00 = String.valueOf(thu);
        String dateString = dayOfMonth + " Tháng " + month + ", " + year;
        textView.setText(dateString);
        filteredTimes = filtered(str00);

        adapterTimetable = new CustomAdapterTimetable(getContext(), filteredTimes);
        listTimetable.setAdapter(adapterTimetable);
        if (adapterTimetable.getCount() == 0) {
            noClassToday.setVisibility(View.VISIBLE);
            noClassToday.setText("Không có lớp học nào");
        } else {
            noClassToday.setVisibility(View.GONE);
        }
        HeightUtils.setListViewHeight(listTimetable);
        listTimetable.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                showDialogClass(getContext(), filteredTimes.get(position));
            }
        });

        materialCalendarView.setOnDateChangedListener(new OnDateSelectedListener() {
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
                filteredTimes = filtered(str);
                adapterTimetable = new CustomAdapterTimetable(getContext(), filteredTimes);
                listTimetable.setAdapter(adapterTimetable);
                if (adapterTimetable.getCount() == 0) {
                    noClassToday.setVisibility(View.VISIBLE);
                    noClassToday.setText("Không có lớp học nào");
                } else {
                    noClassToday.setVisibility(View.GONE);
                }
                HeightUtils.setListViewHeight(listTimetable);
                listTimetable.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        showDialogClass(getContext(), filteredTimes.get(position));
                    }
                });
            }
        });
        return view;
    }

    

    @Override
    public void onResume() {
        if (!isTrans) {
            cardView.setTranslationY(-400);
            cardView.animate().translationY(0).setDuration(1800).setInterpolator(new AccelerateDecelerateInterpolator()).start();
        }
        super.onResume();
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (savedInstanceState == null) {
            isTrans = true;
        }
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
            timeTables = new ArrayList<>();
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

    private ArrayList<TimeTable> filtered(String select) {
        ArrayList<TimeTable> stack = new ArrayList<>();
        for (TimeTable item : timeTables) {
            if (item.getThoigian().substring(4, 5).equals(select)) {
                stack.add(item);
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
}