package com.example.app_01.AppCompat;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.app_01.Adapter.DKHocPhanAdapter;
import com.example.app_01.Constructor.HocPhanDK;
import com.example.app_01.R;
import com.example.app_01.UtilsPack.Utils;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class SapXepTKBHome extends AppCompatActivity {
    private ListView listDKHP;
    private DKHocPhanAdapter adapter;
    private ArrayList<HocPhanDK> dkhocphan;
    private String data, hockidki, tongtc;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.timetable_sorting_activity);
        TextView titleHKDK = findViewById(R.id.titleHKDK);
        TextView hint = findViewById(R.id.hint);
        TextView tongsotcdk = findViewById(R.id.tongsotcdk);
        initLayout();
        listDKHP = findViewById(R.id.listDKHP);
        adapter = new DKHocPhanAdapter(this, dkhocphan);
        listDKHP.setAdapter(adapter);
        titleHKDK.setText("Bảng đăng kí học phần kì " + this.hockidki);
        hint.setText("Chú ý: Đây là danh sách học phần bạn đã đăng kí cho học kì " + this.hockidki + ", nếu như thấy danh sách có gì sai sót, hãy cập nhật lại dữ liệu học tập!");
        tongsotcdk.setText("Tổng số TC đăng kí: " + this.tongtc);

        CheckBox checkall = findViewById(R.id.check_all);
        checkall.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                adapter.checkAll(isChecked);
            }
        });

        ImageButton btnBack = findViewById(R.id.backtoMain);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        Button findClass = findViewById(R.id.findClass);
        findClass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LayoutInflater inflater = getLayoutInflater();
                View layout = inflater.inflate(R.layout.custom_toast7, null);
                Toast toast0 = new Toast(SapXepTKBHome.this);
                toast0.setDuration(Toast.LENGTH_LONG);
                toast0.setView(layout);
                toast0.show();
            }
        });
    }

    private void initLayout() {
        String value = Utils.getInstance().getValueFromSharedPreferences(this, "share_preferences_data", "key_share_preferences_data_dang_ky_hoc_phan");
        this.data = value;
        if (value == null || value.equals("") || this.data.equals("[]")) {
            Toast.makeText(SapXepTKBHome.this, "Không tìm thấy thông tin", Toast.LENGTH_SHORT).show();
        } else {
            showDangkiHP();
        }
    }

    private void showDangkiHP() {
        try {
            JSONArray jsonArray = new JSONObject(this.data).getJSONArray("thongtinDK");
            this.dkhocphan = new ArrayList<>();
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                this.dkhocphan.add(new HocPhanDK(
                        jsonObject.getString("maHPDK"),
                        jsonObject.getString("tenHPDK"),
                        jsonObject.getString("ngayDK"),
                        jsonObject.getString("TTDK"),
                        jsonObject.getString("soTCDK"), true));
            }
            this.hockidki = new JSONObject(this.data).getString("thongtinHK");
            this.tongtc = new JSONObject(this.data).getString("tongtinchi");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
