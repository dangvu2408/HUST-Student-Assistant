package com.example.app_01.AppCompat;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import com.example.app_01.R;
import com.example.app_01.UtilsPack.Utils;
import com.shawnlin.numberpicker.NumberPicker;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class MainActivity2 extends AppCompatActivity {
    private ImageButton img1;
    private Spinner spinner;
    private List<String> list;
    private NumberPicker numberPicker;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        numberPicker = findViewById(R.id.number_picker);
        numberPicker.setTypeface(ResourcesCompat.getFont(this, R.font.sfpro_bold));
        numberPicker.setSelectedTypeface(ResourcesCompat.getFont(this, R.font.sfpro_bold));
        numberPicker.setValue(Integer.parseInt(Utils.getInstance().getWeek(this)));
        list = new ArrayList<>();
        list.add("15 phút");
        list.add("30 phút");
        list.add("45 phút");
        list.add("60 phút");
        spinner = findViewById(R.id.spinneropt);
        ArrayAdapter adapter = new ArrayAdapter<>(this, R.layout.spinner_item, list);
        spinner.setAdapter(adapter);
        Button button = findViewById(R.id.saveSet);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lamdaSetWeek();
                finish();
            }
        });
        img1 = findViewById(R.id.backtoMain);
        img1.setOnClickListener(v -> finish());
    }

    public void lamdaSetWeek() {
        Utils.getInstance().saveToSharedPreferences(this, "share_preferences_data", "key_share_preferenceds_data_tuan_hoc_hien_tai", String.valueOf(numberPicker.getValue()));
        Utils.getInstance().saveToSharedPreferences(this, "share_preferences_data", "key_share_preferenceds_data_week_of_year", String.valueOf(Calendar.getInstance().get(3)));

    }
}