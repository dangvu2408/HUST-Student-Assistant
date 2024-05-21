package com.example.app_01.Fragment;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import com.example.app_01.Adapter.CustomAdapterScore;
import com.example.app_01.Adapter.CustomMarkerView;
import com.example.app_01.Adapter.CustomMarkerView2;
import com.example.app_01.Constructor.CourseScore;
import com.example.app_01.R;
import com.example.app_01.UtilsPack.Utils;
import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;


public class Service_Fragment extends Fragment {
    public LineChart chartCpa;
    public LineChart chartGpa;
    CustomAdapterScore adapter;
    ArrayList<CourseScore> arrayScore;
    ListView courseList;
    private String data, data0;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.service_fragment, container, false);
        courseList = view.findViewById(R.id.courses);
        initLayout();
        adapter = new CustomAdapterScore(getContext(), arrayScore);
        courseList.setAdapter(adapter);
        courseList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                showDialogScore(getContext(), arrayScore.get(position));
            }
        });

        chartGpa = view.findViewById(R.id.line_chart_gpa);
        chartCpa = view.findViewById(R.id.line_chart_cpa);
        String str = this.data0;
        if (str != null && !str.equals("")) {
            try {
                JSONArray jsonArray = new JSONArray(this.data0);
                if (jsonArray.length() > 0) {
                    ArrayList arrayListGPA = new ArrayList();
                    ArrayList arrayListCPA = new ArrayList();
                    ArrayList arrayListHK = new ArrayList();
                    ArrayList arrayListTC = new ArrayList();
                    ArrayList arrayListTCTL = new ArrayList();
                    for (int i = jsonArray.length() - 1; i >= 0; i--) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        arrayListGPA.add(new Entry((float)(jsonArray.length() - 1 - i), Float.parseFloat(jsonObject.getString("gpa"))));
                        arrayListCPA.add(new Entry((float)(jsonArray.length() - 1 - i), Float.parseFloat(jsonObject.getString("cpa"))));
                        arrayListHK.add(jsonObject.getString("hockihoc"));
                        arrayListTC.add(jsonObject.getString("tinchiqua"));
                        arrayListTCTL.add(jsonObject.getString("tinchitichluy"));
                    }
                    LineDataSet lineDataSetGPA = new LineDataSet(arrayListGPA, "");
                    LineData lineDataGPA = new LineData(lineDataSetGPA);
                    lineDataSetGPA.setValueTextSize(12f);
                    lineDataSetGPA.setColor(Color.RED);
                    lineDataSetGPA.setCircleColor(Color.RED);
                    lineDataSetGPA.setCircleRadius(4);
                    lineDataSetGPA.setCircleHoleRadius(2);
                    lineDataSetGPA.setDrawValues(true);
                    lineDataSetGPA.setLabel("GPA");
                    lineDataSetGPA.setMode(LineDataSet.Mode.CUBIC_BEZIER);
                    chartGpa.setData(lineDataGPA);
                    chartGpa.getXAxis().setValueFormatter(new IndexAxisValueFormatter(arrayListHK));
                    chartGpa.getXAxis().setAxisMaximum((float)(arrayListHK.size() - 0.7));
                    chartGpa.getXAxis().setLabelCount(arrayListHK.size());
                    chartGpa.getXAxis().setPosition(XAxis.XAxisPosition.BOTTOM);
                    chartGpa.getAxisRight().setEnabled(false);
                    chartGpa.getAxisLeft().setAxisMinimum(0f);
                    chartGpa.getAxisLeft().setAxisMaximum(4.3f);
                    chartGpa.getXAxis().setAxisMinimum(-0.3f);
                    chartGpa.getXAxis().setLabelCount(arrayListHK.size());
                    chartGpa.getXAxis().setGranularity(1f);
                    chartGpa.animateX(500, Easing.EaseInOutCubic);
                    chartGpa.getXAxis().enableGridDashedLine(5f, 5f, 0f);
                    chartGpa.getAxisLeft().enableGridDashedLine(5f, 5f, 0f);
                    Description description = chartGpa.getDescription();
                    description.setText("");
                    description.setEnabled(false);
                    CustomMarkerView markerView1 = new CustomMarkerView(getContext(), R.layout.marker_view_linechart, arrayListTC, arrayListHK);
                    chartGpa.setMarker(markerView1);


                    LineDataSet lineDataSetCPA = new LineDataSet(arrayListCPA, "");
                    LineData lineDataCPA = new LineData(lineDataSetCPA);
                    lineDataSetCPA.setValueTextSize(12f);
                    lineDataSetCPA.setColor(Color.parseColor("#02AB63"));
                    lineDataSetCPA.setCircleColor(Color.parseColor("#02AB63"));
                    lineDataSetCPA.setCircleRadius(4);
                    lineDataSetCPA.setCircleHoleRadius(2);
                    lineDataSetCPA.setDrawValues(true);
                    lineDataSetCPA.setLabel("CPA");
                    lineDataSetCPA.setMode(LineDataSet.Mode.CUBIC_BEZIER);
                    chartCpa.setData(lineDataCPA);
                    chartCpa.getXAxis().setValueFormatter(new IndexAxisValueFormatter(arrayListHK));
                    chartCpa.getXAxis().setAxisMaximum((float)(arrayListHK.size() - 0.7));
                    chartCpa.getXAxis().setLabelCount(arrayListHK.size());
                    chartCpa.getXAxis().setPosition(XAxis.XAxisPosition.BOTTOM);
                    chartCpa.getAxisRight().setEnabled(false);
                    chartCpa.getAxisLeft().setAxisMinimum(0f);
                    chartCpa.getAxisLeft().setAxisMaximum(4.3f);
                    chartCpa.getXAxis().setAxisMinimum(-0.3f);
                    chartCpa.getXAxis().setLabelCount(arrayListHK.size());
                    chartCpa.getXAxis().setGranularity(1f);
                    chartCpa.animateX(500, Easing.EaseInOutCubic);
                    chartCpa.getXAxis().enableGridDashedLine(5f, 5f, 0f);
                    chartCpa.getAxisLeft().enableGridDashedLine(5f, 5f, 0f);
                    Description description1 = chartCpa.getDescription();
                    description1.setText("");
                    description1.setEnabled(false);
                    CustomMarkerView2 markerView2 = new CustomMarkerView2(getContext(), R.layout.marker_view_linechart2, arrayListTCTL, arrayListHK);
                    chartCpa.setMarker(markerView2);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }



        return view;
    }

    private void initLayout() {
        if (getContext() != null) {
            String value = Utils.getInstance().getValueFromSharedPreferences(getContext(),"share_preferences_data", "key_share_preferences_data_diem_thi_ca_nhan");
            String str = Utils.getInstance().getValueFromSharedPreferences(getContext(),"share_preferences_data", "key_share_preferences_data_diem_gpa_cpa");
            this.data = value;
            this.data0 = str;
            if (value == null || str == null ||
                    value.equals("") || str.equals("") ||
                    this.data.equals("[]") || this.data0.equals("[]")) {
                Toast.makeText(getContext(), "Không tìm thấy thông tin", Toast.LENGTH_SHORT).show();
            } else {
                showCourseScore();
            }
        }
    }

    private void showCourseScore() {
        try {
            JSONArray jsonArray = new JSONArray(this.data);
            arrayScore = new ArrayList<>();
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                this.arrayScore.add(new CourseScore(
                        jsonObject.getString("HocKi"),
                        jsonObject.getString("MaHocPhan"),
                        jsonObject.getString("TenHocPhan"),
                        jsonObject.getString("TinChi"),
                        jsonObject.getString("LopHoc"),
                        jsonObject.getString("diemQT"),
                        jsonObject.getString("diemThi"),
                        jsonObject.getString("diemChu")));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void showDialogScore(Context context, CourseScore courseScore) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        LayoutInflater inflater = LayoutInflater.from(context);
        View dialogView = inflater.inflate(R.layout.course_score_dialog, null);
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

        TextView txt01 = dialogView.findViewById(R.id.mahocphan);
        TextView txt02 = dialogView.findViewById(R.id.hocki);
        TextView txt03 = dialogView.findViewById(R.id.lophoc);
        TextView txt04 = dialogView.findViewById(R.id.sotinchi);
        TextView txt05 = dialogView.findViewById(R.id.diemquatrinh);
        TextView txt06 = dialogView.findViewById(R.id.diemthi);
        TextView txt07 = dialogView.findViewById(R.id.diemchu);
        TextView txt00 = dialogView.findViewById(R.id.title_txt);
        txt00.setText(courseScore.getTenHP());
        txt01.setText("Mã HP: " + courseScore.getMaHP());
        txt02.setText("Học kì: " + courseScore.getHocKi());
        txt03.setText("Lớp học: " + courseScore.getLophoc());
        txt04.setText("Số tín chỉ: " + courseScore.getTinchi());
        txt05.setText("Điểm QT: " + courseScore.getDiemQT());
        txt06.setText("Điểm thi KTHP: " + courseScore.getDiemthi());
        txt07.setText(courseScore.getDiemchu());
        dialog.show();
    }


}