package com.example.app_01.AppCompat;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.app_01.Adapter.CTDTAdapter;
import com.example.app_01.Constructor.CTDT;
import com.example.app_01.R;
import com.example.app_01.UtilsPack.OnSwipeTouchListener;
import com.example.app_01.UtilsPack.Utils;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class ChuongtrinhActivity extends AppCompatActivity {
    private ListView ctdt;
    private CTDTAdapter adapter;
    private ArrayList<CTDT> chuongtrinh;
    private String data;
    private RelativeLayout main_layout;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.program_activity);
        ctdt = findViewById(R.id.listCTDT);
        initLayout();
        adapter = new CTDTAdapter(this, chuongtrinh);
        ctdt.setAdapter(adapter);

        ImageButton btnBack = findViewById(R.id.backtoMain);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        main_layout = findViewById(R.id.main_layout);
        main_layout.setOnTouchListener(new OnSwipeTouchListener(this) {
            @Override
            public void onSwipeRight() {
                finish();
            }
        });

    }

    private void initLayout() {
        String value = Utils.getInstance().getValueFromSharedPreferences(this, "share_preferences_data", "key_share_preferences_data_chuong_trinh_dao_tao_sv");
        this.data = value;
        if (value == null || value.equals("") || this.data.equals("[]")) {
            Toast.makeText(ChuongtrinhActivity.this, "Không tìm thấy thông tin", Toast.LENGTH_SHORT).show();
        } else {
            showChuongtrinh();
        }
    }

    private void showChuongtrinh() {
        try {
            JSONArray jsonArray = new JSONArray(this.data);
            chuongtrinh = new ArrayList<>();
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                this.chuongtrinh.add(new CTDT(
                        jsonObject.getString("maHPCTDT"),
                        jsonObject.getString("tenHPCTDT"),
                        jsonObject.getString("kyhocCTDT"),
                        jsonObject.getString("tinchiDT"),
                        jsonObject.getString("maHPhoc"),
                        jsonObject.getString("ghichuHPH"),
                        jsonObject.getString("dienchuCTDT"),
                        jsonObject.getString("diemsoCTDT"),
                        jsonObject.getString("vienkhoaDT")));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
