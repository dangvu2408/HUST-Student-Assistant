package com.example.app_01;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

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

        TextView coursename = listItem.findViewById(R.id.coursename);
        coursename.setText(currentStudent.getCourseName());

        TextView courseid = listItem.findViewById(R.id.courseid);
        courseid.setText(currentStudent.getCourseID());

        TextView tc = listItem.findViewById(R.id.tinchi);
        tc.setText(String.valueOf(currentStudent.getTC()));

        TextView diemQT = listItem.findViewById(R.id.QTscore);
        diemQT.setText(String.valueOf(currentStudent.getQT()));

        TextView diemCK = listItem.findViewById(R.id.CKscore);
        diemCK.setText(String.valueOf(currentStudent.getCK()));

        TextView alphascore = listItem.findViewById(R.id.alphabet);
        alphascore.setText(currentStudent.getAlphabet());

        return listItem;
    }
}
