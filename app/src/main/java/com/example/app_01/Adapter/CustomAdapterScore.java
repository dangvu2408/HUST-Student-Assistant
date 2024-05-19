package com.example.app_01.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.example.app_01.Constructor.CourseScore;
import com.example.app_01.R;

import java.util.List;

public class CustomAdapterScore extends BaseAdapter {
    final private Context context;
    final private List<CourseScore> itemList;

    public CustomAdapterScore(Context context, List<CourseScore> itemList) {
        this.context = context;
        this.itemList = itemList;
    }

    @Override
    public int getCount() {
        return itemList.size();
    }

    @Override
    public Object getItem(int position) {
        return itemList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View listItem = convertView;
        if (listItem == null) {
            listItem = LayoutInflater.from(context).inflate(R.layout.course_score_listitem, parent, false);
        }

        CourseScore currentStudent = itemList.get(position);


        return listItem;
    }
}
