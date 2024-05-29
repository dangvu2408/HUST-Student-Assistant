package com.example.app_01.Dialog;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

import com.example.app_01.Adapter.ToeicAdapter;
import com.example.app_01.Constructor.ToeicScore;
import com.example.app_01.R;
import com.example.app_01.UtilsPack.HeightUtils;
import com.example.app_01.UtilsPack.Utils;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class Toeic_Dialog extends DialogFragment {
    public ListView listView;
    private String data, data0, data1;
    private ArrayList<ToeicScore> toeicScores;
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = requireActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.toeic_dialog, null);
        builder.setView(view); //setview khi bat su kien
        AlertDialog dialog1 = builder.create();
        dialog1.getWindow().setBackgroundDrawableResource(R.drawable.linearlayout_background);
        initLayout();
        listView = view.findViewById(R.id.listTOEIC);
        ToeicAdapter adapter = new ToeicAdapter(getContext(), toeicScores);
        listView.setAdapter(adapter);
        HeightUtils.setListViewHeight(listView);
        adapter.notifyDataSetChanged();

        Button cancel = view.findViewById(R.id.buttonCancel);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        return dialog1;
    }

    private void initLayout() {
        String value = Utils.getInstance().getValueFromSharedPreferences(getContext(),"share_preferences_data", "key_share_preferences_data_diem_thi_toeic");
        this.data = value;

        if (value == null || value.equals("") || this.data.equals("[]")) {
            Toast.makeText(getContext(), "Không tìm thấy thông tin", Toast.LENGTH_SHORT).show();
        } else {
            showTOEICScore();
        }
    }
    private void showTOEICScore() {
        try {
            JSONArray jsonArray = new JSONArray(this.data);
            toeicScores = new ArrayList<>();
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);

                this.toeicScores.add(new ToeicScore(
                        jsonObject.getString("masoSV"),
                        jsonObject.getString("hotenSV"),
                        jsonObject.getString("ngaySinh"),
                        jsonObject.getString("hocKi"),
                        jsonObject.getString("ghiChu"),
                        jsonObject.getString("ngayThi"),
                        jsonObject.getString("diemNghe"),
                        jsonObject.getString("diemDoc"),
                        jsonObject.getString("tongDiem")));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

