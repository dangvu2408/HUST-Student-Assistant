package com.example.app_01;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;

public class Class_Info extends Activity {
    private ListView classlist;
    String[] namestudent = {"Pham Thien An", "Nguyen Cao Quang Anh", "Nguyen Tuan Anh", "Vu Duy Viet Anh"};
    String[] numberlist = {"1", "2", "3", "4"};
    String[] idstudent = {"20223840", "20223849", "20223855", "20223862"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.class_info_activity);
        classlist = (ListView)findViewById(R.id.class_list);



        ListViewItem adapter = new ListViewItem(this, namestudent, numberlist, idstudent);
        classlist.setAdapter(adapter);



        Button btn_back = findViewById(R.id.gobackclass);
        btn_back.setOnClickListener(v -> finish());
    }
}