package com.example.app_01.Dialog;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

import com.example.app_01.R;
import com.example.app_01.UtilsPack.Utils;

import org.json.JSONException;
import org.json.JSONObject;

public class Student_Info_Dialog extends DialogFragment {
    public TextView text01, text02, text03, text04, text05, text06, text07, text08;
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = requireActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_student_info, null);
        builder.setView(view); //setview khi bat su kien

//        LinearLayout linearLayout = view.findViewById(R.id.linear_dialog);
//        linearLayout.setBackground(ContextCompat.getDrawable(requireContext(), R.drawable.linearlayout_background)); //set background cho dialog
        AlertDialog dialog1 = builder.create();
        dialog1.getWindow().setBackgroundDrawableResource(R.drawable.linearlayout_background);
        text01 = view.findViewById(R.id.namestudent);
        text02 = view.findViewById(R.id.genderstudent);
        text03 = view.findViewById(R.id.yearschool);
        text04 = view.findViewById(R.id.groupstudent);
        text05 = view.findViewById(R.id.email);
        text06 = view.findViewById(R.id.ctdt);
        text07 = view.findViewById(R.id.dean);
        text08 = view.findViewById(R.id.study);
        showStudentData();
        Button cancel = view.findViewById(R.id.btnCancel);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        return dialog1;
    }

    private void showStudentData() {
        try {
            JSONObject jsonObject = new JSONObject(Utils.getInstance().getValueFromSharedPreferences(getContext(), "share_preferences_data", "key_share_preferences_data_thong_tin_sinh_vien"));
            text01.setText(jsonObject.getString("Ho_ten"));
            text02.setText(jsonObject.getString("Gioi_tinh"));
            text03.setText(jsonObject.getString("Nam_vao_truong"));
            text04.setText(jsonObject.getString("Khoa"));
            text05.setText(jsonObject.getString("Email"));
            text06.setText(jsonObject.getString("Chuong_tring"));
            text07.setText(jsonObject.getString("Khoa_vien"));
            text08.setText(jsonObject.getString("Tinh_trang"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
