package com.example.app_01.AppCompat;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.app_01.Adapter.DiemKiMoiAdapter;
import com.example.app_01.Constructor.InputGrade;
import com.example.app_01.R;
import com.example.app_01.UtilsPack.Utils;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;


public class Grade_Term extends AppCompatActivity {
    private ListView listInputGrade;
    private DiemKiMoiAdapter adapter;
    private ArrayList<InputGrade> arrayGrade;
    private String data;
    private Context context;
    private TextView no_info;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.input_grade_term_activity);
        context = this;
        listInputGrade = findViewById(R.id.grade_term_list);
        no_info = findViewById(R.id.khong_co_info);
        arrayGrade = new ArrayList<>();
        initLayout();
        adapter = new DiemKiMoiAdapter(context, arrayGrade);
        listInputGrade.setAdapter(adapter);
        ImageButton btn_back = findViewById(R.id.gobackclass);
        btn_back.setOnClickListener(v -> finish());
    }

    private void initLayout() {
        if (this != null) {
            String value = Utils.getInstance().getValueFromSharedPreferences(this,"share_preferences_data", "key_share_preferences_data_thong_tin_nhap_diem_ki_moi");
            this.data = value;
            if (value == null || value.equals("") || this.data.equals("[]")) {
                no_info.setVisibility(View.VISIBLE);
            } else {
                no_info.setVisibility(View.GONE);
                showStudentDetail();
            }
        }
    }

    private void showStudentDetail() {
        try {
            JSONArray jsonArray = new JSONArray(this.data);
            this.arrayGrade = new ArrayList<>();
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                this.arrayGrade.add(new InputGrade(
                        jsonObject.getString("masinhvien"),
                        jsonObject.getString("malop"),
                        jsonObject.getString("tenlop"),
                        jsonObject.getString("trongsoqt"),
                        jsonObject.getString("diemqt"),
                        jsonObject.getString("ttdiemqt"),
                        jsonObject.getString("diemthi"),
                        jsonObject.getString("ttdiemthi")));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}