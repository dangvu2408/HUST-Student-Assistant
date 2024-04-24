package com.example.app_01;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;


public class Class_Info extends AppCompatActivity {
    private ListView listClassView;
    private CustomAdapter adapter;
    private ArrayList<SinhVien> arrayClass;
    private ProgressBar progressBar;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.class_info_activity);

        progressBar = findViewById(R.id.progress_bar);
        listClassView = findViewById(R.id.class_list);
        arrayClass = new ArrayList<>();
        progressBar.setVisibility(View.VISIBLE);
        DatabaseReference mData = FirebaseDatabase.getInstance().getReference();
        mData.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                for (DataSnapshot snapshot1 : snapshot.getChildren()) {
                    String HoTen = snapshot1.child("HoTen").getValue(String.class);
                    String MSSV = snapshot1.child("MSSV").getValue(String.class);
                    int numberlist = snapshot1.child("numberlist").getValue(int.class);

                    SinhVien sv = new SinhVien(HoTen, MSSV, numberlist);
                    arrayClass.add(sv);
                }
                if (adapter == null) {
                    adapter = new CustomAdapter(Class_Info.this, arrayClass);
                    listClassView.setAdapter(adapter);
                } else {
                    adapter.notifyDataSetChanged();
                }
                progressBar.setVisibility(View.GONE);
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        Button btn_back = findViewById(R.id.gobackclass);
        btn_back.setOnClickListener(v -> finish());
    }
}