package com.example.app_01.AppCompat;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.app_01.Adapter.HocPhiAdapter;
import com.example.app_01.Constructor.HocPhi;
import com.example.app_01.R;
import com.example.app_01.UtilsPack.Utils;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class HocPhiActivity extends AppCompatActivity {
    private ListView hocphi;
    private HocPhiAdapter adapter;
    private ArrayList<HocPhi> hocphiList;
    private String data, string01, string02;
    TextView txt01, txt02;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hocphi_activity);
        hocphi = findViewById(R.id.listHocPhi);
        txt01 = findViewById(R.id.tienHocPhi);
        txt02 = findViewById(R.id.ghichutongtienHP);
        initLayout();
        adapter = new HocPhiAdapter(this, hocphiList);
        hocphi.setAdapter(adapter);

        txt01.setText(string01);
        txt02.setText(string02);
        ImageButton btnBack = findViewById(R.id.backtoMain);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void initLayout() {
        String value = Utils.getInstance().getValueFromSharedPreferences(this, "share_preferences_data", "key_share_preferences_data_thong_tin_hoc_phi");
        this.data = value;
        if (value == null || value.equals("") || this.data.equals("[]")) {
            Toast.makeText(HocPhiActivity.this, "Không tìm thấy thông tin", Toast.LENGTH_SHORT).show();
        } else {
            showHocphi();
        }
    }

    private void showHocphi() {
        try {
            JSONObject js1 = new JSONObject(this.data);
            String str = js1.getString("soTienCanDongHP");
            String str1 = js1.getString("ghiChuSoTienHP");
            if (str.equals(" ")) {
                this.string01 = "000 đ";
            } else {
                this.string01 = str + " đ";
            }
            this.string02 = str1;
            if (js1.has("toanBoCongNoHP")) {
                JSONArray jsonArray = js1.getJSONArray("toanBoCongNoHP");
                this.hocphiList = new ArrayList<>();
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    this.hocphiList.add(new HocPhi(
                            jsonObject.getString("maHocPhanHP"),
                            jsonObject.getString("tenHocPhanHP"),
                            jsonObject.getString("soTien1TC"),
                            jsonObject.getString("tinChiHocPhi"),
                            jsonObject.getString("heSoHocPhi"),
                            jsonObject.getString("tongTienHP"),
                            jsonObject.getString("trangThaiDK"),
                            jsonObject.getString("loaiDangKi"),
                            jsonObject.getString("ghiChuHP")));
                }
            } else {
                Log.e("HocPhiActivity", "Khong co \"toanBoCongNoHP\"!!!");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
