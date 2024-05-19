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

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import com.example.app_01.Adapter.CustomAdapterScore;
import com.example.app_01.Constructor.CourseScore;
import com.example.app_01.R;
import com.example.app_01.UtilsPack.Utils;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;


public class Service_Fragment extends Fragment {
    CustomAdapterScore adapter;
    ArrayList<CourseScore> arrayScore;
    ListView courseList;
    private String data;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.service_fragment, container, false);
        initLayout();

        courseList = view.findViewById(R.id.courses);
        adapter = new CustomAdapterScore(getContext(), arrayScore);
        courseList.setAdapter(adapter);
        courseList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                showDialogScore(getContext(), arrayScore.get(position));
            }
        });

        return view;
    }

    private void initLayout() {
        if (getContext() != null) {
            String value = Utils.getInstance().getValueFromSharedPreferences(getContext(),"share_preferences_data", "key_share_preferences_data_diem_thi_ca_nhan");
            this.data = value;
            if (value == null || value.equals("") || this.data.equals("[]")) {
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