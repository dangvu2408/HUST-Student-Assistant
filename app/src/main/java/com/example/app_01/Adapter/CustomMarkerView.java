package com.example.app_01.Adapter;

import android.content.Context;
import android.widget.TextView;

import com.example.app_01.R;
import com.github.mikephil.charting.components.MarkerView;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.utils.MPPointF;

import java.util.List;

public class CustomMarkerView extends MarkerView {
    private TextView hocki, gpa, tinchitl;
    private String data;
    private List<String> tinchiqua;
    private List<String> hockihoc;
    public CustomMarkerView (Context context, int layoutResource, List<String> tinchiqua, List<String> hockihoc) {
        super(context, layoutResource);
        // this markerview only displays a textview
        this.tinchiqua = tinchiqua;
        this.hockihoc = hockihoc;

        hocki = findViewById(R.id.hocki);
        gpa = findViewById(R.id.gpa);
        tinchitl = findViewById(R.id.tctl);
    }



    @Override
    public void refreshContent(Entry e, Highlight highlight) {
        gpa.setText("GPA: " + e.getY());
        int pos = (int) e.getX();
        hocki.setText("Học kì: " + hockihoc.get(pos));
        tinchitl.setText("TC qua: " + tinchiqua.get(pos));
        super.refreshContent(e, highlight);
    }

    @Override
    public MPPointF getOffset() {
        // Đặt vị trí của MarkerView
        return new MPPointF(-(getWidth() / 2), -getHeight());
    }
}
