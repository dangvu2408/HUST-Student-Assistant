package com.example.app_01.Adapter;

import android.content.Context;
import android.widget.TextView;

import com.example.app_01.R;
import com.github.mikephil.charting.components.MarkerView;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.utils.MPPointF;

import java.util.List;

public class CustomMarkerView2 extends MarkerView {
    private TextView hocki, gpa, tinchitl;
    private String data;
    private List<String> tinchitichluy;
    private List<String> hockihoc;
    public CustomMarkerView2 (Context context, int layoutResource, List<String> tinchitichluy, List<String> hockihoc) {
        super(context, layoutResource);
        // this markerview only displays a textview
        this.tinchitichluy = tinchitichluy;
        this.hockihoc = hockihoc;

        hocki = findViewById(R.id.hocki);
        gpa = findViewById(R.id.gpa);
        tinchitl = findViewById(R.id.tctl);
    }



    @Override
    public void refreshContent(Entry e, Highlight highlight) {
        gpa.setText("CPA: " + e.getY());
        int pos = (int) e.getX();
        hocki.setText("Học kì: " + hockihoc.get(pos));
        tinchitl.setText("TC tích lũy: " + tinchitichluy.get(pos));
        super.refreshContent(e, highlight);
    }

    @Override
    public MPPointF getOffset() {
        // Đặt vị trí của MarkerView
        return new MPPointF(-(getWidth() / 2), -getHeight());
    }
}
