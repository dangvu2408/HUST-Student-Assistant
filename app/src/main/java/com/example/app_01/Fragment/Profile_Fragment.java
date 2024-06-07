package com.example.app_01.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.app_01.AppCompat.Class_Info;
import com.example.app_01.AppCompat.Grade_Term;
import com.example.app_01.AppCompat.Hust_Map;
import com.example.app_01.Dialog.Student_Info_Dialog;
import com.example.app_01.Dialog.Toeic_Dialog;
import com.example.app_01.LoginPropeties.LoginActivity;
import com.example.app_01.R;
import com.example.app_01.UtilsPack.Utils;

import org.json.JSONException;
import org.json.JSONObject;

public class Profile_Fragment extends Fragment {
    public TextView textView, textView01, textView02;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.profile_fragment, container, false);
        textView = view.findViewById(R.id.namestudent);
        textView01 = view.findViewById(R.id.idstudent);
        textView02 = view.findViewById(R.id.email);
        Button btn_info = view.findViewById(R.id.student_info);
        Button btn_toeic = view.findViewById(R.id.toeic);
        Button btn_map = view.findViewById(R.id.hust_map);
        Button btn_class = view.findViewById(R.id.class_info);
        Button btn_input_term = view.findViewById(R.id.newest_score);
        Button btn_signout = view.findViewById(R.id.signout);
        ImageButton edtavt = view.findViewById(R.id.editprofile);

        edtavt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction("android.intent.action.GET_CONTENT");
                startActivityForResult(Intent.createChooser(intent, "Select Picture"), 1);
            }
        });

        try {
            JSONObject jsonObject = new JSONObject(Utils.getInstance().getValueFromSharedPreferences(getContext(), "share_preferences_data", "key_share_preferences_data_thong_tin_sinh_vien"));
            textView.setText(jsonObject.getString("Ho_ten"));
            textView01.setText(jsonObject.getString("MSSV"));
            textView02.setText(jsonObject.getString("Email"));
        } catch (JSONException e) {
            e.printStackTrace();
        }


        btn_info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Student_Info_Dialog dialog = new Student_Info_Dialog();
                dialog.show(getActivity().getSupportFragmentManager(), "CustomDialog");
            }
        });


        btn_toeic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toeic_Dialog dialog = new Toeic_Dialog();
                dialog.show(getActivity().getSupportFragmentManager(), "CustomDialog");
            }
        });

        btn_map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Hust_Map.class);
                startActivity(intent);
            }
        });

        btn_input_term.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Grade_Term.class);
                startActivity(intent);
            }
        });

        btn_class.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Class_Info.class);
                startActivity(intent);
            }
        });
        btn_signout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Profile_Fragment.this.getContext() != null) {
                    Utils.getInstance().saveToSharedPreferences(Profile_Fragment.this.getContext(), "share_preferences_data", "key_share_preferences_data_already_user_login", "");
                    Utils.getInstance().saveToSharedPreferences(Profile_Fragment.this.getContext(), "share_preferences_data", "key_share_preferences_data_hoc_phan_cai_thien", "");
                    Utils.getInstance().saveToSharedPreferences(Profile_Fragment.this.getContext(), "share_preferences_data", "key_share_preferences_data_hoc_phan_moi", "");
                    Utils.getInstance().saveToSharedPreferences(Profile_Fragment.this.getContext(), "share_preferences_data", "key_share_preferences_data_hoc_phan_khong_tinh_diem", "");
                    Utils.getInstance().saveToSharedPreferences(Profile_Fragment.this.getContext(), "share_preferences_data", "key_share_preferences_data_thong_tin_sinh_vien", "");
                    Utils.getInstance().saveToSharedPreferences(Profile_Fragment.this.getContext(), "share_preferences_data", "key_share_preferences_data_tkb", "");
                    Intent intent = new Intent(getActivity(), LoginActivity.class);
                    startActivity(intent);
                    if (Profile_Fragment.this.getActivity() != null) {
                        Profile_Fragment.this.getActivity().finish();
                    }
                }
            }
        });
        return view;
    }
}