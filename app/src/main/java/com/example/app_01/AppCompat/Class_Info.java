package com.example.app_01.AppCompat;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.app_01.Adapter.CustomAdapter;
import com.example.app_01.Constructor.SinhVien;
import com.example.app_01.R;
import com.example.app_01.UtilsPack.OnSwipeTouchListener;
import com.example.app_01.UtilsPack.Utils;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;


public class Class_Info extends AppCompatActivity {
    private ListView listClassView;
    private CustomAdapter adapter;
    private ArrayList<SinhVien> arrayClass;
    private String data;
    private Context context;
    private RelativeLayout main_layout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.class_info_activity);
        context = this;
        listClassView = findViewById(R.id.class_list);
        arrayClass = new ArrayList<>();
        initLayout();

        adapter = new CustomAdapter(this, arrayClass);
        listClassView.setAdapter(adapter);
        listClassView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                showDialogClass(context, arrayClass.get(position));
            }
        });
        ImageButton btn_back = findViewById(R.id.gobackclass);
        btn_back.setOnClickListener(v -> finish());
        main_layout = findViewById(R.id.main_layout);
        main_layout.setOnTouchListener(new OnSwipeTouchListener(this) {
            @Override
            public void onSwipeRight() {
                finish();
            }
        });
    }

    private void initLayout() {
        if (this != null) {
            String value = Utils.getInstance().getValueFromSharedPreferences(this,"share_preferences_data", "key_share_preferences_data_danh_sach_lop_sv");
            this.data = value;
            if (value == null || value.equals("") || this.data.equals("[]")) {
                Toast.makeText(this, "Không tìm thấy thông tin", Toast.LENGTH_SHORT).show();
            } else {
                showStudentDetail();
            }
        }
    }

    private void showStudentDetail() {
        try {
            JSONArray jsonArray = new JSONArray(this.data);
            arrayClass = new ArrayList<>();
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                this.arrayClass.add(new SinhVien(jsonObject.getString("maSV"),
                        jsonObject.getString("hoSV"),
                        jsonObject.getString("demSV"),
                        jsonObject.getString("tenSV"),
                        jsonObject.getString("ngaysinh"),
                        jsonObject.getString("tenlop"),
                        jsonObject.getString("ctdt"),
                        jsonObject.getString("trangthai")));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void showDialogClass(Context context, SinhVien sinhVien) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        LayoutInflater inflater = LayoutInflater.from(context);
        View dialogView = inflater.inflate(R.layout.class_member_dialog, null);
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

        TextView txt01 = dialogView.findViewById(R.id.hotendaydu);
        TextView txt02 = dialogView.findViewById(R.id.masosinhvien);
        TextView txt03 = dialogView.findViewById(R.id.ngaysinh);
        TextView txt04 = dialogView.findViewById(R.id.lopsv);
        TextView txt05 = dialogView.findViewById(R.id.chuongtrinh);
        TextView txt06 = dialogView.findViewById(R.id.trangthaihoc);
        TextView txt00 = dialogView.findViewById(R.id.title_txt);
        String hotenFull = sinhVien.getHo() + " " + sinhVien.getDem() + " " + sinhVien.getTen();
        txt00.setText(hotenFull);
        txt01.setText("Họ và tên: " + hotenFull);
        txt02.setText("Mã số sinh viên: " + sinhVien.getMSSV());
        txt03.setText("Ngày sinh: " + sinhVien.getNgaysinh());
        txt04.setText("Tên lớp: " + sinhVien.getTenlop());
        txt05.setText("Chương trình đào tạo: " + sinhVien.getCtdt());
        txt06.setText("Trạng thái học tập: " + sinhVien.getTrangthai());
        dialog.show();
    }
}