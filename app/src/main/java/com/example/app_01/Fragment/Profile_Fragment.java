package com.example.app_01.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;

import com.example.app_01.AppCompat.Class_Info;
import com.example.app_01.AppCompat.Hust_Map;
import com.example.app_01.LoginPropeties.LoginActivity;
import com.example.app_01.R;
import com.example.app_01.Dialog.Student_Info_Dialog;
import com.example.app_01.Dialog.Toeic_Dialog;

public class Profile_Fragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.profile_fragment, container, false);

        Button btn_info = view.findViewById(R.id.student_info);
        Button btn_toeic = view.findViewById(R.id.toeic);
        Button btn_map = view.findViewById(R.id.hust_map);
        Button btn_class = view.findViewById(R.id.class_info);
        Button btn_signout = view.findViewById(R.id.signout);
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
                Intent intent = new Intent(getActivity(), LoginActivity.class);
                startActivity(intent);
            }
        });
        return view;
    }
}