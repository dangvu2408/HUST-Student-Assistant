package com.example.app_01;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


public class Class_Info extends AppCompatActivity {
    private ListView listClassView;
    private CustomAdapter adapter;
    private List<ListViewItem> arrayClass;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.class_info_activity);

        listClassView = findViewById(R.id.class_list);
        arrayClass = new ArrayList<>();
        DatabaseReference mData = FirebaseDatabase.getInstance().getReference();
        mData.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                arrayClass.clear();
                for (DataSnapshot snapshot1 : snapshot.getChildren()) {
                    ListViewItem data = snapshot1.getValue(ListViewItem.class);
                    arrayClass.add(data);
                }
                if (adapter == null) {
                    adapter = new CustomAdapter(Class_Info.this, arrayClass);
                    listClassView.setAdapter(adapter);
                } else {
                    adapter.notifyDataSetChanged();
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                //lenh cancel
            }
        });

        Button btn_back = findViewById(R.id.gobackclass);
        btn_back.setOnClickListener(v -> finish());
    }
}